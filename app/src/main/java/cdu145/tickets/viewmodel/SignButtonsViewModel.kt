package cdu145.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import cdu145.model.Actual
import cdu145.tickets.domain.solution.AlteredSolution
import cdu145.tickets.domain.solution.Solution
import cdu145.tickets.domain.solution.result.SolutionResult
import cdu145.tickets.domain.solution.result.isHundred
import cdu145.tickets.domain.solution.sign.SolutionSign

class SignButtonsViewModel(
    private val solution: Actual.Mutable<Solution>,
    private val highlightedSignPosition: Actual<Int>,
    private val solutionResultFlow: Flow<SolutionResult>,
) : ViewModel() {

    private val _shown = MutableStateFlow(value = true)

    val shown: StateFlow<Boolean>
        get() = _shown

    init {
        viewModelScope.launch {
            solutionResultFlow.collect { _shown.value = !it.isHundred }
        }
    }

    fun chooseSign(sign: SolutionSign) {
        if (!shown.value) return

        viewModelScope.launch {
            solution.mutate(
                AlteredSolution(
                    original = solution.value(),
                    targetPosition = highlightedSignPosition.value(),
                    newSign = sign,
                ),
            )
        }
    }
}