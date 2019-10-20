package com.example.wikipediasearch

import android.app.Application
import com.example.wikipediasearch.dependencyinjection.DaggerWikiMediaComponent
import com.example.wikipediasearch.dependencyinjection.WikiMediaComponent

class WikipediaSearchApplication : Application() {

    lateinit var wikiMediaComponent: WikiMediaComponent

    init {
        initDagger()
    }

    private fun initDagger() {
        wikiMediaComponent = DaggerWikiMediaComponent.create()
    }


    companion object {

        private var instance: WikipediaSearchApplication? = null

        fun getInstance(): WikipediaSearchApplication =
            instance ?: WikipediaSearchApplication().also {
                instance = it
            }
    }
}