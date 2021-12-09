package cdu145.tickets.domain.solution.result

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import cdu145.tickets.domain.digits.TicketDigits
import cdu145.tickets.domain.solution.Solution
import cdu145.tickets.domain.solution.result.SolutionResult.Defined
import cdu145.tickets.domain.solution.result.SolutionResult.Undefined
import cdu145.tickets.domain.solution.sign.SolutionSign.*

@Suppress("ClassName")
internal class ResultOfTests {

    @Nested
    inner class `When there is division by 0, should be undefined` {

        @Test
        fun `Case 1 - direct division by 0`() {
            assertThat(
                    resultOf(
                            Solution(None, None, None, None, Div),
                            TicketDigits(0, 0, 0, 0, 0, 0),
                    ),
                    equalTo(Undefined),
            )
        }

        @Test
        fun `Case 2 - indirect division by 0`() {
            assertThat(
                    resultOf(
                            Solution(None, None, None, Div, None),
                            TicketDigits(0, 0, 0, 0, 0, 0),
                    ),
                    equalTo(Undefined),
            )
        }
    }

    @Test
    fun `When there is no division by 0, should be defined`() {
        assertThat(
                resultOf(
                        Solution(Times, Plus, Div, Minus, None),
                        TicketDigits(5, 2, 7, 7, 0, 9),
                ),
                equalTo(Defined(2.0)),
        )
    }

    @Test
    fun `Should be evaluated normally when the lowest priority sign is at the end`() {
        assertThat(
                resultOf(
                        Solution(None, None, None, None, Plus),
                        TicketDigits(0, 0, 0, 0, 1, 2),
                ),
                equalTo(Defined(3.0)),
        )
    }

    @Test
    fun `Should be evaluated normally when the lowest priority sign is at the beginning`() {
        assertThat(
                resultOf(
                        Solution(Plus, None, None, None, None),
                        TicketDigits(1, 2, 0, 0, 0, 0),
                ),
                equalTo(Defined(20001.0)),
        )
    }

    @Test
    fun `Correct arithmetic operations order should be followed`() {
        assertThat(
                resultOf(
                        Solution(Plus, Minus, Minus, None, Plus),
                        TicketDigits(3, 6, 1, 8, 6, 6),
                ),
                equalTo(Defined(-72.0)),
        )
    }
}