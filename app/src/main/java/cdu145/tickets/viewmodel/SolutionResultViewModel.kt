package cdu145.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import cdu145.model.Loadable
import cdu145.model.Loadable.NotReady
import cdu145.model.Loadable.Ready
import cdu145.tickets.domain.solution.result.SolutionResult
import cdu145.tickets.view.composable.solutionresult.SolutionResultViewState

class SolutionResultViewModel(
    resultFlow: Flow<SolutionResult>,
) : ViewModel() {

    private val _state = MutableStateFlow<Loadable<SolutionResultViewState>>(NotReady)

    val state: StateFlow<Loadable<SolutionResultViewState>>
        get() = _state

    init {
        resultFlow
            .onEach { _state.value = Ready(SolutionResultViewState.createFrom(it)) }
            .launchIn(viewModelScope)
    }
}