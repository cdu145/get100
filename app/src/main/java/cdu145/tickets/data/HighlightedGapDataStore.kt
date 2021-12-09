package cdu145.tickets.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import cdu145.data.serializer.IntSerializer

val Context.highlightedGapDataStore: DataStore<Int>
        by dataStore(
            fileName = "highlighted_position.bin",
            serializer = IntSerializer(defaultValue = 0),
        )