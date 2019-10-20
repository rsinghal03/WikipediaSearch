package com.example.wikipediasearch.dependencyinjection

import com.example.wikipediasearch.ui.searchqueryresult.SearchQueryResultFragment
import dagger.Component

@Component(modules = [WikiMediaModule::class])
interface WikiMediaComponent {

    fun inject(SearchQueryResultFragment: SearchQueryResultFragment)

}