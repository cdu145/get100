package cdu145.tickets.model

sealed interface GapPosition {

    object None : GapPosition

    data class Some(val value: Int) : GapPosition
}