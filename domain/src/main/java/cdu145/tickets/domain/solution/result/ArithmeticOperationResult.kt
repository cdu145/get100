package cdu145.tickets.domain.solution.result

import cdu145.tickets.domain.solution.sign.SolutionSign
import cdu145.tickets.domain.solution.sign.SolutionSign.*

internal fun arithmeticOperationResultOrNull(
    sign: SolutionSign,
    left: Double?,
    right: Double?,
): Double? {
    return if (left != null && right != null) {
        sign.performOperation(left, right)
    } else {
        null
    }
}

private fun SolutionSign.performOperation(left: Double, right: Double): Double? {
    return when (this) {
        Plus -> left + right
        Minus -> left - right
        Times -> left * right
        Div -> left.safelyDividedBy(right)
        None -> numberOfTwoParts(left, right)
    }
}

private fun Double.safelyDividedBy(divider: Double): Double? {
    return if (divider == 0.0) null else this / divider
}

private fun numberOfTwoParts(left: Double, right: Double): Double {
    return (left * right.decimalShift) + right
}

private val Double.decimalShift: Int
    get() {
        var value = 10
        while (value <= this) {
            value *= 10
        }
        return value
    }