package cdu145.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import cdu145.model.Actual
import cdu145.model.DialogState
import cdu145.model.DialogState.Hidden
import cdu145.tickets.di.PageCount
import cdu145.tickets.model.guide.Guide
import cdu145.tickets.view.composable.guide.NextPageButtonState
import cdu145.tickets.view.composable.guide.PrevPageButtonState

typealias PagePosition = Int

class GuideDialogViewModel(
    private val state: MutableStateFlow<DialogState>,
    private val guideCompletedFlag: Actual.Mutable<Boolean>,
    private val pageCount: Int = Guide.scope.get(PageCount),
) : ViewModel() {

    private val _pagePosition = MutableStateFlow(0)
    val pagePosition: StateFlow<PagePosition>
        get() = _pagePosition

    private val _nextButtonState = MutableStateFlow<NextPageButtonState>(
        NextPageButtonState.ShowNext(1),
    )
    val nextButtonState: StateFlow<NextPageButtonState>
        get() = _nextButtonState

    private val _prevButtonState = MutableStateFlow(PrevPageButtonState.Hidden)
    val prevButtonState: StateFlow<PrevPageButtonState>
        get() = _prevButtonState

    fun dismiss() {
        state.value = Hidden
    }

    fun showNextPage() {
        updatePagePosition(pagePosition.value + 1)
    }

    fun showPrevPage() {
        updatePagePosition(pagePosition.value - 1)
    }

    private fun updatePagePosition(newPosition: PagePosition) {
        requireValid(newPosition)

        _pagePosition.value = newPosition

        _prevButtonState.value = if (newPosition.isFirst) {
            PrevPageButtonState.Hidden
        } else {
            PrevPageButtonState.Shown
        }
        _nextButtonState.value = if (newPosition.isLast) {
            NextPageButtonState.Finish
        } else {
            NextPageButtonState.ShowNext(newPosition + 1)
        }

        if (newPosition == pageCount - 1) {
            viewModelScope.launch { guideCompletedFlag.mutate(true) }
        }
    }

    private fun requireValid(position: PagePosition) {
        require(position in 0 until pageCount) { "Invalid page position: $position" }
    }

    fun finish() {
        check(pagePosition.value.isLast)
        dismiss()
    }

    private val PagePosition.isFirst: Boolean
        get() = this == 0

    private val Int.isLast: Boolean
        get() = this == pageCount - 1
}