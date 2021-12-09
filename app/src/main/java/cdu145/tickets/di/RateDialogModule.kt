package cdu145.tickets.di

import android.content.Context
import cdu145.model.DataStoreActual
import cdu145.tickets.data.ratedialog.rateDialogStateDataStore
import cdu145.tickets.model.ratedialog.AppMarketPage
import cdu145.tickets.viewmodel.RateDialogViewModel
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