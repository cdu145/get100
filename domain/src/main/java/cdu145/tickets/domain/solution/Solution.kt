package cdu145.tickets.domain.solution

import cdu145.tickets.domain.solution.sign.SolutionSign

interface Solution {

    fun signAt(position: Int): SolutionSign

    companion object {

        val Empty: Solution = object : Solution {

            override fun signAt(position: Int): SolutionSign = SolutionSign.None
        }
    }
}

fun Solution.asIterable(): Iterable<SolutionSign> = List(size = 5) { i -> this.signAt(i) }