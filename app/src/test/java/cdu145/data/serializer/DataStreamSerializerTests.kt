package cdu145.data.serializer

import androidx.datastore.core.CorruptionException
import cdu145.util.CorruptedInputStream
import cdu145.util.CorruptedOutputStream
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

internal class DataStreamSerializerTests {

    private val serializer = DataStreamSerializer(
        defaultValue = 0,
        readValue = DataInputStream::readInt,
        writeValue = DataOutputStream::writeInt,
    )

    @Test
    fun `Should use given readValue to deserialize`() = runBlocking {
        assertThat(
            serializer.readFrom(
                ByteArrayInputStream(
                    byteArrayOf(0x12, 0x34, 0x56, 0x78),
                ),
            ),
            equalTo(0x12345678),
        )
    }

    @Test
    fun `When failed to read should throw CorruptionException`(): Unit = runBlocking {
        assertThrows<CorruptionException> { serializer.readFrom(CorruptedInputStream) }
    }

    @Test
    fun `Should use given writeValue to serialize`() = runBlocking {
        assertThat(
            ByteArrayOutputStream(Int.SIZE_BYTES).use {
                serializer.writeTo(0x12345678, it)
                it.toByteArray().toList()
            },
            equalTo(
                listOf(0x12, 0x34, 0x56, 0x78).map(Int::toByte),
            ),
        )
    }

    @Test
    fun `When failed to write should throw CorruptionException`(): Unit = runBlocking {
        assertThrows<CorruptionException> { serializer.writeTo(0, CorruptedOutputStream) }
    }
}