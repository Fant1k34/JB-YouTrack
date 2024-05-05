package org.jetbrains.intership.youtrack.server.dto

data class SuccessResultDto(val status: String = "OK", val data: List<RepositoryDto>)
