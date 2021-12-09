package cdu145.tickets.domain.solution.result

import cdu145.tickets.domain.digits.TicketDigits
import cdu145.tickets.domain.digits.notEquivalentTo
import cdu145.tickets.domain.solution.Solution
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter

@Suppress("FunctionName")
fun SolutionResultFlow(
    ticketDigitsFlow: Flow<TicketDigits>,
    solutionUpdatesFlow: Flow<Solution>,
): Flow<SolutionResult> {
    return ticketDigitsFlow
        .filter { it notEquivalentTo TicketDigits.Zeros }
        .combine(solutionUpdatesFlow) { ticketDigits, solution ->
            resultOf(solution, ticketDigits)
        }
}