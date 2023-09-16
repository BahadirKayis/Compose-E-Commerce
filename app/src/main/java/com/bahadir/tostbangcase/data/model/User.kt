package com.bahadir.tostbangcase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
)
