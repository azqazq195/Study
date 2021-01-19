package com.project.mvvmexampleapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(@PrimaryKey val name: String, @NonNull val meaning: String) {
}