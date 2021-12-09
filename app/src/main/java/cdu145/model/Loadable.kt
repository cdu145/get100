package cdu145.model

sealed interface Loadable<out T> {

    object NotReady : Loadable<Nothing>

    data class Ready<T>(val value: T) : Loadable<T>
}