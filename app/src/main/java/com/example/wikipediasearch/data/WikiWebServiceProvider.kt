package com.example.wikipediasearch.data

import com.example.wikipediasearch.data.model.WikiMediaResponse
import io.reactivex.Observable

interface WikiWebServiceProvider {

    fun getResultForSearchQuery(query: String): Observable<WikiMediaResponse>
}