package com.o7services.a6wsdcollege.activities

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.o7services.a6wsdcollege.ClgDatabase
import com.o7services.a6wsdcollege.entities.NotesEntity
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var notesDatabase : ClgDatabase
    lateinit var tvInsert: TextView
    var notesList = arrayListOf<NotesEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notesDatabase = ClgDatabase.getClgInstance(this)
        getNotes()
        binding.btnAdd.setOnClickListener {
            class insertClass : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg p0: Void?): Void? {
                    var entity = NotesEntity(notesTitle = "This is testing", notesDescription = "Description")
                    notesDatabase.clgDao().insertNotes(entity)
                    return null
                }
            }

            insertClass().execute()
        }

    }


    fun getNotes(){
        class getNotesDb : AsyncTask<Void, Void, List<NotesEntity>>() {
            override fun doInBackground(vararg p0: Void?): List<NotesEntity> {
//                notesList.addAll()
                return notesDatabase.clgDao().getNotesList()
            }

            override fun onPostExecute(result: List<NotesEntity>?) {
                super.onPostExecute(result)
                notesList.addAll(result?: arrayListOf())
            }
        }
        getNotesDb().execute()
    }
}