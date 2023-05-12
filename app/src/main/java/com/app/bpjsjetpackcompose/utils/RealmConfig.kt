package com.app.bpjsjetpackcompose.utils

import com.app.bpjsjetpackcompose.realmModel.User
import io.realm.kotlin.Configuration
import io.realm.kotlin.RealmConfiguration

object RealmConfig {

    fun createConfigRealm(): Configuration {
        return RealmConfiguration.Builder(
            setOf(User::class)
        ).build()
    }

    fun inputUser(): List<User> {
        val user1 = User(1, "testing@gmail.com", "1234567")
        return listOf(user1)
    }
}