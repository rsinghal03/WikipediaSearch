package com.example.wikipediasearch.data

import com.example.wikipediasearch.data.model.WikiMediaResponse
import com.example.wikipediasearch.networking.WikiMediaApiClient
import com.example.wikipediasearch.networking.WikiMediaService
import io.reactivex.Observable

/*
   * Example url
   * https://en.wikipedia.org//w/api.php?action=query&format=json
   * &prop=pageimages|pageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&
   * pithumbsize=50&pilimit=10&wbptterms=description&gpssearch=rahul&gpslimit=10

 */

class WikiMediaServiceProviderImpl(private val wikiMediaApiClient: WikiMediaApiClient) : WikiServiceProvider{

    override fun getResultForSearchQuery(query: String): Observable<WikiMediaResponse> {
        val wikiMediaService = wikiMediaApiClient.getRetrofit().create(WikiMediaService::class.java)
        return wikiMediaService.getWikiMediaResponse(ACTION, FORMAT, PROP, GENERATOR, REDIRECTS,
            FORMATVERSION, PIPROP, PITHUMBSIZE, PILIMIT, WBPTTERMS, query, GPSLIMIT)
    }

    companion object {
        const val ACTION = "query"
        const val FORMAT = "json"
        const val PROP = "pageimages|pageterms"
        const val GENERATOR = "prefixsearch"
        const val REDIRECTS = "1"
        const val FORMATVERSION = "2"
        const val PIPROP = "thumbnail"
        const val PITHUMBSIZE = "50"
        const val PILIMIT = "10"
        const val WBPTTERMS = "description"
//        const val GPSSEARCH = "1"
        const val GPSLIMIT = "10"
    }
}