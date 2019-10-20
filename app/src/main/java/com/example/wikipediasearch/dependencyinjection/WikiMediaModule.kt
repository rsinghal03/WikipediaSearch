    package com.example.wikipediasearch.dependencyinjection

import android.content.Context
import com.example.wikipediasearch.ServiceLocator
import com.example.wikipediasearch.ServiceLocatorImpl
import com.example.wikipediasearch.data.WikiWebServiceProviderImpl
import com.example.wikipediasearch.data.WikiWebServiceProvider
import com.example.wikipediasearch.networking.WikiMediaApiClient
import com.example.wikipediasearch.ui.searchQueryResult.SearchQueryResultPresenter
import dagger.Module
import dagger.Provides

@Module
class WikiMediaModule(private val context: Context) {

    @Provides
    fun provideWikiMediaApiClient(): WikiMediaApiClient {
        return WikiMediaApiClient()
    }

    @Provides
    fun provideSearchQueryResultPresenter(serviceLocator: ServiceLocator): SearchQueryResultPresenter {
        return SearchQueryResultPresenter(serviceLocator)
    }

    @Provides
    fun provideWikiServiceProvider(wikiMediaApiClient: WikiMediaApiClient): WikiWebServiceProvider {
        return WikiWebServiceProviderImpl(wikiMediaApiClient)
    }

    @Provides
    fun providesServiceLocatorImpl(context: Context, wikiMediaApiClient: WikiMediaApiClient): ServiceLocator {
        return ServiceLocatorImpl(context, wikiMediaApiClient)
    }

    @Provides
    fun provideContext(): Context {
        return context
    }

}