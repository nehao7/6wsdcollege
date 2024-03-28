package com.o7services.a6wsdcollege.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RegisterEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var username : String?= "",
    var email : String?= "",
    var password : String ?= "",
    var type: Int?= 0 //0 - teacher, 1 - student
)