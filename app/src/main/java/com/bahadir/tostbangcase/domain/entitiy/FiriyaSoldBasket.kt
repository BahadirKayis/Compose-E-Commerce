package com.bahadir.tostbangcase.domain.entitiy

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "firiyaSoldBasket")
@Parcelize
data class FiriyaSoldBasket(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val date: String,
    val firiyaItem: List<FiriyaUI>,
) : Parcelable
