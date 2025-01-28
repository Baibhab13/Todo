package com.example.todo.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TodoConverters {

    /*here it will give string from list of boolean
    * this one is for insert the data*/
    @TypeConverter
    fun fromBooleanList(value: List<Boolean>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    /*here it will give list of boolean from a string
    * this one will for load the data*/
    @TypeConverter
    fun toBooleanList(value: String): List<Boolean> {
        val listType = object : TypeToken<List<Boolean>>() {}.type
        return Gson().fromJson(value, listType)
    }
}