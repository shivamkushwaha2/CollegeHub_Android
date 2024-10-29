package com.insoft.collegehub.models

data class noteResponse(
    val __v: Int,
    val _id: String,
    val course: String,
    val name: String,
    val noteUrl: String,
    val semester: String,
    val subject: String,
    val uploadedAt: String
)