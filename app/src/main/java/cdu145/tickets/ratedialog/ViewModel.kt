package cdu145.tickets.ratedialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdu145.actual.Actual
import cdu145.actual.Mutation
import cdu145.tickets.ratedialog.RateDialogState.*
import cdu145.tickets.solution.result.SolutionResult
import cdu145.tickets.solution.result.isHundred
import cdu145.ui.state.DialogState
import cdu145.ui.state.DialogState.Hidden
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RateDialogViewModel(
    private val actualState: Actual.Mutable<RateDialogState>,
    solutionResultFlow: Flow<SolutionResult>,
) : ViewModel() {

    private val _state = MutableStateFlow(Hidden)

    val state: StateFlow<DialogState>
        get() = _state

    init {
        solutionResultFlow
            .transform<SolutionResult, Unit> { result ->
                mutateState {
                    when (it) {
                        is ToBeShown -> if (result.isHundred) it.onTicketSolved() else it
                        is Completed -> throw CancellationException("Completed")
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun complete() {
        viewModelScope.launch {
            mutateState { Completed }
        }
    }

    private suspend fun mutateState(mutation: Mutation<RateDialogState>) {
        val newState = actualState.mutate(mutation)
        _state.value = newState.dialogState
    }
}