package com.example.wikipediasearch.ui.searchqueryresult

import com.example.wikipediasearch.data.model.Page
import com.example.wikipediasearch.ui.BaseContract

interface SearchQueryResultContract {

    interface View: BaseContract.BaseView {
        fun updateSearchQueryResult(listOfQueryResult: List<Page>?)
    }

    interface Presenter: BaseContract.BasePresenter<View> {
        fun getSearchQueryResult(query: String)

        fun getSearchQueryFromDb(query: String)

        fun cancelQuery()
    }
}