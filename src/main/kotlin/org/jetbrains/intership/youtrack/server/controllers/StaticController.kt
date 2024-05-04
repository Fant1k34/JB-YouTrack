package org.jetbrains.intership.youtrack.server.controllers

import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.core.io.ClassPathResource
import org.springframework.web.bind.annotation.*

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
