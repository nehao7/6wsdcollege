package com.o7services.a6wsdcollege.activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7services.a6wsdcollege.ClgDatabase
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.entities.RegisterEntity
import com.o7services.a6wsdcollege.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    var regstatus=-1
    lateinit var clgDatabase: ClgDatabase
    var userList = arrayListOf<RegisterEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clgDatabase = ClgDatabase.getClgInstance(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        regstatus=intent.getIntExtra("status",-1)
        Log.e("registerstatus", "onCreateregister: $regstatus", )

        binding.tvlogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java).putExtra("status",regstatus))
        }

        binding.btnregister.setOnClickListener {
            if(binding.edtusername.text.toString().isNullOrEmpty()){
                binding.edtusername.error = "Enter username"
            }else if(binding.edtpassword.text.toString().isNullOrEmpty()){
                binding.edtpassword.error = "Enter password"
            }
            else if(binding.edtemail.text.toString().isNullOrEmpty()){
                binding.edtpassword.error = "Enter Email"
            }
            else{
                var response = RegisterEntity(username = binding.edtusername.text.toString(),email= binding.edtemail.text.toString(), password = binding.edtpassword.text.toString(),type= regstatus)
                if(response != null){
                   startActivity(Intent(this,LoginActivity::class.java))
                }else{
                    Toast.makeText(this, "Sorry user does not exists", Toast.LENGTH_LONG).show()
                }
            }

            class insertClass : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg p0: Void?): Void? {
//                       clgDatabase.clgDao().insertuser(
                    var response = RegisterEntity(username = binding.edtusername.text.toString(),email= binding.edtemail.text.toString(), password = binding.edtpassword.text.toString(),type= regstatus)

                    clgDatabase.clgDao().insertuser(response)

                    return null
                }

            }

            insertClass().execute()

        }
    }

    fun getNotes(){
        class getuserDb : AsyncTask<Void, Void, List<RegisterEntity>>() {
            override fun doInBackground(vararg p0: Void?): List<RegisterEntity> {
//                notesList.addAll()
                return clgDatabase.clgDao().getuserList()
            }

            override fun onPostExecute(result: List<RegisterEntity>?) {
                super.onPostExecute(result)
                userList.addAll(result?: arrayListOf())
            }
        }
        getuserDb().execute()
    }
}