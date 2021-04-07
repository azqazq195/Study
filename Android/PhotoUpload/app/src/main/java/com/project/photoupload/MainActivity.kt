package com.project.photoupload

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class MainActivity : AppCompatActivity(), UploadRequestBody.UploadCallback {

    private lateinit var button: Button
    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var layout_root: ConstraintLayout
    private lateinit var progressBar: ProgressBar

    private var selectedImage: Uri? = null
    private var selectedImage2: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        imageView = findViewById(R.id.imageView)
        imageView2 = findViewById(R.id.imageView2)
        layout_root = findViewById(R.id.layout_root)
        progressBar = findViewById(R.id.progressBar)

        imageView.setOnClickListener{
            openImageChooser(1)
        }
        imageView2.setOnClickListener{
            openImageChooser(2)
        }

        button.setOnClickListener{
            uploadImage()
        }
    }

    private fun uploadImage() {
        if (selectedImage == null) {
            layout_root.snackbar("Select an image first")
            return
        }

        val parcelFileDescriptor =
            contentResolver.openFileDescriptor(selectedImage!!, "r", null) ?: return
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(selectedImage!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        val body = UploadRequestBody(file, "image", this)

        val parcelFileDescriptor2 =
            contentResolver.openFileDescriptor(selectedImage2!!, "r", null) ?: return
        val inputStream2 = FileInputStream(parcelFileDescriptor2.fileDescriptor)
        val file2 = File(cacheDir, contentResolver.getFileName(selectedImage2!!))
        val outputStream2 = FileOutputStream(file2)
        inputStream2.copyTo(outputStream2)
        val body2 = UploadRequestBody(file2, "image", this)

        progressBar.progress = 0
        val foret = Foret("포레1","포레 테스트",10)
        val gson = Gson().toJson(foret)

        Log.e("TAG", "uploadImage: $gson", )
        MyAPI().uploadImage(
            listOf(MultipartBody.Part.createFormData("files", file.name, body),
                MultipartBody.Part.createFormData("files", file2.name, body2)),
            gson.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        ).enqueue(object: Callback<UploadResponse>{
            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                progressBar.progress = 100
                layout_root.snackbar(response.body()?.message.toString())
            }

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                layout_root.snackbar(t.message!!)
            }

        })
    }

    private fun openImageChooser(selected: Int){
        Intent(Intent.ACTION_GET_CONTENT).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, selected)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            when(requestCode){
                1 -> {
                    selectedImage = data?.data
                    imageView.setImageURI(selectedImage)
                }
                2 -> {
                    selectedImage2 = data?.data
                    imageView2.setImageURI(selectedImage2)
                }
            }
        }
    }

    override fun onProgressUpdate(percentage: Int) {
        progressBar.progress = percentage
    }

    companion object{
        private const val REQUEST_CODE_IMAGE_PICKER = 100
    }
}