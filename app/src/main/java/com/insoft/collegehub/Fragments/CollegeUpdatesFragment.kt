package com.insoft.collegehub.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.insoft.collegehub.R
import com.insoft.collegehub.adapter.NewsAdapter
import com.insoft.collegehub.models.collegeUpdates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class CollegeUpdatesFragment : Fragment() {
    private val newsList = mutableListOf<collegeUpdates>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_college_updates, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        newsAdapter = NewsAdapter(newsList)
        recyclerView.adapter = newsAdapter
        progressBar = view.findViewById(R.id.progressBar)

        fetchNews()

        return view
    }
    private fun fetchNews() {
        progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Fetch the HTML from the website
                val doc = Jsoup.connect("https://www.allduniv.ac.in/all_news").get()

                // Select all divs with class "newss"
                val newsDivs = doc.select("div.newss")

                for (newsDiv in newsDivs) {
                    // Extract the title (inside <h3>)
                    val title = newsDiv.selectFirst("h3")?.text() ?: "No Title"

                    // Extract the link (inside <a> tag)
                    val link = newsDiv.selectFirst("a")?.attr("href") ?: "No Link"
                    val date = newsDiv.selectFirst("p.date span")?.text() ?: "No Date"

                    // Log the extracted data
                    Log.d("ExtractedData", "Title: $title")
                    Log.d("ExtractedData", "Link: $link")
                    Log.d("ExtractedData", "Date: $date")

                    // Add the news item to the list
                    newsList.add(collegeUpdates(title, link, date))
                }
                withContext(Dispatchers.Main) {
                    progressBar.visibility = View.GONE
                    newsAdapter.notifyDataSetChanged()
                }

            } catch (e: Exception) {
                Log.e("Error", "Failed to fetch data: ${e.message}")

                withContext(Dispatchers.Main) {
                    progressBar.visibility = View.GONE
                }
                e.printStackTrace()
            }
        }
    }
}