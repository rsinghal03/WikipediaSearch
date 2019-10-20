package com.example.wikipediasearch.data.database

import androidx.room.TypeConverter
import com.example.wikipediasearch.data.model.Page
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



object WikipediaTypeConverter {

    @TypeConverter
    @JvmStatic
    fun listOfStirngToString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun stringToListOfString(string: String): List<String> {
        val listType = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    @JvmStatic
    fun listOfPagesToString(list: List<Page>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun stringToListOfPages(string: String): List<Page> {
        val listType = object : TypeToken<List<Page>>(){}.type

        return Gson().fromJson(string, listType)
    }
}