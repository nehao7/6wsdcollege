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
import com.o7services.a6wsdcollege.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    var loginstatus=-1
    lateinit var clgDatabase: ClgDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clgDatabase = ClgDatabase.getClgInstance(this)

        loginstatus=intent.getIntExtra("status",-1)
        Log.e("status", "onCreateLogin: $loginstatus", )

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tvregister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java).putExtra("status",loginstatus))
        }

        binding.btn1.setOnClickListener {
           login(binding.edtemail.text.toString(),binding.edtpassword.text.toString())
        }
    }
    private fun login(email: String, password: String) {
        class LoginAsyncTask : AsyncTask<Void, Void, RegisterEntity?>() {
            override fun doInBackground(vararg params: Void?): RegisterEntity? {
                return clgDatabase.clgDao().getSinglePerson(email, password)
            }

            override fun onPostExecute(result: RegisterEntity?) {
                super.onPostExecute(result)
                if (result != null) {
                    startActivity(Intent(this@LoginActivity,HomeActivity::class.java).putExtra("status",loginstatus))
                    finish()
                    Toast.makeText(this@LoginActivity,  "Login Successfully"
                           , Toast.LENGTH_SHORT).show()
                    // Login successful, proceed with your logic
                } else { Toast.makeText(this@LoginActivity,  "Login Error"
                    , Toast.LENGTH_SHORT).show()

                    // User not found or invalid credentials
                    // Handle login failure
                }
            }
        }

        LoginAsyncTask().execute()
    }
}