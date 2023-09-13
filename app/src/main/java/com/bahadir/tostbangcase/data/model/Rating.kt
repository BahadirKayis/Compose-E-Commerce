package com.bahadir.tostbangcase.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "rating")
data class Rating(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("count")
    val count: Int,
    @SerializedName("rate")
    val rate: Double,
): Parcelable
