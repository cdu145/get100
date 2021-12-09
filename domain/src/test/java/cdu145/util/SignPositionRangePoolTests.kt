package cdu145.util

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.sameInstance
import org.junit.jupiter.api.Test

internal class SignPositionRangePoolTests {

    @Test
    fun `Should always return the same instance for the same arguments`() {
        assertThat(
            SignPositionRangePool.obtain(start = 1, end = 2),
            sameInstance(
                SignPositionRangePool.obtain(start = 1, end = 2),
            ),
        )
    }

    @Test
    fun `'start' value should be passed as 'start' to the constructor`() {
        assertThat(
            SignPositionRangePool.obtain(start = 1, end = 2).first,
            equalTo(1),
        )
    }

    @Test
    fun `'end' value should be passed as 'endInclusive' to the constructor`() {
        assertThat(
            SignPositionRangePool.obtain(start = 1, end = 2).last,
            equalTo(2),
        )
    }
}