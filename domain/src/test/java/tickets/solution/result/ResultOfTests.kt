package tickets.solution.result

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import tickets.digits.TicketDigits
import tickets.solution.Solution
import tickets.solution.result.SolutionResult.Defined
import tickets.solution.result.SolutionResult.Undefined
import tickets.solution.signs.ArithmeticSign.*

@Suppress("ClassName")
internal class ResultOfTests {

    @Nested
    inner class `When there is division by 0, should be undefined` {

        @Test
        fun `Case 1 - direct division by 0`() {
            assertThat(
                    resultOf(
                            Solution(NONE, NONE, NONE, NONE, DIV),
                            TicketDigits(0, 0, 0, 0, 0, 0),
                    ),
                    equalTo(Undefined),
            )
        }

        @Test
        fun `Case 2 - indirect division by 0`() {
            assertThat(
                    resultOf(
                            Solution(NONE, NONE, NONE, DIV, NONE),
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
                        Solution(TIMES, PLUS, DIV, MINUS, NONE),
                        TicketDigits(5, 2, 7, 7, 0, 9),
                ),
                equalTo(Defined(2.0)),
        )
    }

    @Test
    fun `Should be evaluated normally when the lowest priority sign is at the end`() {
        assertThat(
                resultOf(
                        Solution(NONE, NONE, NONE, NONE, PLUS),
                        TicketDigits(0, 0, 0, 0, 1, 2),
                ),
                equalTo(Defined(3.0)),
        )
    }

    @Test
    fun `Should be evaluated normally when the lowest priority sign is at the beginning`() {
        assertThat(
                resultOf(
                        Solution(PLUS, NONE, NONE, NONE, NONE),
                        TicketDigits(1, 2, 0, 0, 0, 0),
                ),
                equalTo(Defined(20001.0)),
        )
    }

    @Test
    fun `Correct arithmetic operations order should be followed`() {
        assertThat(
                resultOf(
                        Solution(PLUS, MINUS, MINUS, NONE, PLUS),
                        TicketDigits(3, 6, 1, 8, 6, 6),
                ),
                equalTo(Defined(-72.0)),
        )
    }
}