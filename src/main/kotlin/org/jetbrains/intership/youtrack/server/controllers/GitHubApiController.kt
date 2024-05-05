package org.jetbrains.intership.youtrack.server.controllers

import org.jetbrains.intership.youtrack.server.dto.ConfigurationDto
import org.jetbrains.intership.youtrack.server.dto.RepositoryDto
import org.jetbrains.intership.youtrack.server.dto.SuccessResultDto
import org.jetbrains.intership.youtrack.server.services.FilterRepositoryService
import org.jetbrains.intership.youtrack.server.services.GitHubService
import org.jetbrains.intership.youtrack.server.services.ValidationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class GitHubApiController(
    private val validationService: ValidationService,
    private val repositoryService: GitHubService,
    private val filterRepositoryService: FilterRepositoryService,
) {
    @PostMapping("/filter-repos-api")
    fun filterReposApi(@RequestBody config: ConfigurationDto): SuccessResultDto {
        validationService.validate(config)
        val repos = getRepositories(config)
        return SuccessResultDto(data = repos)
    }

    private fun getRepositories(config: ConfigurationDto): List<RepositoryDto> {
        val organization = config.link
        val token = config.token

        val repos = repositoryService.getRepositories(organization, token)
        return filterRepositoryService.filterRepositoriesByConfig(config, repos)
    }
}