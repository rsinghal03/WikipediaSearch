package com.example.wikipediasearch.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wikipediasearch.data.model.WikiMediaResponse

@Dao
interface WikipediaSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wikiMediaResponse: WikiMediaResponse)

    @Query("select * from wikipediasearch where userQuery like :query")
    fun getSearchQueryResult(query: String): LiveData<WikiMediaResponse>

}