package org.jetbrains.intership.youtrack.server

import com.google.gson.Gson
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@SpringBootApplication
class ServerApplication

fun main(args: Array<String>) {
	runApplication<ServerApplication>(*args)
}


//fun main() {
//	val paramsData = mapOf(
//		"Accept" to "application/vnd.github+json",
//		"Authorization" to "Bearer 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",
//		"X-GitHub-Api-Version" to "2022-11-28"
//	)


//	val client = HttpClient.newBuilder().build()
//	val request = HttpRequest.newBuilder()
//		.uri(URI.create("https://api.github.com/orgs/stepancar-web-programming/repos"))
//		.header("Accept", "application/vnd.github+json")
//		.header("Authorization", "Bearer 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")
//		.header("X-GitHub-Api-Version", "2022-11-28")
//		.build()
//
//	val response = client.send(request, HttpResponse.BodyHandlers.ofString())
//	println(response.body())
//}

