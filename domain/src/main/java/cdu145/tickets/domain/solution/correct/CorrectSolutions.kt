package cdu145.tickets.domain.solution.correct

import cdu145.tickets.domain.digits.TicketDigits
import cdu145.tickets.domain.digits.digitsOf
import cdu145.tickets.domain.number.TicketNumber
import cdu145.tickets.domain.solution.result.isHundred
import cdu145.tickets.domain.solution.result.resultOf
import cdu145.tickets.domain.solution.Solution

typealias CorrectSolutions = Iterable<Solution>

fun TicketNumber.correctSolutions(
    allPossibleSolutions: Iterable<Solution>,
): CorrectSolutions {
    val digits = digitsOf(this)
    return allPossibleSolutions
        .filter { it.correctlySolves(digits) }
        .toList()
}

private fun Solution.correctlySolves(digits: TicketDigits): Boolean {
    return resultOf(this, digits).isHundred
}