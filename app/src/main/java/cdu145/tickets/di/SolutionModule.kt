package cdu145.tickets.di

import android.content.Context
import cdu145.model.Actual
import cdu145.model.DataStoreActual
import cdu145.tickets.data.solutionDataStore
import cdu145.tickets.domain.solution.allPossibleSolutions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val Solution = StringQualifier("Solution")
val SolutionFlow = StringQualifier("SolutionFlow")

val AllPossibleSolutions = StringQualifier("AllPossibleSolutions")

val SolutionModule = module {
    factory<Actual<*>>(Solution) {
        DataStoreActual(get<Context>().solutionDataStore)
    } bind Actual.Mutable::class

    factory(SolutionFlow) { get<Context>().solutionDataStore.data }

    single(AllPossibleSolutions) {
        get<CoroutineScope>(ApplicationCoroutineScope).async(Dispatchers.Default) {
            allPossibleSolutions()
        }
    }
}