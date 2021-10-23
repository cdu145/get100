package cdu145.tickets.ratedialog

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import cdu145.tickets.ratedialog.RateDialogState.*
import java.io.*
import java.lang.RuntimeException

@Suppress("BlockingMethodInNonBlockingContext")
object RateDialogStateSerializer : Serializer<RateDialogState> {

    override val defaultValue: RateDialogState
        get() = ToBeShown()

    override suspend fun readFrom(input: InputStream): RateDialogState {
        return try {
            DataInputStream(input).use { dataInput ->
                when (val typeCode = dataInput.readInt()) {
                    0 -> Completed
                    1 -> ToBeShown(
                        solvedTicketCount = dataInput.readInt(),
                    )
                    else -> throw UnknownTypeCodeException(typeCode)
                }
            }
        } catch (e: Exception) {
            throw CorruptionException("Cannot read RateDialogState value.", e)
        }
    }

    override suspend fun writeTo(t: RateDialogState, output: OutputStream) {
        try {
            DataOutputStream(output).use { dataOutput ->
                when (t) {
                    is Completed -> dataOutput.writeInt(0)
                    is ToBeShown -> dataOutput.run { writeInt(1); writeInt(t.solvedTicketCount) }
                }
            }
        } catch (e: IOException) {
            throw CorruptionException("Cannot write RateDialogState value.", e)
        }
    }
}

private class UnknownTypeCodeException(typeCode: Int) :
    RuntimeException("Unknown RateDialogState type code: $typeCode")