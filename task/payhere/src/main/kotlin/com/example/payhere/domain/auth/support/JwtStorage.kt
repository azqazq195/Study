package com.example.payhere.domain.auth.support

object JwtStorage {
    private val signOutToken: MutableSet<String> = mutableSetOf()

    fun signOut(token: String) {
        signOutToken.add(token)
    }

    fun isSignOut(token: String): Boolean {
        return signOutToken.contains(token)
    }
}