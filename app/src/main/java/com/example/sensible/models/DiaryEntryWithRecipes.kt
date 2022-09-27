package com.example.sensible.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiaryEntry (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Long,
    val recipeId: Long,
    val amount: Long = 100,
        )

data class ListItem(
    val id: Long,
    val name: String,
    val amount: Long,
    val calories: Double
)
/*
data class DiaryEntry (
    val recipe: Recipe,
    val amount: Long,
        )

data class  DayWithDiaryEntries (
    @Embedded val day: Day,
    @Relation(
        parentColumn = "date",
        entityColumn = "recipeId",
        associateBy = Junction(DiaryEntryRecipeCrossRef::class)
    )
    val entries: List<DiaryEntry>
        )
 */

