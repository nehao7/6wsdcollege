package com.o7services.a6wsdcollege

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.o7services.a6wsdcollege.entities.AchievementEntity
import com.o7services.a6wsdcollege.entities.NotesEntity
import com.o7services.a6wsdcollege.entities.PlacementsAlumniEntity
import com.o7services.a6wsdcollege.entities.RegisterEntity

/**
 * @Author: 017
 * @Date: 18/03/24
 * @Time: 2:32 pm
 */
@Database(version = 2, entities = [NotesEntity::class, RegisterEntity::class, AchievementEntity::class,PlacementsAlumniEntity::class])
abstract class ClgDatabase : RoomDatabase(){
    abstract  fun clgDao() : ClgDao
    companion object{
        private var clgDatabase: ClgDatabase?= null
        fun getClgInstance(context: Context): ClgDatabase {
            if(clgDatabase == null){
                clgDatabase = Room.databaseBuilder(context, ClgDatabase::class.java, context.resources.getString(R.string.app_name)).build()
            }
            return clgDatabase!!
        }
    }
}