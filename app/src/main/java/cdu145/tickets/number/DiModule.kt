package cdu145.tickets.number

import android.content.Context
import kotlinx.coroutines.flow.Flow
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import cdu145.actual.Actual
import cdu145.actual.DataStoreMutable
import cdu145.coroutine.scope.ApplicationCoroutineScope
import cdu145.flow.DataStoreFlow
import cdu145.tickets.number.good.RandomGoodNumberFromRes

val TicketNumberFlow = StringQualifier("TicketNumberFlow")
val ActualTicketNumber = StringQualifier("TicketNumberActual")
val TicketNumberDataStore = StringQualifier("TicketNumberDataStore")
val NextTicketNumber = StringQualifier("NextTicketNumber")

val TicketNumberModule = module {
    factory(TicketNumberDataStore) { get<Context>().ticketNumberDataStore }
    factory<Actual<TicketNumber>>(ActualTicketNumber) {
        DataStoreMutable(get(TicketNumberDataStore))
    } bind Actual.Mutable::class
    factory<Flow<TicketNumber>>(TicketNumberFlow) { DataStoreFlow(get(TicketNumberDataStore)) }

    factory<Actual<TicketNumber>>(NextTicketNumber) { RandomGoodNumberFromRes(context = get()) }
    factory {
        EnsureTicketNumberCreated(
            ticketNumber = get(ActualTicketNumber),
            firstNumber = get(NextTicketNumber),
            scope = get(ApplicationCoroutineScope),
        )
    }
}