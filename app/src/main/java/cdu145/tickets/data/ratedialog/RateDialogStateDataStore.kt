package cdu145.tickets.data.ratedialog

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import cdu145.tickets.model.ratedialog.RateDialogState

val Context.rateDialogStateDataStore: DataStore<RateDialogState>
        by dataStore(
            fileName = "rate_dialog_state.bin",
            RateDialogStateSerializer,
        )