package org.jetbrains.intership.youtrack.server.services

import org.jetbrains.intership.youtrack.server.dto.ConfigurationDto
import org.jetbrains.intership.youtrack.server.dto.RepositoryDto
import org.springframework.stereotype.Service

@Service
class FilterRepositoryService(private val gitHubService: GitHubService) {
    fun filterRepositoriesByConfig(config: ConfigurationDto, repos: List<RepositoryDto>): List<RepositoryDto> {
        return repos.filter { repo -> filterRepo(config, repo) }
    }

    private fun filterRepo(config: ConfigurationDto, repo: RepositoryDto): Boolean {
        val content = gitHubService.getFileContent(config.link, repo.name, config.searchFile, config.token)
        return content?.contains(config.keyword) == true
    }
}