package com.bahadir.tostbangcase.domain.entitiy

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "firiyaItem")
@Parcelize
data class FiriyaUI(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    var price: String,
    val title: String,
):Parcelable
