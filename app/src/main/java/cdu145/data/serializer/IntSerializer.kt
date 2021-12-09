package cdu145.data.serializer

import androidx.datastore.core.Serializer
import java.io.DataInputStream
import java.io.DataOutputStream

class IntSerializer(
    override val defaultValue: Int = 0,
) : Serializer<Int> by DataStreamSerializer(
    defaultValue,
    readValue = DataInputStream::readInt,
    writeValue = DataOutputStream::writeInt,
)