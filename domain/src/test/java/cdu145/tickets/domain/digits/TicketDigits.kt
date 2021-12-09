package cdu145.tickets.domain.digits

@Suppress("TestFunctionName")
internal fun TicketDigits(d1: Int, d2: Int, d3: Int, d4: Int, d5: Int, d6: Int): TicketDigits {
    return ListTicketDigits(listOf(d1, d2, d3, d4, d5, d6))
}