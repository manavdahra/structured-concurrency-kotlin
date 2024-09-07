package org.example.exercise

import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ApiAsync {
    private val httpClient = HttpClient.newHttpClient()
    private val page = Page()

    private val json = Json { ignoreUnknownKeys = true }
    private val usersURI = URI.create("https://jsonplaceholder.typicode.com/users")
    private val postsURI = URI.create("https://jsonplaceholder.typicode.com/posts")
    private val commentsURI = URI.create("https://jsonplaceholder.typicode.com/comments")

    private suspend fun fetchUsers(): List<User> {
        val response = fetchData(usersURI)

        return json.decodeFromString<List<User>>(response.body())
    }

    private suspend fun fetchComments(): List<Comment> {
        val response = fetchData(commentsURI)

        return json.decodeFromString<List<Comment>>(response.body())
    }

    private suspend fun fetchPosts(): List<Post> {
        val response = fetchData(postsURI)

        return json.decodeFromString<List<Post>>(response.body())
    }

    private suspend fun fetchData(uri: URI?): HttpResponse<String> {
        val request = HttpRequest.newBuilder(uri).GET().build()
        val stringBodyHandler = HttpResponse.BodyHandlers.ofString()
        val response = withContext(Dispatchers.IO) {
            httpClient.send(request, stringBodyHandler)
        }
        if (response.statusCode() != 200) {
            throw Exception("server responded with status code: ${response.statusCode()}")
        }
        return response
    }

    fun clearData() {
        page.clear()
    }

    fun loadPageSync() {
        val start = System.currentTimeMillis()
        runBlocking {
            page.addUsers(fetchUsers())
            page.addPosts(fetchPosts())
            page.addComments(fetchComments())
        }

        println("$page\nTime-taken: ${System.currentTimeMillis()-start}")
        println("======================================")
    }

    fun loadPageAsync() {
        val start = System.currentTimeMillis()
        runBlocking {
            launch { page.addUsers(fetchUsers()) }
            launch { page.addPosts(fetchPosts()) }
            launch { page.addComments(fetchComments()) }
        }

        println("$page\nTime-taken: ${System.currentTimeMillis()-start}")
        println("======================================")
    }
}

fun main() {
    val apiAsync = ApiAsync()

    apiAsync.loadPageSync()
    apiAsync.clearData()
    apiAsync.loadPageAsync()
}
