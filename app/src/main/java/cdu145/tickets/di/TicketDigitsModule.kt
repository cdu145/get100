package cdu145.tickets.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import cdu145.tickets.domain.digits.digitsOf
import cdu145.tickets.domain.number.TicketNumber
import cdu145.tickets.viewmodel.DigitCardsViewModel
import cdu145.tickets.viewmodel.NextNumberButtonViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import cdu145.tickets.di.TicketNumber as TicketNumberQualifier

val TicketDigitsFlow = StringQualifier("TicketDigitsFlow")

val TicketDigitsModule = module {
    factory(TicketDigitsFlow) { get<Flow<TicketNumber>>(TicketNumberFlow).map(::digitsOf) }
    viewModel { DigitCardsViewModel(get(TicketDigitsFlow)) }
    viewModel {
        NextNumberButtonViewModel(
            actualNumber = get(TicketNumberQualifier),
            nextNumber = get(NextTicketNumber),
            solution = get(Solution),
        )
    }
}