package com.example.wikipediasearch.ui.searchQueryResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikipediasearch.DaggerInit
import com.example.wikipediasearch.R
import com.example.wikipediasearch.data.model.Page
import com.example.wikipediasearch.extension.replace
import com.example.wikipediasearch.extension.visible
import com.example.wikipediasearch.ui.BaseFragment
import com.example.wikipediasearch.ui.queryresultitemselection.QueryResultItemSelectionFragment
import kotlinx.android.synthetic.main.search_result_query_fragment.*
import javax.inject.Inject

class SearchQueryResultFragment: BaseFragment(), SearchQueryResultContract.View {

    lateinit var searchQueryResultListAdapter: SearchQueryResultListAdapter

    @Inject
    lateinit var searchQueryResultPresenter: SearchQueryResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerInit.instance?.wikiMediaComponent?.inject(this)
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
        getSearchQuery()
        searchQueryResultListAdapter = SearchQueryResultListAdapter(requireContext())
        searchQueryResultListAdapter.onItemClick = {pageId -> replace(QueryResultItemSelectionFragment.getInstance(pageId.toString()), R.id.container, true)}
        recycler_view_id.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recycler_view_id.adapter = searchQueryResultListAdapter
    }

    private fun getSearchQuery() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrEmpty())
                searchQueryResultPresenter.getSearchQueryResult(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    override fun updateSearchQueryResult(listOfQueryResult: List<Page>?) {
        if(!listOfQueryResult.isNullOrEmpty())
        searchQueryResultListAdapter.updateSearchQueryResultList(listOfQueryResult)
        recycler_view_id.visible()
}


    override fun onDestroy() {
        super.onDestroy()
        searchQueryResultPresenter.detachView()
    }

    companion object {
        val instance = SearchQueryResultFragment()
    }
}