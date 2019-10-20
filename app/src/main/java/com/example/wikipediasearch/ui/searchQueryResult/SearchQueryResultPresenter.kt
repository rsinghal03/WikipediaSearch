package com.example.wikipediasearch.ui.searchQueryResult

import com.example.wikipediasearch.data.WikiServiceProvider
import io.reactivex.disposables.CompositeDisposable

class SearchQueryResultPresenter(val wikiServiceProvider: WikiServiceProvider): SearchQueryResultContract.Presenter{

    override var view: SearchQueryResultContract.View? = null

    override var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getSearchQueryResult() {
        view?.showLoading()

    }

}