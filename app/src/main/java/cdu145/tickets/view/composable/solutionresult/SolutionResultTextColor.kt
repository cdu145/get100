package cdu145.tickets.view.composable.solutionresult

import cdu145.tickets.view.composable.solutionresult.SolutionResultViewState.NotSolved
import cdu145.tickets.view.composable.solutionresult.SolutionResultViewState.Solved

enum class SolutionResultTextColor {

    WhenNotSolved,  WhenSolved;

    companion object {

        fun dependingOn(state: SolutionResultViewState): SolutionResultTextColor {
            return when (state) {
                is NotSolved -> WhenNotSolved
                is Solved -> WhenSolved
            }
        }
    }
}