package com.example.wikipediasearch.data.model

data class WikiMediaResponse(
    val batchcomplete: Boolean,
    val `continue`: Continue,
    val query: Query?
)