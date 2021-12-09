package cdu145.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import cdu145.model.Actual
import cdu145.model.Loadable
import cdu145.model.Loadable.NotReady
import cdu145.model.Loadable.Ready
import cdu145.tickets.domain.solution.Solution
import cdu145.tickets.model.GapPosition.None
import cdu145.tickets.model.GapPosition.Some
import cdu145.tickets.domain.solution.result.SolutionResult
import cdu145.tickets.domain.solution.result.isHundred
import cdu145.tickets.model.GapPosition

class SolutionGapsViewModel(
    private val actualHighlightedPosition: Actual.Mutable<Int>,
    highlightedPositionFlow: Flow<Int>,
    solutionFlow: Flow<Solution>,
    solutionResultFlow: Flow<SolutionResult>,
    justRevealedGapFlow: Flow<Int>,
) : ViewModel() {

    private val _highlightedPosition = MutableStateFlow<GapPosition>(None)

    val highlightedPosition: StateFlow<GapPosition>
        get() = _highlightedPosition

    private val _shownSolution = MutableStateFlow<Loadable<Solution>>(NotReady)

    val shownSolution: StateFlow<Loadable<Solution>>
        get() = _shownSolution

    private val _justOpenedPosition = MutableStateFlow<GapPosition>(None)

    val justOpenedPosition: StateFlow<GapPosition>
        get() = _justOpenedPosition

    private var active = false

    init {
        highlightedPositionFlow
            .onEach { _highlightedPosition.value = Some(it) }
            .launchIn(viewModelScope)
        solutionFlow
            .onEach { _shownSolution.value = Ready(it) }
            .launchIn(viewModelScope)
        solutionResultFlow
            .onEach {
                active = !it.isHundred
                _highlightedPosition.value = if (it.isHundred) {
                    None
                } else {
                    Some(actualHighlightedPosition.value())
                }
            }
            .launchIn(viewModelScope)
        justRevealedGapFlow
            .onEach { _justOpenedPosition.value = Some(it) }
            .launchIn(viewModelScope)
    }

    fun highlightGapAt(position: Int) {
        if (active) {
            viewModelScope.launch { actualHighlightedPosition.mutate(position) }
        }
    }
}