package com.example.wikipediasearch.ui.searchQueryResult

import com.example.wikipediasearch.ui.BaseContract

interface SearchQueryResultContract {

    interface View: BaseContract.BaseView {
        fun updateSearchQueryResult()
    }

    interface Presenter: BaseContract.BasePresenter<View> {
        fun getSearchQueryResult()
    }
}