package cdu145.tickets.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import cdu145.tickets.viewmodel.TicketViewModel

val TicketModule = module {
    viewModel {
        TicketViewModel(
            get(TicketDigitsFlow),
        )
    }
}