package com.dicoding.asclepius.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PredictionHistory::class], version = 1, exportSchema = false)
abstract class PredictionDatabase : RoomDatabase() {

    abstract fun predictionHistoryDao(): PredictionDao

    companion object {
        @Volatile
        private var INSTANCE: PredictionDatabase? = null

        fun getDatabase(context: Context): PredictionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PredictionDatabase::class.java,
                    "prediciton_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
