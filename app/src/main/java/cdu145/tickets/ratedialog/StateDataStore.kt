package cdu145.tickets.ratedialog

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore

val Context.rateDialogStateDataStore: DataStore<RateDialogState>
        by dataStore(
            fileName = "rate_dialog_state.bin",
            RateDialogStateSerializer,
        )