package com.example.wikipediasearch.ui.queryresultitemselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.example.wikipediasearch.ui.BaseFragment
import kotlinx.android.synthetic.main.query_result_item_selection_fragment.*
import android.webkit.WebViewClient
import com.example.wikipediasearch.MainActivity
import com.example.wikipediasearch.R


const val URL_KEY = "url"

class QueryResultItemSelectionFragment : BaseFragment() {

    lateinit var pageId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageId = arguments?.get(URL_KEY) as String
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return LayoutInflater.from(inflater.context).inflate(R.layout.query_result_item_selection_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        (requireActivity() as MainActivity).onBackPressWebView(webView)
        webView.loadUrl("https://en.wikipedia.org/wiki/Translation?curid=$pageId")
    }

    companion object {
        fun getInstance(url: String): QueryResultItemSelectionFragment {
            val fragment = QueryResultItemSelectionFragment()
            fragment.arguments = Bundle().apply {
                putString(URL_KEY, url)
            }
            return fragment
        }
    }

    interface BackPressHandler {
        fun onBackPressWebView(webView: WebView)
    }

}