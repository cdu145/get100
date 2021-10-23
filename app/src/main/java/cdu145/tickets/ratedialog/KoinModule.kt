package cdu145.tickets.ratedialog

import android.content.Context
import cdu145.actual.DataStoreActual
import cdu145.tickets.solution.result.SolutionResultFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val RateDialogModule = module {
    factory {
        AppMarketPage(
            context = get(),
        )
    }

    viewModel {
        RateDialogViewModel(
            actualState = DataStoreActual(get<Context>().rateDialogStateDataStore),
            get(SolutionResultFlow),
        )
    }
}