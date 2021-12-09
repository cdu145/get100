package cdu145.tickets.domain.solution

import cdu145.tickets.domain.solution.sign.SolutionSign

@Suppress("TestFunctionName")
internal fun Solution(
    s1: SolutionSign,
    s2: SolutionSign,
    s3: SolutionSign,
    s4: SolutionSign,
    s5: SolutionSign,
): Solution {
    return SignListSolution(listOf(s1, s2, s3, s4, s5))
}