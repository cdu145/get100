package cdu145.tickets.domain.solution.result

import cdu145.tickets.domain.digits.TicketDigits
import cdu145.tickets.domain.solution.Solution
import cdu145.tickets.domain.solution.result.SolutionResult.Undefined
import cdu145.tickets.domain.solution.result.SolutionResult.Defined
import cdu145.tickets.domain.solution.sign.SolutionSign
import cdu145.tickets.domain.solution.sign.SolutionSign.*
import cdu145.util.SignPositionRangePool

internal fun resultOf(solution: Solution, ticketDigits: TicketDigits): SolutionResult {
    return solution
        .resultInRangeOrNull(ticketDigits, from = 0, to = 5)
        ?.let(::Defined)
        ?: Undefined
}

private fun Solution.resultInRangeOrNull(
    ticketDigits: TicketDigits,
    from: Int,
    to: Int,
): Double? {
    return if (from == to) {
        ticketDigits[from].toDouble()
    } else {
        val range = positionsBetweenDigits(from, to)
        val position = findLastInRange(range) { it == Plus || it == Minus }
            ?: findLastInRange(range) { it == Times || it == Div }
            ?: findLastInRange(range) { it == None }!!
        return arithmeticOperationResultOrNull(
            sign = this.signAt(position),
            left = resultInRangeOrNull(ticketDigits, from, positionOfDigitBefore(position)),
            right = resultInRangeOrNull(ticketDigits, positionOfDigitAfter(position), to),
        )
    }
}

private fun positionsBetweenDigits(firstDigitPosition: Int, lastDigitPosition: Int): IntRange {
    return SignPositionRangePool.obtain(start = firstDigitPosition, end = lastDigitPosition - 1)
}

private inline fun Solution.findLastInRange(
    range: IntRange,
    predicate: (SolutionSign) -> Boolean,
): Int? {
    var positionOfLast = -1
    for (position in range) {
        if (
            predicate(
                this.signAt(position),
            )
        ) {
            positionOfLast = position
        }
    }
    return positionOfLast.takeIf { it != -1 }
}

private fun positionOfDigitBefore(signPosition: Int): Int = signPosition

private fun positionOfDigitAfter(signPosition: Int): Int = signPosition + 1