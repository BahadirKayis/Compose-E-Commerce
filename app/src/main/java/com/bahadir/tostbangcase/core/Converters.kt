package com.bahadir.tostbangcase.core

import androidx.room.TypeConverter
import com.bahadir.tostbangcase.data.model.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromRating(item: Rating): String {
        return Gson().toJson(item)
    }

    @TypeConverter
    fun toIngredient(item: String): Rating {
        val objectType = object : TypeToken<Rating>() {}.type
        return Gson().fromJson(item, objectType)
    }
}
