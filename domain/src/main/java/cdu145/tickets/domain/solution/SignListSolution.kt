package cdu145.tickets.domain.solution

import cdu145.tickets.domain.solution.sign.SolutionSign

class SignListSolution(
    private val signs: List<SolutionSign>,
) : Solution {

    init {
        signs.count().let {
            require(it == 5) { "Invalid sign count: $it" }
        }
    }

    override fun signAt(position: Int): SolutionSign = signs[position]
}