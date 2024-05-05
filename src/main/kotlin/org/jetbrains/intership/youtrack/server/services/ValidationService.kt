package org.jetbrains.intership.youtrack.server.services

import org.jetbrains.intership.youtrack.server.dto.ConfigurationDto
import org.jetbrains.intership.youtrack.server.exception.ValidationException
import org.springframework.stereotype.Service

@Service
class ValidationService {
    fun validate(config: ConfigurationDto) {
        if (config.keyword.isEmpty()) {
            throw ValidationException("Keyword could not be empty")
        }

        if (config.searchFile.isEmpty()) {
            throw ValidationException("Search file could not be empty")
        }
    }
}