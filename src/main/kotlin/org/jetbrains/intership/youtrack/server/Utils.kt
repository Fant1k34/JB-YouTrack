package org.jetbrains.intership.youtrack.server

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

data class Repository(val name: String, val url: String)


fun getOrganizationReposList(organization: String, key: String): List<Repository> {
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.github.com/orgs/$organization/repos"))
        .header("Accept", "application/vnd.github+json")
        .header("Authorization", "Bearer $key")
        .header("X-GitHub-Api-Version", "2022-11-28")
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    val type = object : TypeToken<List<Map<String, Any>>>() {}.type
    val result: List<Map<String, Any>> = Gson().fromJson(response.body(), type)

    val repos = mutableListOf<Repository>()

    result.forEach {
        val url = it["clone_url"] as String
        val name = it["name"] as String

        repos.add(Repository(name, url))
    }

    return repos
}


fun getRepoDescription(organization: String, repoName: String, key: String): String {
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.github.com/repos/$organization/$repoName/contents/README.md"))
        .header("Accept", "application/vnd.github.raw+json")
        .header("Authorization", "Bearer $key")
        .header("X-GitHub-Api-Version", "2022-11-28")
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    return response.body()
}



