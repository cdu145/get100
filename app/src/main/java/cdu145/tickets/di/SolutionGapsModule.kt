package cdu145.tickets.di

import android.content.Context
import cdu145.model.Actual
import cdu145.model.DataStoreActual
import cdu145.tickets.data.highlightedGapDataStore
import cdu145.tickets.viewmodel.SolutionGapsViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val HighlightedGapPosition = StringQualifier("HighlightedGapPosition")

val SolutionGapsModule = module {
    factory<Actual<Int>>(HighlightedGapPosition) {
        DataStoreActual(get<Context>().highlightedGapDataStore)
    } bind Actual.Mutable::class
    viewModel {
        SolutionGapsViewModel(
            get(HighlightedGapPosition),
            highlightedPositionFlow = get<Context>().highlightedGapDataStore.data,
            get(SolutionFlow),
            get(SolutionResultFlow),
            justRevealedGapFlow = get<Channel<Int>>(JustRevealedGapChannel).receiveAsFlow(),
        )
    }
}