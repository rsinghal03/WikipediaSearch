package com.example.wikipediasearch.ui.searchqueryresult

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.SearchRecentSuggestions
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
import com.example.wikipediasearch.ui.MainActivity
import com.example.wikipediasearch.ui.queryresultitemselection.QueryResultItemSelectionFragment
import com.example.wikipediasearch.utils.isNetworkAvailable
import kotlinx.android.synthetic.main.search_result_query_fragment.*
import javax.inject.Inject

class SearchQueryResultFragment : BaseFragment(), SearchQueryResultContract.View {

    private lateinit var searchQueryResultListAdapter: SearchQueryResultListAdapter

    private var listOfQueryResult: ArrayList<Page> = ArrayList()

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
        return LayoutInflater.from(inflater.context)
            .inflate(R.layout.search_result_query_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchQueryResultPresenter.attachView(this)
        getSearchQuery()
        setSuggestionListener()
        setSearchQueryListAdapter()
        if (!listOfQueryResult.isNullOrEmpty()) {
            searchQueryResultListAdapter.updateSearchQueryResultList(listOfQueryResult)
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

    private fun setSearchQueryListAdapter() {
        searchQueryResultListAdapter = SearchQueryResultListAdapter(requireContext())
        searchQueryResultListAdapter.onItemClick = { pageId ->
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
                    SearchRecentSuggestions(
                        requireContext(),
                        WikiSearchSuggestionProvider.AUTHORITY,
                        WikiSearchSuggestionProvider.MODE
                    )
                        .saveRecentQuery(query, "")

                    triggerSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(!newText.isNullOrEmpty()){
                    triggerSearch(newText)
                } else {
                    listOfQueryResult.clear()
                    searchQueryResultListAdapter.updateSearchQueryResultList(listOfQueryResult)
                }
                return true
            }
        })
    }



    private fun triggerSearch(query: String) {
        if (isNetworkAvailable(requireContext())) {
            searchQueryResultPresenter.getSearchQueryResult(query)
        } else {
            searchQueryResultPresenter
                .getSearchQueryFromDb(query)
        }
    }

    override fun updateSearchQueryResult(listOfQueryResult: List<Page>?) {
        if (!listOfQueryResult.isNullOrEmpty()) {
            searchQueryResultListAdapter.updateSearchQueryResultList(listOfQueryResult)
            this.listOfQueryResult = listOfQueryResult as ArrayList<Page>
        }
        recycler_view_id.visible()
    }

    override fun onDestroy() {
        super.onDestroy()
        listOfQueryResult.clear()
        searchQueryResultPresenter.detachView()
    }

    companion object {
        val instance = SearchQueryResultFragment()
    }
}