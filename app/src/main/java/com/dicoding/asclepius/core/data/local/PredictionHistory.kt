package com.dicoding.asclepius.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prediction_history")
data class PredictionHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val imagePath: String,
    val result: String,
)

