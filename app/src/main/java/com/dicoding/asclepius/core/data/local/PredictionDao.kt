package com.dicoding.asclepius.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PredictionDao {
    @Insert
    suspend fun insertPrediction(prediction: PredictionHistory)

    @Query("SELECT * FROM prediction_history")
    suspend fun getAllPredictions(): List<PredictionHistory>

    @Query("DELETE FROM prediction_history WHERE id = :id")
    suspend fun deletePrediction(id: Long)
}
