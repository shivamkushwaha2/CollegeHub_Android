package com.insoft.collegehub.models

data class SignupRequest(
    val name:String,
    val email: String,
    val password: String
)