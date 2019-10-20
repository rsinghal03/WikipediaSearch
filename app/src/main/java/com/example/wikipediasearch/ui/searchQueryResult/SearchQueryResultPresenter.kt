package com.example.wikipediasearch.ui.searchQueryResult

import com.example.wikipediasearch.data.WikiServiceProvider
import com.example.wikipediasearch.data.model.WikiMediaResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class SearchQueryResultPresenter(private val wikiServiceProvider: WikiServiceProvider): SearchQueryResultContract.Presenter{

    override var view: SearchQueryResultContract.View? = null

    override var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getSearchQueryResult(query: String) {
        view?.showLoading()
        wikiServiceProvider.getResultForSearchQuery(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({success -> handleSuccess(success)  }, {error ->  })
            .addTo(compositeDisposable)
    }

    private fun handleSuccess(success: WikiMediaResponse) {
        view?.hideLoading()
        view?.updateSearchQueryResult(success.query?.pages)
    }

}