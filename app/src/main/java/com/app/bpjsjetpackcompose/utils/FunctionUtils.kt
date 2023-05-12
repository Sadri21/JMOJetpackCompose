package com.app.bpjsjetpackcompose.utils

import com.app.bpjsjetpackcompose.realmModel.User
import com.app.bpjsjetpackcompose.utils.RealmConfig.createConfigRealm
import com.app.bpjsjetpackcompose.utils.RealmConfig.inputUser
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

fun isEmailValid(email: String): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
    return emailRegex.matches(email)
}

fun addAllUser() {
    val realm =  Realm.open(createConfigRealm())
    val items: RealmResults<User> = realm.query<User>().find()
    realm.writeBlocking {
        for (data in inputUser()) {
            if (items.none { it._idUser == data._idUser }) {
                copyToRealm(data)
            }
        }
    }
}