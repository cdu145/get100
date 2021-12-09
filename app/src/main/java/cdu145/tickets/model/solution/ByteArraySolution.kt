package cdu145.tickets.model.solution

import cdu145.tickets.domain.solution.Solution
import cdu145.tickets.domain.solution.sign.SolutionSign

class ByteArraySolution(
    private val array: ByteArray,
) : Solution {

    override fun signAt(position: Int): SolutionSign {
        return SolutionSign.values()[array[position].toInt()]
    }
}