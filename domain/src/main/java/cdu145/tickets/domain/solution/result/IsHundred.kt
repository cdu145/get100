package cdu145.tickets.domain.solution.result

import cdu145.tickets.domain.solution.result.SolutionResult.Defined

val SolutionResult.isHundred: Boolean
    get() = (this as? Defined)?.value == 100.0