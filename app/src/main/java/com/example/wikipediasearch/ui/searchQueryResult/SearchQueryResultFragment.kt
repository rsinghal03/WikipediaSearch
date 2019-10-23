package com.example.wikipediasearch.ui.searchqueryresult

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wikipediasearch.DaggerInit
import com.example.wikipediasearch.R
import com.example.wikipediasearch.data.model.WikiMediaResponse
import com.example.wikipediasearch.extension.replace
import com.example.wikipediasearch.extension.visible
import com.example.wikipediasearch.ui.BaseFragment
import com.example.wikipediasearch.ui.MainActivity
import com.example.wikipediasearch.ui.queryresultitemselection.QueryResultItemSelectionFragment
import com.example.wikipediasearch.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.search_result_query_fragment.*
import javax.inject.Inject

private const val TAG = "SearchQueryResult"

class SearchQueryResultFragment : BaseFragment(), SearchQueryResultContract.View {

    private lateinit var searchQueryResultListAdapter: SearchQueryResultListAdapter

    private var wikiMediaResponse: WikiMediaResponse? = null

    @Inject
    lateinit var searchQueryResultPresenter: SearchQueryResultContract.Presenter

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
        Log.i(TAG, "onCreateView")
        return LayoutInflater.from(inflater.context)
            .inflate(R.layout.search_result_query_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")
        searchQueryResultPresenter.attachView(this)
        getSearchQuery()
        setSuggestionListener()
        setSearchQueryListAdapter()
        wikiMediaResponse.let {
            searchQueryResultListAdapter.updateSearchQueryResultList(it)
            recycler_view_id.visible()
        }
        val searchManager = requireContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search_view.setSearchableInfo(searchManager.getSearchableInfo(ComponentName(requireContext(), MainActivity::class.java)))
    }

    private fun setSuggestionListener() {
        search_view.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val adapter = search_view.suggestionsAdapter
                val item = adapter.getItem(position) as Cursor
                val query = item.getString(2)
                triggerSearch(query)
                search_view.setQuery(query, false)
                return true
            }

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG, "onActivityCreated")
    }

    private fun setSearchQueryListAdapter() {
        searchQueryResultListAdapter = SearchQueryResultListAdapter(requireContext())
        searchQueryResultListAdapter.onItemClick = { pageId, userQuery ->
            userQuery?.let { saveToRecentSearchSuggestion(it) }
            replace(
                QueryResultItemSelectionFragment.getInstance(pageId.toString()),
                R.id.container,
                true
            )
        }
        recycler_view_id.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recycler_view_id.adapter = searchQueryResultListAdapter
    }


    private fun getSearchQuery() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    saveToRecentSearchSuggestion(query)
                    triggerSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrEmpty()){
                    triggerSearch(newText)
                } else {
                    searchQueryResultPresenter.cancelQuery()
                    wikiMediaResponse = null
                    wikiMediaResponse.let {
                        searchQueryResultListAdapter.updateSearchQueryResultList(it)
                    }
                }
                return true
            }
        })
    }

    private fun saveToRecentSearchSuggestion(query: String) {
        SearchRecentSuggestions(
            requireContext(),
            WikiSearchSuggestionProvider.AUTHORITY,
            WikiSearchSuggestionProvider.MODE
        )
            .saveRecentQuery(query, "")
    }


    private fun triggerSearch(query: String) {
        if (isNetworkAvailable(requireContext())) {
            searchQueryResultPresenter.getSearchQueryResult(query)
        } else {
            searchQueryResultPresenter
                .getSearchQueryFromDb(query)
        }
    }

    override fun updateSearchQueryResult(wikiMediaResponse: WikiMediaResponse) {
            wikiMediaResponse.let {
                searchQueryResultListAdapter.updateSearchQueryResultList(it)
                this.wikiMediaResponse = it
            }
        recycler_view_id.visible()
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
        wikiMediaResponse = null
        searchQueryResultPresenter.detachView()
    }

    companion object {
        val instance = SearchQueryResultFragment()
    }
}