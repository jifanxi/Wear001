package com.j.wear001.presentation.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var age: Int
)