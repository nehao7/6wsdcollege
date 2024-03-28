package com.o7services.a6wsdcollege.activities

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.o7services.a6wsdcollege.ClgDatabase
import com.o7services.a6wsdcollege.R
import com.o7services.a6wsdcollege.adapters.AchievementsAdapter
import com.o7services.a6wsdcollege.adapters.AlumniPlacementAdapter
import com.o7services.a6wsdcollege.databinding.ActivityPlacementsAlumniBinding
import com.o7services.a6wsdcollege.databinding.AddAchievementsDialogBinding
import com.o7services.a6wsdcollege.databinding.AlumniPlacementsDialogBinding
import com.o7services.a6wsdcollege.entities.AchievementEntity
import com.o7services.a6wsdcollege.entities.PlacementsAlumniEntity
import java.io.ByteArrayOutputStream
import java.io.InputStream

class PlacementsAlumniActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlacementsAlumniBinding
    lateinit var clgDatabase : ClgDatabase
    lateinit var alumniPlacementAdapter: AlumniPlacementAdapter
    private val PICK_IMAGE_REQUEST = 1
    private var selectedImage: ImageView? = null
    var selectedimgUri=""
    lateinit var alumniPlacementsDialogBinding: AlumniPlacementsDialogBinding
    var plaAluList = arrayListOf<PlacementsAlumniEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityPlacementsAlumniBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getplacementAlumniList()
        clgDatabase = ClgDatabase.getClgInstance(this)


        Log.e("list", "onCreate: $plaAluList", )



        binding.recycleraddphotoText.layoutManager= LinearLayoutManager(this)
        alumniPlacementAdapter = AlumniPlacementAdapter(this,plaAluList,
            object :AlumniPlacementAdapter.ViewHandler{

                override fun viewHandler(
                    position: Int,
                    clickType: AlumniPlacementAdapter.DialogClickType
                ) {
                    when(clickType){
                        AlumniPlacementAdapter.DialogClickType.Delete ->{
                            AlertDialog.Builder(this@PlacementsAlumniActivity).apply {
                                setTitle("Are you sure you want to delete this item")
                                setPositiveButton(resources.getString(R.string.yes)){ _, _->
                                    delete(position)
                                }
                                setNegativeButton(resources.getString(R.string.no)){ _, _->}
                                show()
                            }
                        }

                        AlumniPlacementAdapter.DialogClickType.update->{
                            AddUpdatedialog(position)
//                            updateAchievement(position,addAchievementsDialogBinding.edtDescription,addAchievementsDialogBinding.edtAchievement)
                        }
                        else -> {}
                    }
                }

                override fun imgView(position: Int, imageView: ImageView) {
                    plaAluList[position].image?.let { setBase64Image(it,imageView) }
                }
            })
        alumniPlacementAdapter.notifyDataSetChanged()
        binding.recycleraddphotoText.adapter = alumniPlacementAdapter

        binding.btnAddPA.setOnClickListener {
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
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
            setBase64Image(base64String,alumniPlacementsDialogBinding.imgAdd )


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
        Toast.makeText(this, "Addupdate", Toast.LENGTH_SHORT).show()

        var dialog = Dialog(this)
        alumniPlacementsDialogBinding =  AlumniPlacementsDialogBinding.inflate(layoutInflater)
        dialog.setContentView(alumniPlacementsDialogBinding!!.root)
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

        alumniPlacementsDialogBinding.imgAdd.setOnClickListener {
            openGallery()
        }
        alumniPlacementsDialogBinding?.btnsave?.setOnClickListener {
            if (
           alumniPlacementsDialogBinding.edtAlumni.text.toString()==""||alumniPlacementsDialogBinding.edtPlacements.text.toString()==""
//                galleryUri==null
            ){
                Toast.makeText(this, "Please add image and name", Toast.LENGTH_SHORT).show()
            }else{
//                print("bitmap $bitmap")
                if(position > -1){
                    update(position,alumniPlacementsDialogBinding.edtAlumni,alumniPlacementsDialogBinding.edtPlacements,selectedimgUri)
                }else{
                    insert(alumniPlacementsDialogBinding.edtAlumni,alumniPlacementsDialogBinding.edtPlacements,selectedimgUri)
                    alumniPlacementAdapter.notifyDataSetChanged()
                    getplacementAlumniList()
                }
                dialog.dismiss()


            }

        }
        dialog.show()
    }

    private fun insert(placement: TextView, alumni: TextView, image: String) {
        class InsertAchAsyncTask : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                // Create a new AchievementEntity
                val entity = PlacementsAlumniEntity(placement = placement.text.toString(), alumni =alumni.text.toString(), image = image)
                // Insert the entity into the database
                clgDatabase.clgDao().insertplacementAlumni(entity)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                // Once insertion is done, update the UI or perform any required action
                // For example, you may want to refresh the list of achievements
                getplacementAlumniList()
            }
        }
        InsertAchAsyncTask().execute()
    }

    private fun delete(position: Int) {
        class DeleteAchAsyncTask : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                // Get the achievement to delete (for example, you may get it from your list)
//                val entityToDelete = AchievementEntity(id = achList[1].id)
                // Delete the achievement from the database
                clgDatabase.clgDao().deleteplacementAlumni(plaAluList[position])
//                achievementsAdapter.notifyDataSetChanged()
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                // Once deletion is done, update the UI or perform any required action
                // For example, you may want to refresh the list of achievements
                getplacementAlumniList()
            }
        }
        DeleteAchAsyncTask().execute()
    }

    private fun update(position: Int, placement: TextView, alumni: TextView, image: String) {
        class UpdateAchAsyncTask : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                // Get the achievement to update (for example, you may get it from your list)
                val achievementIdToUpdate = plaAluList[position].id


                // Create a new AchievementEntity with updated values
                val updatedEntity = PlacementsAlumniEntity(
                    id = achievementIdToUpdate,
                    placement = "${placement.text.toString()}",
                    alumni = "${alumni.text.toString()}",
                    image = image
                )

                clgDatabase.clgDao().updateplacementAlumni(updatedEntity)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                // Once update is done, update the UI or perform any required action
                // For example, you may want to refresh the list of achievements
                getplacementAlumniList()
            }
        }
        UpdateAchAsyncTask().execute()
    }
    private fun getplacementAlumniList() {
        class GetAchAsyncTask : AsyncTask<Void, Void, List<PlacementsAlumniEntity>>() {
            override fun doInBackground(vararg params: Void?): List<PlacementsAlumniEntity> {
                // Retrieve the list of achievements from the database
                return clgDatabase.clgDao().getplacementAlumni()
            }

            override fun onPostExecute(result: List<PlacementsAlumniEntity>?) {
                super.onPostExecute(result)
                // Update the UI with the retrieved list of achievements
                plaAluList.clear()
                result?.let { plaAluList.addAll(it) }
                alumniPlacementAdapter.notifyDataSetChanged()
                // Notify your adapter or update UI as needed
            }
        }
        GetAchAsyncTask().execute()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}