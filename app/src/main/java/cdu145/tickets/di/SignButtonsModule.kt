package cdu145.tickets.di

import cdu145.tickets.viewmodel.SignButtonsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val SignButtonsModule = module {
    viewModel {
        SignButtonsViewModel(
            get(Solution),
            get(HighlightedGapPosition),
            get(SolutionResultFlow),
        )
    }
}