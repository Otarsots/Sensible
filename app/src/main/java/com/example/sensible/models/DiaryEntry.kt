package com.example.sensible.models

import androidx.room.*

@Entity
data class DiaryEntry(
    @PrimaryKey(autoGenerate = true)
    val entryId: Long,
    val amount: Long,
    val recipeId: Long,
){

}
