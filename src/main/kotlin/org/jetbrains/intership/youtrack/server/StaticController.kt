package org.jetbrains.intership.youtrack.server

import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.core.io.ClassPathResource
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


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
    fun filterReposApi(): String {
        return "[1, 2, 3]"
    }
}
