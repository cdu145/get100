package cdu145.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import cdu145.model.Actual
import cdu145.tickets.domain.solution.result.SolutionResult
import cdu145.tickets.domain.solution.result.isHundred
import cdu145.tickets.domain.solution.Solution

class ClearSolutionButtonViewModel(
    private val solution: Actual.Mutable<Solution>,
    solutionResultFlow: Flow<SolutionResult>,
) : ViewModel() {

    private val _shown = MutableStateFlow(true)

    val shown: StateFlow<Boolean>
        get() = _shown

    init {
        solutionResultFlow
            .onEach { _shown.value = !it.isHundred }
            .launchIn(viewModelScope)
    }

    fun clearSolution() {
        if (!shown.value) return

        viewModelScope.launch { solution.mutate(Solution.Empty) }
    }
}