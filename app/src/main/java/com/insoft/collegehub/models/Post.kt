package com.insoft.collegehub.models

data class Post(
    val __v: Int,
    val _id: String,
    val content: String,
    val createdAt: String,
    val imageUrl: String,
    val likes: List<String>,
    val updatedAt: String,
    val user: UserX,
    var isLiked: Boolean = false
)