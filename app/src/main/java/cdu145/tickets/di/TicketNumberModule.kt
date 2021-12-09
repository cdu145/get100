package cdu145.tickets.di

import android.content.Context
import androidx.datastore.core.DataStore
import cdu145.model.Actual
import cdu145.model.DataStoreActual
import cdu145.tickets.data.ticketNumberDataStore
import cdu145.tickets.domain.number.TicketNumber
import cdu145.tickets.model.ticketnumber.EnsureTicketNumberCreated
import cdu145.tickets.model.ticketnumber.RandomGoodNumberFromRes
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val TicketNumberFlow = StringQualifier("TicketNumberFlow")
val TicketNumber = StringQualifier("TicketNumberActual")
val TicketNumberDataStore = StringQualifier("TicketNumberDataStore")
val NextTicketNumber = StringQualifier("NextTicketNumber")

val TicketNumberModule = module {
    factory(TicketNumberDataStore) { get<Context>().ticketNumberDataStore }
    factory<Actual<TicketNumber>>(TicketNumber) {
        DataStoreActual(get(TicketNumberDataStore))
    } bind Actual.Mutable::class
    factory(TicketNumberFlow) { get<DataStore<TicketNumber>>(TicketNumberDataStore).data }

    factory<Actual<TicketNumber>>(NextTicketNumber) { RandomGoodNumberFromRes(context = get()) }
    factory {
        EnsureTicketNumberCreated(
            get(TicketNumber),
            get(NextTicketNumber),
            get(ApplicationCoroutineScope),
        )
    }
}