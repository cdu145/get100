package cdu145.tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import cdu145.tickets.domain.digits.TicketDigits
import cdu145.model.Loadable
import cdu145.model.Loadable.NotReady
import cdu145.model.Loadable.Ready

class TicketViewModel(
    digitsFlow: Flow<TicketDigits>,
) : ViewModel() {

    private val _digits = MutableStateFlow<Loadable<TicketDigits>>(NotReady)

    val digits: StateFlow<Loadable<TicketDigits>>
        get() = _digits

    init {
        digitsFlow
            .onEach { _digits.value = Ready(it) }
            .launchIn(viewModelScope)
    }
}