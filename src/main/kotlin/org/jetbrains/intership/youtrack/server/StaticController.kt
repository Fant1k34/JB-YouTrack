package org.jetbrains.intership.youtrack.server

import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class StaticController {
    @GetMapping("/")
    fun page(): String {
        return "index"
    }

    @GetMapping("/bundle.js")
    @ResponseBody
    fun getImageDynamicType(): ResponseEntity<InputStreamResource> {
        val contentType: MediaType = MediaType.TEXT_PLAIN
        val data = javaClass.getResourceAsStream(
            "/src/main/resources/templates/bundle.js"
        )

        return ResponseEntity.ok()
            .contentType(contentType)
            .body(data?.let { InputStreamResource(it) })
    }
}
