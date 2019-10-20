package com.example.wikipediasearch.data

import com.example.wikipediasearch.data.model.WikiMediaResponse
import io.reactivex.Observable

interface WikiServiceProvider {

    fun getResultForSearchQuery(query: String): Observable<WikiMediaResponse>
}