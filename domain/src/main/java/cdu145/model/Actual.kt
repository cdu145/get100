package cdu145.model

interface Actual<out T> {

    suspend fun value(): T

    interface Mutable<T> : Actual<T> {

        suspend fun mutate(newValue: T): T

        suspend fun mutate(mutation: Mutation<T>): T
    }
}