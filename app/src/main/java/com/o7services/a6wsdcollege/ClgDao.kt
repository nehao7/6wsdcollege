package com.o7services.a6wsdcollege

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.o7services.a6wsdcollege.entities.AchievementEntity
import com.o7services.a6wsdcollege.entities.NotesEntity
import com.o7services.a6wsdcollege.entities.PlacementsAlumniEntity
import com.o7services.a6wsdcollege.entities.RegisterEntity

/**
 * @Author: 017
 * @Date: 18/03/24
 * @Time: 2:40 pm
 */
@Dao
interface ClgDao {

    @Insert
    fun insertNotes(notesEntity: NotesEntity)
    @Delete
    fun deleteNotes(notesEntity: NotesEntity)

    // Update a note
    @Update
    fun updateNotes(notesEntity: NotesEntity)


    @Query("Select * from NotesEntity")
    fun getNotesList(): List<NotesEntity>

    @Insert
    fun insertuser(registerEntity: RegisterEntity)

    @Query("Select * from RegisterEntity")
    fun getuserList(): List<RegisterEntity>

    @Query("Select * from RegisterEntity where email = :email and password = :password")
    fun getSinglePerson(email: String, password: String) : RegisterEntity

    @Query("Select * from AchievementEntity")
    fun getAchList(): List<AchievementEntity>

    @Insert
    fun insertAch(achievementEntity: AchievementEntity)
    @Delete
    fun deleteAch(achievementEntity: AchievementEntity)

    // Update a note
    @Update
    fun updateAch(achievementEntity: AchievementEntity)

    @Query("Select * from PlacementsAlumniEntity")
    fun getplacementAlumni(): List<PlacementsAlumniEntity>
    @Insert
    fun insertplacementAlumni(placementsOurPrideEntity: PlacementsAlumniEntity)
    @Delete
    fun deleteplacementAlumni(placementsOurPrideEntity: PlacementsAlumniEntity)
    // Update a note
    @Update
    fun updateplacementAlumni(placementsOurPrideEntity: PlacementsAlumniEntity)


}
