package com.example.wikipediasearch.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.wikipediasearch.data.model.WikiMediaResponse

@Dao
interface WikipediaSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wikiMediaResponse: WikiMediaResponse)

}