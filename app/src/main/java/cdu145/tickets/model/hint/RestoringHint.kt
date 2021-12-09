package cdu145.tickets.model.hint

import androidx.room.Entity
import androidx.room.PrimaryKey

typealias HintUseTimestamp = Long

@Entity(tableName = "restoring_hints")
data class RestoringHint(
    @PrimaryKey
    val usedAt: HintUseTimestamp = System.currentTimeMillis(),
)