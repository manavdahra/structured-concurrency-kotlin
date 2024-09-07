package org.example.suspend

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        // Suspend functions can only be called within another suspend function
        doSomething()
    }
    println("Hello")
}

suspend fun doSomething() {
    delay(1000)
    println("World")
}
