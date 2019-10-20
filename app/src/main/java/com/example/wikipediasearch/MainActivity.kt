package com.example.wikipediasearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wikipediasearch.extension.add
import com.example.wikipediasearch.ui.searchQueryResult.SearchQueryResultFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add(SearchQueryResultFragment.instance, R.id.container, false)
    }
}
