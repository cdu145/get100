package cdu145.tickets.domain.solution

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

internal class AllPossibleSolutionsTests {

    @Test
    fun `Count should be 5^5 = 3125`() {
        assertThat(
            allPossibleSolutions().count(),
            equalTo(3125),
        )
    }
}