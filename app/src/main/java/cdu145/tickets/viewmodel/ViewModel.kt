package cdu145.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdu145.model.Actual
import cdu145.model.DialogState
import cdu145.model.DialogState.Hidden
import cdu145.model.Mutation
import cdu145.tickets.domain.solution.result.SolutionResult
import cdu145.tickets.domain.solution.result.isHundred
import cdu145.tickets.model.ratedialog.RateDialogState
import cdu145.tickets.model.ratedialog.RateDialogState.Completed
import cdu145.tickets.model.ratedialog.RateDialogState.ToBeShown
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