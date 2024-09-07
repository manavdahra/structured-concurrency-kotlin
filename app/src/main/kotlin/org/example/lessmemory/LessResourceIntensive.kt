package org.example.lessmemory

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Coroutines are less resource intensive as compared to threads.
 * Spawning 50K JVM threads will lead to OOM. However, spawning 50K coroutines will not.
 */
fun main() = runBlocking {
    repeat(50_000) {
        launch {
            delay(5000L)
            print(".")
        }
    }
}