package com.example.sevenwindstest.data

object User {
    var username: String? = null
    var token: String? = null
    var tokenLifetime: Long? = null
    val isAuthorized: Boolean
        get() = token != null
}