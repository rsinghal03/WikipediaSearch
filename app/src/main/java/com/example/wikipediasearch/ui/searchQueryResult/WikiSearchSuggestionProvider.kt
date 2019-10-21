package com.example.wikipediasearch.ui.searchqueryresult

import android.content.SearchRecentSuggestionsProvider

class WikiSearchSuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY =
            "com.example.wikipediasearch.ui.searchqueryresult.WikiSearchSuggestionProvider"
        const val MODE: Int = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
    }
}