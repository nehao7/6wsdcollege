package com.o7services.a6wsdcollege

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: 017
 * @Date: 18/03/24
 * @Time: 2:33 pm
 */
@Entity
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var notesTitle: String?= null,
    var notesDescription: String?= null,
)
