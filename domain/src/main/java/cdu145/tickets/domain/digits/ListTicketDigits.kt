package cdu145.tickets.domain.digits

class ListTicketDigits(
    private val digits: List<Int>,
) : TicketDigits {

    init {
        digits.count().let {
            require(it == 6) { "Invalid digit count: $it" }
        }
    }

    override fun get(position: Int): Int = digits[position]
}