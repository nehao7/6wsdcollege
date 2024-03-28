package com.o7services.a6wsdcollege.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AchievementEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var description : String?= "",
    var achievement : String?= "",
    var image : String ?="",
)