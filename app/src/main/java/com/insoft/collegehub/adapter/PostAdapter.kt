package com.insoft.collegehub.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.insoft.collegehub.R
import com.insoft.collegehub.models.Post

class PostAdapter(private val postList: MutableList<Post>, private val onLikeClicked: (Post) -> Unit) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postContent: TextView = itemView.findViewById(R.id.postTitle)
        val postImage: ImageView = itemView.findViewById(R.id.postImage)
        val name: TextView = itemView.findViewById(R.id.userName)
        val likeButton: ImageView = itemView.findViewById(R.id.likeButton)
        val likecount: TextView = itemView.findViewById(R.id.likeCount)

        // Add other views like 'like button', etc. here if needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.postContent.text = post.content
        holder.name.text = post.user.name
        holder.likecount.text = post.likes.size.toString() // Set the like count

        try {
            // Load image if available, otherwise hide ImageView
            holder.postImage.visibility = View.VISIBLE
            Glide.with(holder.itemView.context)
                .load(post.imageUrl)
                .into(holder.postImage)
        } catch (e:Exception){
            Log.d("Glide error",e.toString())
        }

        if (post.isLiked) {
            holder.likeButton.setImageResource(R.drawable.baseline_thumb_up_24) // Change to liked icon
        } else {
            holder.likeButton.setImageResource(R.drawable.like_icon) // Change to unliked icon
        }

        holder.likeButton.setOnClickListener {
            post.isLiked = !post.isLiked

            onLikeClicked(post)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
    fun updatePost(updatedPost: Post) {
        val position = postList.indexOfFirst { it._id == updatedPost._id }
        if (position != -1) {
            postList[position] = updatedPost
            notifyItemChanged(position) // Notify the adapter to refresh the item
        }
    }
}
