package cdu145.tickets.domain.hint.restoring

import kotlinx.coroutines.delay

class RemainingRestorationTime(
    private val millis: Long,
) {

    val seconds: Int
        get() {
            val seconds = (millis / 1000).toInt()
            return if (millis % 1000 == 0L) seconds else seconds + 1
        }

    val over: Boolean
        get() = millis == 0L

    suspend fun tick(): RemainingRestorationTime {
        check(!over) { "Restoration time is over" }
        val delayTime = (millis % 1000).takeIf { it != 0L } ?: 1000L
        delay(delayTime)
        return RemainingRestorationTime(millis - delayTime)
    }
}