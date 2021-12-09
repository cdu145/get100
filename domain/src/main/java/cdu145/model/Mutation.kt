package cdu145.model

fun interface Mutation<T> {

    suspend fun apply(actualValue: T): T
}