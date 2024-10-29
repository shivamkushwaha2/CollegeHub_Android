package com.insoft.collegehub.models

data class LoginResponse(
    val token: String,
    val user: User
)