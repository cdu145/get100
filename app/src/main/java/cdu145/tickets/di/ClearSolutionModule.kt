package cdu145.tickets.di

import cdu145.tickets.viewmodel.ClearSolutionButtonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ClearSolutionModule = module {
    viewModel {
        ClearSolutionButtonViewModel(
            get(Solution),
            get(SolutionResultFlow),
        )
    }
}