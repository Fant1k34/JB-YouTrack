package org.jetbrains.intership.youtrack.server.controllers

import com.google.gson.Gson
import org.jetbrains.intership.youtrack.server.services.Repository
import org.jetbrains.intership.youtrack.server.services.getOrganizationReposList
import org.jetbrains.intership.youtrack.server.services.getRepoGetFileContent
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


data class Configuration(val token: String, val keyword: String, val link: String, val searchFile: String)

@RestController
class GitHubApiController {
    @PostMapping("/filter-repos-api")
    suspend fun filterReposApi(@RequestBody config: Configuration): String {
        val organization = config.link
        val key = config.token
        val word = config.keyword
        val file = config.searchFile
        val filteredRepos = mutableListOf<Repository>()

        if (word == "") return Gson().toJson(
            mapOf(
                "status" to "ERROR",
                "message" to "Keyword could not be empty"
            )
        )

        if (file == "") return Gson().toJson(
            mapOf(
                "status" to "ERROR",
                "message" to "File must be defined"
            )
        )

        val repos = getOrganizationReposList(organization, key)
            ?: return Gson().toJson(
                mapOf(
                    "status" to "ERROR",
                    "message" to "Organization within this access token does not exist"
                )
            )

        repos.forEach {
            val content = getRepoGetFileContent(organization, it.name, file, key)

            if (content?.contains(word) == true) {
                filteredRepos.add(it)
            }
        }

        return Gson().toJson(
            mapOf(
                "status" to "OK",
                "data" to filteredRepos
            )
        )
    }
}