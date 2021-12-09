package cdu145.data.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.*

class DataStreamSerializer<T>(
    override val defaultValue: T,
    private val readValue: DataInputStream.() -> T,
    private val writeValue: DataOutputStream.(T) -> Unit,
) : Serializer<T> {

    override suspend fun readFrom(input: InputStream): T {
        return try {
            DataInputStream(input).readValue()
        } catch (e: IOException) {
            throw CorruptionException("Cannot read value.", e)
        }
    }

    override suspend fun writeTo(t: T, output: OutputStream) {
        try {
            DataOutputStream(output).writeValue(t)
        } catch (e: IOException) {
            throw CorruptionException("Cannot read value.", e)
        }
    }
}