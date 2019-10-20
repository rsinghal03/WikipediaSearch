package com.example.wikipediasearch.ui.searchQueryResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wikipediasearch.R
import com.example.wikipediasearch.WikipediaSearchApplication
import com.example.wikipediasearch.ui.BaseFragment
import javax.inject.Inject

class SearchQueryResultFragment: BaseFragment(), SearchQueryResultContract.View {

    override fun updateSearchQueryResult() {

    }

    @Inject
    lateinit var searchQueryResultPresenter: SearchQueryResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WikipediaSearchApplication.getInstance().wikiMediaComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return LayoutInflater.from(inflater.context).inflate(R.layout.search_result_query_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchQueryResultPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchQueryResultPresenter.detachView()
    }

    companion object {
        val instance = SearchQueryResultFragment()
    }
}