package cdu145.tickets.model.solution

import cdu145.tickets.domain.number.TicketNumber
import cdu145.tickets.domain.solution.correct.CorrectSolutions
import cdu145.tickets.domain.solution.correct.correctSolutions
import cdu145.tickets.domain.solution.Solution
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("FunctionName")
fun DeferredCorrectSolutionsFlow(
    numberFlow: Flow<TicketNumber>,
    allPossibleSolutions: Deferred<Iterable<Solution>>,
    scope: CoroutineScope,
): Flow<Deferred<CorrectSolutions>> {
    return numberFlow.map {
        scope.async { it.correctSolutions(allPossibleSolutions.await()) }
    }
}