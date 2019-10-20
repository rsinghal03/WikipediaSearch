package com.example.wikipediasearch.ui.searchQueryResult

import androidx.lifecycle.LiveData
import com.example.wikipediasearch.data.model.Page
import com.example.wikipediasearch.data.model.WikiMediaResponse
import com.example.wikipediasearch.ui.BaseContract

interface SearchQueryResultContract {

    interface View: BaseContract.BaseView {
        fun updateSearchQueryResult(listOfQueryResult: List<Page>?)
    }

    interface Presenter: BaseContract.BasePresenter<View> {
        fun getSearchQueryResult(query: String)

        fun getSearchQueryFromDb(query: String): LiveData<WikiMediaResponse>
    }
}