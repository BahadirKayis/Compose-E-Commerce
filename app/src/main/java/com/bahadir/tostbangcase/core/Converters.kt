package com.bahadir.tostbangcase.core

import androidx.room.TypeConverter
import com.bahadir.tostbangcase.data.model.Rating
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromRating(item: List<FiriyaUI>): String {
        return Gson().toJson(item)
    }

    @TypeConverter
    fun toIngredient(item: String): List<FiriyaUI> {
        val objectType = object : TypeToken<List<FiriyaUI>>() {}.type
        return Gson().fromJson(item, objectType)
    }
}
