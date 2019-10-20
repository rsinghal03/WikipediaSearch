package com.example.wikipediasearch

import android.content.Context
import com.example.wikipediasearch.data.WikipediaSearchDbRepository
import com.example.wikipediasearch.data.WikiWebServiceProviderImpl
import com.example.wikipediasearch.networking.WikiMediaApiClient

interface ServiceLocator {

    fun getWikiWebServiceProvider(): WikiWebServiceProviderImpl

    fun getWikiDbServiceProvider(): WikipediaSearchDbRepository
}


class ServiceLocatorImpl(private val context: Context, private val wikiMediaApiClient: WikiMediaApiClient) : ServiceLocator {

    override fun getWikiWebServiceProvider(): WikiWebServiceProviderImpl {
        return WikiWebServiceProviderImpl(wikiMediaApiClient)
    }

    override fun getWikiDbServiceProvider(): WikipediaSearchDbRepository {
        return WikipediaSearchDbRepository(context)
    }
}
