package org.jetbrains.intership.youtrack.server

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StaticController {
    @GetMapping("/")
    fun page(): String {

        return "Here is mock for first page"
    }
}