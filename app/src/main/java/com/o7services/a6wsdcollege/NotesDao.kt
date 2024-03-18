package com.o7services.a6wsdcollege

import androidx.room.Dao
import androidx.room.Index
import androidx.room.Insert
import androidx.room.Query

/**
 * @Author: 017
 * @Date: 18/03/24
 * @Time: 2:40 pm
 */
@Dao
interface NotesDao {

    @Insert
    fun insertNotes(notesEntity: NotesEntity)

    @Query("Select * from NotesEntity")
    fun getNotesList(): List<NotesEntity>
}