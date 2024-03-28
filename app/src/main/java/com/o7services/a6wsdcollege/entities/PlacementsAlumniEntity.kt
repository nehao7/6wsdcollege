package com.o7services.a6wsdcollege.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlacementsAlumniEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var placement : String?= "",
    var alumni : String?= "",
    var image : String ?="",
)