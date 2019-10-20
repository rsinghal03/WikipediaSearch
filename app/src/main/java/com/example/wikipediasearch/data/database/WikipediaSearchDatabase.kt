package com.example.wikipediasearch.data.database

import android.content.Context
import androidx.room.*
import com.example.wikipediasearch.data.model.WikiMediaResponse

@Database(
    entities = [WikiMediaResponse::class],
    version = 1,
    exportSchema = false
)
abstract class WikipediaSearchDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: WikipediaSearchDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            WikipediaSearchDatabase::class.java, "Wikipedia.db"
        )
            .build()
    }

    abstract fun getWikipediaSearchDao(): WikipediaSearchDao

}