package com.example.wikipediasearch

import android.app.Application
import com.facebook.stetho.Stetho

class WikipediaSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerInit.getInstance(this)
        Stetho.initializeWithDefaults(this)
    }

    companion object {

        private var instance: WikipediaSearchApplication? = null

        fun getInstance(): WikipediaSearchApplication =
            instance ?: WikipediaSearchApplication().also {
                instance = it
            }
    }
}