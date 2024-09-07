package org.example.coroutinesscope

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.time.Duration

/**
 * Bridges threads to coroutines. Blocks underlying thread
 */
fun main() = runBlocking {
    println("Started work...")
    val start = System.currentTimeMillis()
    doWork()
    println("Done in ${(System.currentTimeMillis()-start)}")
}

/**
 * Does not block underlying thread, instead suspends the execution until all coroutines are finished
 */
suspend fun doWork() = coroutineScope {
    for (i in (1..10)) {
        launch { doSomething(i) } // launches coroutine concurrently
    }
}

suspend fun doSomething(id: Int) {
    val randDuration = Random.nextLong(1000L)
    delay(randDuration)

    println("$id -> $randDuration")
}
