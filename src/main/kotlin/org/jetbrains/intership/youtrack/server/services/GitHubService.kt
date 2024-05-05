package org.jetbrains.intership.youtrack.server.services

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.intership.youtrack.server.dto.RepositoryDto
import org.jetbrains.intership.youtrack.server.exception.GitHubException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class GitHubService {
    private fun getRepositoriesFromResponse(response: HttpResponse<String>): List<RepositoryDto> {
        val type = object : TypeToken<List<Map<String, Any>>>() {}.type
        val result: List<Map<String, Any>> = Gson().fromJson(response.body(), type)
        return result.map { mapStringToRepository(it) }.toList()
    }

    private fun mapStringToRepository(line: Map<String, Any>): RepositoryDto {
        try {
            val url = line["clone_url"] as String
            val name = line["name"] as String

            return RepositoryDto(name, url)
        } catch (_: Throwable) {
            throw GitHubException("Mapping exception")
        }
    }

    fun getRepositories(organization: String, key: String): List<RepositoryDto> {
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.github.com/orgs/$organization/repos"))
            .header("Accept", "application/vnd.github+json")
            .header("Authorization", "Bearer $key")
            .header("X-GitHub-Api-Version", "2022-11-28")
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() != 200) throw GitHubException("Repo is not found")

        return getRepositoriesFromResponse(response)
    }

    fun getFileContent(organization: String, repoName: String, fileName: String, key: String): String? {
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.github.com/repos/$organization/$repoName/contents/$fileName"))
            .header("Accept", "application/vnd.github.raw+json")
            .header("Authorization", "Bearer $key")
            .header("X-GitHub-Api-Version", "2022-11-28")
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        return if (response.statusCode() == HttpStatus.OK.value()) response.body() else null
    }
}