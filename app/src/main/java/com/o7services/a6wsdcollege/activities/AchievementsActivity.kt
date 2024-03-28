package com.o7services.a6wsdcollege.activities

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.o7services.a6wsdcollege.ClgDatabase
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.adapters.AchievementsAdapter
import com.o7services.a6wsdcollege.databinding.ActivityAchievementsBinding
import com.o7services.a6wsdcollege.databinding.AddAchievementsDialogBinding
import com.o7services.a6wsdcollege.entities.AchievementEntity
import com.o7services.a6wsdcollege.entities.NotesEntity
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.InputStream

class AchievementsActivity : AppCompatActivity() {
    lateinit var binding: ActivityAchievementsBinding
    lateinit var clgDatabase : ClgDatabase
    lateinit var achievementsAdapter: AchievementsAdapter
    private val PICK_IMAGE_REQUEST = 1
    private var selectedImage: ImageView? = null
    var selectedimgUri=""
    lateinit var addAchievementsDialogBinding: AddAchievementsDialogBinding
    var achList = arrayListOf<AchievementEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityAchievementsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        clgDatabase = ClgDatabase.getClgInstance(this)
        getAch()


        Log.e("list", "onCreate: $achList", )


        binding.recycleraddphotoText.layoutManager=LinearLayoutManager(this)
        achievementsAdapter = AchievementsAdapter(this,achList,
            object :AchievementsAdapter.ViewHandler{
                override fun viewHandler(position: Int, clickType : AchievementsAdapter.DialogClickType) {
                    when(clickType){
                        AchievementsAdapter.DialogClickType.Delete ->{
                            AlertDialog.Builder(this@AchievementsActivity).apply {
                                setTitle("Are you sure you want to delete this item")
                                setPositiveButton(resources.getString(R.string.yes)){ _, _->
                                   deleteAchievement(position)
                                }
                                setNegativeButton(resources.getString(R.string.no)){ _, _->}
                                show()
                            }
                        }

                        AchievementsAdapter.DialogClickType.update->{
                            AddUpdatedialog(position)
//                            updateAchievement(position,addAchievementsDialogBinding.edtDescription,addAchievementsDialogBinding.edtAchievement)
                        }

                        else -> {}
                    }
                }

                override fun imgView(position: Int, imageView: ImageView) {
                    achList[position].image?.let { setBase64Image(it,imageView) }
                }
            })
        achievementsAdapter.notifyDataSetChanged()
        binding.recycleraddphotoText.adapter = achievementsAdapter

