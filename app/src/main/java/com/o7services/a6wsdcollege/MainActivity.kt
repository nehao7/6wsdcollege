package com.o7services.a6wsdcollege

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var notesDatabase : NotesDatabase
    lateinit var tvInsert: TextView
    var notesList = arrayListOf<NotesEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesDatabase = NotesDatabase.getNotesInstance(this)
        tvInsert = findViewById(R.id.tvInsert)

        getNotes()

        tvInsert.setOnClickListener {
            class insertClass : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg p0: Void?): Void? {
                    var entity = NotesEntity(notesTitle = "This is testing", notesDescription = "Description")
                    notesDatabase.notesDao().insertNotes(entity)
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
                return notesDatabase.notesDao().getNotesList()
            }

            override fun onPostExecute(result: List<NotesEntity>?) {
                super.onPostExecute(result)
                notesList.addAll(result?: arrayListOf())
            }
        }

        getNotesDb().execute()
    }
}