package cdu145.tickets.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.plus
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import cdu145.model.Actual
import cdu145.tickets.domain.solution.correct.CorrectSolutions
import cdu145.tickets.model.solution.ActualCorrectSolutions
import cdu145.tickets.model.solution.DeferredCorrectSolutionsFlow

val CorrectSolutions = StringQualifier("CorrectSolutions")

val CorrectSolutionsModule = module {
    val flow = StringQualifier("correctSolutionsFlow")
    single<Flow<*>>(flow, createdAtStart = true) {
        val applicationScope = get<CoroutineScope>(ApplicationCoroutineScope)
        DeferredCorrectSolutionsFlow(
            get(TicketNumberFlow),
            get(AllPossibleSolutions),
            scope = applicationScope + Dispatchers.Default,
        ).shareIn(applicationScope, started = SharingStarted.Eagerly, replay = 1)
    }

    factory<Actual<CorrectSolutions>>(CorrectSolutions) { ActualCorrectSolutions(get(flow)) }
}