        binding.btnAdd.setOnClickListener {
            Log.e("TAG"," on click")
            AddUpdatedialog()
//            insertAchievement()
//            deleteAchievement()

//            updateAchievement()
        }


    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data
//            selectedimgUri= imageUri.toString()
            val imageStream: InputStream? = contentResolver.openInputStream(imageUri!!)
            val selectedBitmap = BitmapFactory.decodeStream(imageStream)
//            addAchievementsDialogBinding.imgAdd?.setImageBitmap(selectedBitmap)
            val base64String = bitmapToBase64(selectedBitmap)
            selectedimgUri=base64String
//            addAchievementsDialogBinding.imgAdd?.setImageURI(Uri.parse(base64String))
            setBase64Image(base64String,addAchievementsDialogBinding.imgAdd )


        }
    }
    fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }


    fun setBase64Image(base64String: String, imageView: ImageView) {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        val decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

        imageView.setImageBitmap(decodedBitmap)
    }
    private fun AddUpdatedialog(position: Int = -1) {
        var dialog = Dialog(this)
        addAchievementsDialogBinding =  AddAchievementsDialogBinding.inflate(layoutInflater)
        dialog.setContentView(addAchievementsDialogBinding.root)
        dialog.setCancelable(true)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        addAchievementsDialogBinding?.imgAdd?.setOnClickListener {
//            checkPermissions()
//        }

//        addAchievementsDialogBinding?.imgDelete?.setOnClickListener{
//            dialog.dismiss()
////            galleryUri=null
//        }

//        if(position == -1){
//            addAchievementsDialogBinding?.imgAdd?.setImageResource(R.drawable.ic_launcher_background)
//        }else{
//            addAchievementsDialogBinding?.imgAdd?.setImageURI(Uri.parse(mainActivity.data[position].image))
//        }

        addAchievementsDialogBinding.imgAdd.setOnClickListener {
            openGallery()
        }
        addAchievementsDialogBinding?.btnsave?.setOnClickListener {
            if (
                addAchievementsDialogBinding?.edtname?.text.toString()==""||addAchievementsDialogBinding.edtAchievement.text.toString()==""||addAchievementsDialogBinding.edtDescription.text.toString()==""
//                galleryUri==null
            ){
                Toast.makeText(this, "Please add image and name", Toast.LENGTH_SHORT).show()
            }else{
//                print("bitmap $bitmap")
                    if(position > -1){
                        updateAchievement(position,addAchievementsDialogBinding.edtDescription,addAchievementsDialogBinding.edtAchievement,selectedimgUri)
                    }else{
                       insertAchievement(addAchievementsDialogBinding.edtDescription,addAchievementsDialogBinding.edtAchievement,selectedimgUri)
                        achievementsAdapter.notifyDataSetChanged()
                        getAch()
                    }
                    dialog.dismiss()
            }

        }
        dialog.show()
    }

    private fun insertAchievement(description: TextView, achievement: TextView,image: String) {
        class InsertAchAsyncTask : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                // Create a new AchievementEntity
                val entity = AchievementEntity(description = description.text.toString(), achievement =achievement.text.toString(), image = image)
                // Insert the entity into the database
                clgDatabase.clgDao().insertAch(entity)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                // Once insertion is done, update the UI or perform any required action
                // For example, you may want to refresh the list of achievements
                getAch()
            }
        }
        InsertAchAsyncTask().execute()
    }

    private fun deleteAchievement(position: Int) {
        class DeleteAchAsyncTask : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                // Get the achievement to delete (for example, you may get it from your list)
//                val entityToDelete = AchievementEntity(id = achList[1].id)
                    // Delete the achievement from the database
                    clgDatabase.clgDao().deleteAch(achList[position])
//                achievementsAdapter.notifyDataSetChanged()
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                // Once deletion is done, update the UI or perform any required action
                // For example, you may want to refresh the list of achievements
                getAch()
            }
        }
        DeleteAchAsyncTask().execute()
    }

    private fun updateAchievement(position: Int,description: TextView, achievement: TextView,image: String) {
        class UpdateAchAsyncTask : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                // Get the achievement to update (for example, you may get it from your list)
                val achievementIdToUpdate = achList[position].id


                // Create a new AchievementEntity with updated values
                val updatedEntity = AchievementEntity(
                    id = achievementIdToUpdate,
                    description = "${description.text.toString()}",
                    achievement = "${achievement.text.toString()}",
                    image = image
                )

                clgDatabase.clgDao().updateAch(updatedEntity)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                // Once update is done, update the UI or perform any required action
                // For example, you may want to refresh the list of achievements
                getAch()
            }
        }
        UpdateAchAsyncTask().execute()
    }
    private fun getAch() {
        class GetAchAsyncTask : AsyncTask<Void, Void, List<AchievementEntity>>() {
            override fun doInBackground(vararg params: Void?): List<AchievementEntity> {
                // Retrieve the list of achievements from the database
                return clgDatabase.clgDao().getAchList()
            }

            override fun onPostExecute(result: List<AchievementEntity>?) {
                super.onPostExecute(result)
                // Update the UI with the retrieved list of achievements
                achList.clear()
                result?.let { achList.addAll(it) }
                achievementsAdapter.notifyDataSetChanged()
                // Notify your adapter or update UI as needed
            }
        }
        GetAchAsyncTask().execute()
    }

//    fun getNotes(){
//        class getNotesDb : AsyncTask<Void, Void, List<AchievementEntity>>() {
//            override fun doInBackground(vararg p0: Void?): List<AchievementEntity> {
////                notesList.addAll()
//                return clgDatabase.clgDao().getAchList()
//            }
//
//            override fun onPostExecute(result: List<AchievementEntity>?) {
//                super.onPostExecute(result)
//                achList.addAll(result?: arrayListOf())
//            }
//        }
//        getNotesDb().execute()
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}