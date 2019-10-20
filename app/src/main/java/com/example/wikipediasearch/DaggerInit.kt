package com.example.wikipediasearch

import android.content.Context
import com.example.wikipediasearch.dependencyinjection.DaggerWikiMediaComponent
import com.example.wikipediasearch.dependencyinjection.WikiMediaComponent
import com.example.wikipediasearch.dependencyinjection.WikiMediaModule

class DaggerInit private constructor(context: Context) {

    lateinit var wikiMediaComponent: WikiMediaComponent

    init {
        initDagger(context)
    }

    private fun initDagger(context: Context) {
        wikiMediaComponent = DaggerWikiMediaComponent
            .builder()
            .wikiMediaModule(WikiMediaModule(context))
            .build()
    }

    companion object {
        var instance: DaggerInit? = null

        fun getInstance(context: Context): DaggerInit =
            instance ?: DaggerInit(context).also {
                instance = it
            }
    }
}