import kotlin.concurrent.thread

fun createThreadInNewState(): Thread {
    return thread(start = false, block = {})
}
