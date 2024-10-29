package com.insoft.collegehub.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.insoft.collegehub.R
import com.insoft.collegehub.models.collegeUpdates

class NewsAdapter(private val newsList: List<collegeUpdates>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val linkTextView: TextView = itemView.findViewById(R.id.linkTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.titleTextView.text = newsItem.title
//        holder.linkTextView.text = newsItem.link
        holder.dateTextView.text = newsItem.date

        holder.linkTextView.setOnClickListener {
            openCustomTab(holder.itemView.context, newsItem.link)
        }
    }

    override fun getItemCount(): Int = newsList.size

    private fun openCustomTab(context: Context, url: String) {
        val uri = Uri.parse(url)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, uri)
    }
}
