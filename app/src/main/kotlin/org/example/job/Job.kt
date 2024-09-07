package org.example.job

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    doWork()
    println("Done")
}

/**
 * Demonstrates how to handle a job execution inside a coroutine scope.
 * Similar to joining threads, coroutines execution can be controlled by using joins.
 * Coroutines return Job class type, which allows this control.
 */
suspend fun doWork() = coroutineScope {
    val job = launch {
        delay(1000)
        println("World")
    }
    println("Hello")
    job.join()
}

