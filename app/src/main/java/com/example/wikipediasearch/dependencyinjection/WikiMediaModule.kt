package com.example.wikipediasearch.dependencyinjection

import com.example.wikipediasearch.networking.WikiMediaApiClient
import dagger.Module
import dagger.Provides

@Module
class WikiMediaModule {

    @Provides
    fun provideWikiMediaApiClient(): WikiMediaApiClient {
        return WikiMediaApiClient()
    }
}