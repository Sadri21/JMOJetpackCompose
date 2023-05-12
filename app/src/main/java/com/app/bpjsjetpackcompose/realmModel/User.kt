package com.app.bpjsjetpackcompose.realmModel

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class User(): RealmObject {
    @PrimaryKey
    var _idUser: Int = 0
    var userName: String = ""
    var password : String = ""

    constructor(_idUser: Int, userName: String , password: String) : this() {
        this._idUser = _idUser
        this.userName = userName
        this.password = password
    }
}