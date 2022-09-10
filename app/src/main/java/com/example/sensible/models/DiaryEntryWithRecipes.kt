package com.example.sensible.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["date", "recipeId"])
data class DiaryEntryRecipeCrossRef (
    val date: Long,
    val recipeId: Long,
    val amount: Long,
        )

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

