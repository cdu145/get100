package cdu145.util

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

internal object CorruptedInputStream : InputStream() {

    override fun read(): Int {
        throw IOException()
    }
}

internal object CorruptedOutputStream : OutputStream() {

    override fun write(b: Int) {
        throw IOException()
    }
}