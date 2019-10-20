package com.example.wikipediasearch.data.model

import androidx.room.Embedded

data class Page(
    val index: Int,
    val ns: Int,
    val pageid: Int,
    @Embedded
    val terms: Terms?,
    @Embedded
    val thumbnail: Thumbnail?,
    val title: String
)