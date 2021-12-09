package cdu145.util

private const val positionCount = 5

internal object SignPositionRangePool {

    private val list = List(size = positionCount * positionCount) { i -> createRangeAt(i) }

    fun obtain(start: Int, end: Int): IntRange = list[positionOfRange(start, end)]

    private fun createRangeAt(position: Int): IntRange {
        return IntRange(
            start = position / positionCount,
            endInclusive = position % positionCount,
        )
    }

    private fun positionOfRange(start: Int, end: Int): Int = start * positionCount + end
}