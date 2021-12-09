package cdu145.tickets.domain.solution.sign.replacement

internal val Iterable<SignReplacement>.totalComplexity: Int
    get() = fold(initial = 0) { acc, repl -> acc + repl.complexity }