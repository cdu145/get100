package cdu145.tickets.domain.solution.sign.replacement

import cdu145.tickets.domain.solution.Solution
import cdu145.tickets.domain.solution.sign.SolutionSign
import cdu145.tickets.domain.solution.asIterable

internal fun Solution.replacementsToTransformInto(other: Solution): List<SignReplacement> {
    return this
        .asIterable()
        .zipIndexed(other.asIterable())
        .filter { (actualSign, targetSign) -> actualSign != targetSign }
        .map { (_, newSign, index) -> SignReplacement(position = index, newSign) }
}

private fun Iterable<SolutionSign>.zipIndexed(
    other: Iterable<SolutionSign>,
): Iterable<Triple<SolutionSign, SolutionSign, Int>> {
    var index = 0
    return this.zip(other) { fromThis, fromThat -> Triple(fromThis, fromThat, index++) }
}
