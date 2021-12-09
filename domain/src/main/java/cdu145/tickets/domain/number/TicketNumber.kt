package cdu145.tickets.domain.number

interface TicketNumber {

    val value: Int
}

fun TicketNumber(value: Int): TicketNumber = IntTicketNumber(value)

@JvmInline
private value class IntTicketNumber(
    override val value: Int,
) : TicketNumber {

    init {
        require(value in 0..999_999) { "Invalid ticket number: $value" }
    }
}

val ZeroTicketNumber = TicketNumber(0)