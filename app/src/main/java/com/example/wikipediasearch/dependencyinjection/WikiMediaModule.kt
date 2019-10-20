package com.example.wikipediasearch.dependencyinjection

import com.example.wikipediasearch.data.WikiMediaServiceProviderImpl
import com.example.wikipediasearch.data.WikiServiceProvider
import com.example.wikipediasearch.networking.WikiMediaApiClient
import com.example.wikipediasearch.ui.searchQueryResult.SearchQueryResultPresenter
import dagger.Module
import dagger.Provides

@Module
class WikiMediaModule {

    @Provides
    fun provideWikiMediaApiClient(): WikiMediaApiClient {
        return WikiMediaApiClient()
    }

    @Provides
    fun provideSearchQueryResultPresenter(wikiServiceProvider: WikiServiceProvider): SearchQueryResultPresenter {
        return SearchQueryResultPresenter(wikiServiceProvider)
    }

    @Provides
    fun provideWikiServiceProvider(wikiMediaApiClient: WikiMediaApiClient): WikiServiceProvider {
        return WikiMediaServiceProviderImpl(wikiMediaApiClient)
    }
}