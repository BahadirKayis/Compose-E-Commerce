package com.bahadir.tostbangcase.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "firiyaItem")
data class FiriyaItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    @SerializedName("rating")
    val rating: Rating,
    val title: String,
) : Parcelable
