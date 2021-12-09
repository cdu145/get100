package cdu145.tickets.domain.digits

import com.natpryce.hamkrest.assertion.assertThat
import org.junit.jupiter.api.Test
import cdu145.tickets.domain.number.TicketNumber

internal class DigitsOfTests {

    @Test
    fun `Should parse number's value's decimal digits`() {
        assertThat(
            digitsOf(
                TicketNumber(123456),
            ),
            equivalentTo(
                TicketDigits(1, 2, 3, 4, 5, 6),
            )
        )
    }
}