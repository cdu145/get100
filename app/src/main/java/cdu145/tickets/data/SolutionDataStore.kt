package cdu145.tickets.data

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import cdu145.tickets.domain.solution.sign.SolutionSign
import cdu145.tickets.domain.solution.Solution
import cdu145.tickets.domain.solution.sign.SolutionSign.*
import cdu145.tickets.model.solution.ByteArraySolution
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

val Context.solutionDataStore: DataStore<Solution>
        by dataStore(
            fileName = "solution_signs.bin",
            serializer = object : Serializer<Solution> {

                override val defaultValue: Solution
                    get() = Solution.Empty

                override suspend fun readFrom(input: InputStream): Solution {
                    return try {
                        ByteArraySolution(input.readBytes())
                    } catch (e: IOException) {
                        throw CorruptionException("Cannot read solution signs.", e)
                    }
                }

                private val SolutionSign.byteOrdinal: Int
                    get() = when (this) {
                        Plus -> 0
                        Minus -> 1
                        Times -> 2
                        Div -> 3
                        None -> 4
                    }

                @Suppress("BlockingMethodInNonBlockingContext")
                override suspend fun writeTo(t: Solution, output: OutputStream) {
                    try {
                        repeat(5) { i ->
                            output.write(t.signAt(i).byteOrdinal)
                        }
                    } catch (e: IOException) {
                        throw CorruptionException("Cannot write solution signs.", e)
                    }
                }
            }
        )