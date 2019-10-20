package com.example.wikipediasearch.data

import android.content.Context
import com.example.wikipediasearch.data.database.WikipediaSearchDatabase
import com.example.wikipediasearch.data.model.WikiMediaResponse
import java.util.concurrent.Executors

class WikipediaSearchDbRepository(private val context: Context) {

    private val diskIoExecutor = Executors.newSingleThreadExecutor()

    private val db by lazy {
        WikipediaSearchDatabase.invoke(context)
    }

    fun insertData(wikiMediaResponse: WikiMediaResponse) {
        diskIoExecutor.execute {
            db.runInTransaction {
                db.getWikipediaSearchDao().insert(wikiMediaResponse)
            }
        }
    }
}