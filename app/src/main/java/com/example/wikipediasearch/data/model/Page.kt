package com.example.wikipediasearch.data.model

data class Page(
    val index: Int,
    val ns: Int,
    val pageid: Int,
    val terms: Terms,
    val thumbnail: Thumbnail,
    val title: String
)