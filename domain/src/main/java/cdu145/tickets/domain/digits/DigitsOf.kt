package cdu145.tickets.domain.digits

import cdu145.tickets.domain.number.TicketNumber
import kotlin.math.pow

private fun TicketNumber.digitAt(position: Int): Int {
    return (this.value / 10.0.pow(5 - position).toInt()) % 10
}

fun digitsOf(ticketNumber: TicketNumber): TicketDigits {
    return ListTicketDigits(
        List(size = 6) { i -> ticketNumber.digitAt(i) },
    )
}