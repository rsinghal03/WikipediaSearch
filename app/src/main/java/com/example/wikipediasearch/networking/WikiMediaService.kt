package com.example.wikipediasearch.networking

import com.example.wikipediasearch.data.model.WikiMediaResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiMediaService {

    @GET("/w/api.php")
    fun getWikiMediaResponse(
        @Query("action") action: String,
        @Query("format") format: String,
        @Query("prop") prop: String,
        @Query("generator") generator: String,
        @Query("redirects") redirects: String,
        @Query("formatversion") formatversion: String,
        @Query("piprop") piprop: String,
        @Query("pithumbsize") pithumbsize: String,
        @Query("pilimit") pilimit: String,
        @Query("wbptterms") wbptterms: String,
        @Query("gpssearch") gpssearch: String,
        @Query("gpslimit") gpslimit: String): Observable<WikiMediaResponse>

}