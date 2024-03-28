package com.o7services.a6wsdcollege.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OurPrideEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var text : String?= "",
    var image : String ?="",
)