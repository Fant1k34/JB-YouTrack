package org.jetbrains.intership.youtrack.server

import com.google.gson.Gson
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.core.io.ClassPathResource
import org.springframework.web.bind.annotation.*


data class Configuration(val token: String, val keyword: String, val link: String, val searchFile: String)

@Controller
class StaticController {
    @GetMapping("/")
    fun page(): String {
        return "index"
    }

    @GetMapping("/bundle.js")
    @ResponseBody
    fun getImageDynamicType(): ResponseEntity<InputStreamResource> {
        val resource = ClassPathResource("templates/bundle.js")
        val data = resource.inputStream

        println(resource)
        println(data)

        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(InputStreamResource(data))
    }
}

@RestController
class FilterReposController {
    @PostMapping("/filter-repos-api")
    fun filterReposApi(@RequestBody config: Configuration): String {
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
                println(content)
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
