package com.o7services.a6wsdcollege

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @Author: 017
 * @Date: 18/03/24
 * @Time: 2:32 pm
 */
@Database(version = 1, entities = [NotesEntity::class])
abstract class NotesDatabase : RoomDatabase(){
    abstract  fun notesDao() : NotesDao
    companion object{
        private var notesDatabase: NotesDatabase?= null
        fun getNotesInstance(context: Context): NotesDatabase {
            if(notesDatabase == null){
                notesDatabase = Room.databaseBuilder(context, NotesDatabase::class.java, context.resources.getString(R.string.app_name)).build()
            }
            return notesDatabase!!
        }
    }
}