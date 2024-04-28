package org.jetbrains.intership.youtrack.server

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class StaticController {
    @GetMapping("/")
    fun page(): String {
        return "index"
    }
}