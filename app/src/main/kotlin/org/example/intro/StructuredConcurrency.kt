package org.example.intro

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Structured concurrency
 *
 * The principle of writing concurrent code such that, it follows a well-defined structure.
 * Here, in this case, we can only spawn coroutines inside a runBlocking coroutine builder.
 *
 * In a real application, you will be launching a lot of coroutines.
 * Structured concurrency ensures that they are not lost and do not leak.
 * An outer scope cannot complete until all its children coroutines complete.
 * Structured concurrency also ensures that any errors in the code are properly reported and are never lost.
 *
 * runBlocking
 * -> fun <T> runBlocking(context: CoroutineContext, block: suspend CoroutineScope.() -> T): T
 *
 * launch
 * -> fun CoroutineScope.launch(
 *     context: CoroutineContext = EmptyCoroutineContext,
 *     start: CoroutineStart = CoroutineStart.DEFAULT,
 *     block: suspend CoroutineScope.() -> Unit
 * ): Job
 * co routine builder that launches a coroutine concurrently and returns a job
 *
 * delay
 * suspend fun delay(timeMillis: Long)
 */
fun main() = runBlocking { // bridges non-coroutine world with coroutine world
    launch { // launch a non-blocking coroutine
        // delay is a suspend function that suspends the execution of a coroutine without blocking the thread.
        delay(1000L)
        println("World")
    }
    println("Hello")
}
