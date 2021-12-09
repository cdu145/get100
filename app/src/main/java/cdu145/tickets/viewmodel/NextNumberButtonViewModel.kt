package cdu145.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import cdu145.model.Actual
import cdu145.tickets.domain.number.TicketNumber
import cdu145.tickets.domain.solution.Solution

class NextNumberButtonViewModel(
    private val actualNumber: Actual.Mutable<TicketNumber>,
    private val nextNumber: Actual<TicketNumber>,
    private val solution: Actual.Mutable<Solution>,
) : ViewModel() {

    fun loadNextNumber() {
        viewModelScope.launch {
            actualNumber.mutate(nextNumber.value())
            solution.mutate(Solution.Empty)
        }
    }
}