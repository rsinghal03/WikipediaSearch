package com.example.wikipediasearch.ui.searchQueryResult

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wikipediasearch.R
import com.example.wikipediasearch.data.model.Page

class SearchQueryResultListAdapter(private val context: Context) :
    RecyclerView.Adapter<SearchQueryResultListAdapter.ViewHolder>() {

    lateinit var listOfQueryResult: List<Page>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listOfQueryResult.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.text = listOfQueryResult[position].title
        holder.detailView.text = listOfQueryResult.get(position).terms?.description?.get(0) ?: ""
        Glide.with(context)
            .load(listOfQueryResult[position].thumbnail?.source)
            .placeholder(R.drawable.place_holder)
            .into(holder.imageView)
    }

    fun updateSearchQueryResultList(listOfQueryResult: List<Page>) {
        this.listOfQueryResult = listOfQueryResult
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: AppCompatTextView =
            view.findViewById<AppCompatTextView>(R.id.search_result_title)
        val detailView: AppCompatTextView =
            view.findViewById<AppCompatTextView>(R.id.search_result_detail)
        val imageView: AppCompatImageView =
            view.findViewById<AppCompatImageView>(R.id.search_result_image)

        init {

        }

    }
}