package com.example.sensible.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["date", "entryId"])
data class DiaryEntryRecipeCrossRef (
    val date: Long,
    val entryId: Long
        )

data class  DayWithDiaryEntries (
    @Embedded val day: Day,
    @Relation(
        parentColumn = "date",
        entityColumn = "entryId",
        associateBy = Junction(DiaryEntryRecipeCrossRef::class)
    )
    val entries: entry
        )

