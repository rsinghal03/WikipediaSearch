package com.example.wikipediasearch.ui

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.wikipediasearch.R
import com.example.wikipediasearch.extension.add
import com.example.wikipediasearch.ui.queryresultitemselection.QueryResultItemSelectionFragment
import com.example.wikipediasearch.ui.searchqueryresult.SearchQueryResultFragment

class MainActivity : AppCompatActivity(), QueryResultItemSelectionFragment.BackPressHandler {

    private var webView: WebView? = null

    override fun onBackPressWebView(webView: WebView) {
        this.webView = webView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add(SearchQueryResultFragment.instance, R.id.container, false)
    }

    override fun onBackPressed() {
        when {
            webView?.canGoBack() == true -> webView?.goBack()
            supportFragmentManager.backStackEntryCount>=1 -> supportFragmentManager.popBackStack()
            else -> finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity","onDestroy")
    }
}
