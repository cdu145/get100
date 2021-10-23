package cdu145.tickets.ratedialog

import cdu145.ui.state.DialogState
import cdu145.ui.state.DialogState.Hidden

sealed interface RateDialogState {

    val dialogState: DialogState

    object Completed : RateDialogState {

        override val dialogState: DialogState
            get() = Hidden
    }

    data class ToBeShown(
        val solvedTicketCount: Int = 0,
    ) : RateDialogState {

        companion object {

            private const val ticketsToShowDialog = 3
        }

        init {
            require(solvedTicketCount in 0..ticketsToShowDialog)
        }

        override val dialogState: DialogState
            get() = DialogState.shownIf { solvedTicketCount == ticketsToShowDialog }

        fun onTicketSolved(): ToBeShown {
            return if (solvedTicketCount == ticketsToShowDialog) {
                this
            } else {
                ToBeShown(solvedTicketCount + 1)
            }
        }
    }
}