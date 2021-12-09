package cdu145.tickets.model.ticketnumber

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import cdu145.model.Actual
import cdu145.tickets.domain.number.TicketNumber
import cdu145.tickets.domain.number.ZeroTicketNumber

interface EnsureTicketNumberCreated {

    operator fun invoke()
}

fun EnsureTicketNumberCreated(
    ticketNumber: Actual.Mutable<TicketNumber>,
    firstNumber: Actual<TicketNumber>,
    scope: CoroutineScope,
): EnsureTicketNumberCreated {
    return object : EnsureTicketNumberCreated {

        override fun invoke() {
            scope.launch(Dispatchers.Main.immediate) {
                if (ticketNumber.value() == ZeroTicketNumber) {
                    ticketNumber.mutate(firstNumber.value())
                }
            }
        }
    }
}