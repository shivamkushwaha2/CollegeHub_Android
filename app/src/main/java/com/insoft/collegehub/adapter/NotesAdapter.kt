package com.insoft.collegehub.adapter

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.insoft.collegehub.R
import com.insoft.collegehub.models.noteResponse

class NotesAdapter (val context: Context, val list: List<noteResponse>):Adapter<NotesAdapter.NotesViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_item,parent,false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val notesItem = list[position]
        holder.titleTextView.text = notesItem.name
//        holder.linkTextView.text = newsItem.link
        holder.dateTextView.text = notesItem.uploadedAt

        holder.linkTextView.setOnClickListener {
        download(context, notesItem.noteUrl,notesItem.name)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val linkTextView: TextView = itemView.findViewById(R.id.linkTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    }

    private fun openCustomTab(context: Context, url: String) {
        val uri = Uri.parse(url)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, uri)
    }

    fun download(context: Context, url: String, fileName: String) {
        val request = DownloadManager.Request(Uri.parse(url)).apply {
            setTitle("Downloading $fileName")
            setDescription("Downloading PDF file...")
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
            setAllowedOverMetered(true) // Allow over cellular network
        }

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request) // Start the download
    }
}