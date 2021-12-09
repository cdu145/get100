package cdu145.tickets.data

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import cdu145.tickets.domain.number.TicketNumber
import cdu145.tickets.domain.number.ZeroTicketNumber
import java.io.*

@Suppress("BlockingMethodInNonBlockingContext")
val Context.ticketNumberDataStore: DataStore<TicketNumber>
        by dataStore(
            fileName = "ticket_number.bin",
            serializer = object : Serializer<TicketNumber> {

                override val defaultValue: TicketNumber
                    get() = ZeroTicketNumber

                override suspend fun readFrom(input: InputStream): TicketNumber {
                    return try {
                        TicketNumber(
                            value = DataInputStream(input).readInt(),
                        )
                    } catch (e: IOException) {
                        throw CorruptionException("Cannot read ticket number.", e)
                    }
                }

                override suspend fun writeTo(t: TicketNumber, output: OutputStream) {
                    try {
                        DataOutputStream(output).writeInt(t.value)
                    } catch (e: IOException) {
                        throw CorruptionException("Cannot write ticket number.", e)
                    }
                }
            }
        )
