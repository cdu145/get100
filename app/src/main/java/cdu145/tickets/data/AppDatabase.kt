package cdu145.tickets.data

import androidx.room.Database
import androidx.room.RoomDatabase
import cdu145.tickets.model.hint.RestoringHint

@Database(entities = [RestoringHint::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val restoringHintsDao: RestoringHintsDao
}