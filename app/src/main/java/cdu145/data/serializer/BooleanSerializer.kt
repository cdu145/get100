package cdu145.data.serializer

import androidx.datastore.core.Serializer
import java.io.DataInputStream
import java.io.DataOutputStream

class BooleanSerializer(
    override val defaultValue: Boolean,
) : Serializer<Boolean> by DataStreamSerializer(
    defaultValue,
    readValue = DataInputStream::readBoolean,
    writeValue = DataOutputStream::writeBoolean,
)