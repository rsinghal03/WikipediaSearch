package com.example.wikipediasearch.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.wikipediasearch.data.database.WikipediaTypeConverter

@Entity(tableName = "WikipediaSearch")
@TypeConverters(WikipediaTypeConverter::class)
data class WikiMediaResponse(
    var batchcomplete: Boolean = false,
    @Embedded
    var `continue`: Continue? = null,
    @Embedded
    var query: Query? = null,
    @PrimaryKey
    var userQuery: String = ""
)