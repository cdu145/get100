package cdu145.tickets.model.ticketnumber

import android.content.Context
import cdu145.tickets.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import cdu145.model.Actual
import cdu145.tickets.domain.number.TicketNumber
import java.io.DataInputStream
import java.io.InputStream
import kotlin.random.Random

class RandomGoodNumberFromRes(
    private val context: Context,
) : Actual<TicketNumber> {

    private fun InputStream.readNextInt(): Int = DataInputStream(this).readInt()

    private fun InputStream.skipInts(count: Int): InputStream {
        skip((Int.SIZE_BYTES * count).toLong())
        return this
    }

    override suspend fun value(): TicketNumber {
        return withContext(Dispatchers.IO) {
            context.resources.openRawResource(R.raw.good_numbers).use { inputStream ->
                val count = inputStream.readNextInt()
                val randomPosition = Random.nextInt(from = 0, until = count)
                TicketNumber(
                    value = inputStream.skipInts(randomPosition).readNextInt(),
                )
            }
        }
    }
}