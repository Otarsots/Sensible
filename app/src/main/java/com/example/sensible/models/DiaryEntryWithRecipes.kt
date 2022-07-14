package com.example.sensible.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["date", "recipeId"])
data class DiaryEntryRecipeCrossRef (
    val date: Long,
    val recipeId: Long
        )

data class  DiaryEntryWithRecipes (
    @Embedded val diaryEntry: DiaryEntry,
    @Relation(
        parentColumn = "date",
        entityColumn = "recipeId",
        associateBy = Junction(DiaryEntryRecipeCrossRef::class)
    )
    val recipeList: List<Recipe>
        )