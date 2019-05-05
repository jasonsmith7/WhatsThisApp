package com.example.whatsthisapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.content.ContentValues
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_ask_view1.*
import android.graphics.Matrix;
//import com.example.whatsthisapp.ask
import android.R.attr.pivotY
import android.R.attr.pivotX
import android.R.attr.angle
import android.graphics.BitmapFactory
import android.media.Image
import java.io.Serializable
import android.R.attr.bitmap
import android.app.Activity
import android.content.Context
import android.graphics.Camera
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.FileProvider
import android.support.v7.app.ActionBar
import android.util.Log
import android.widget.*
//import butterknife.BindView
//import butterknife.ButterKnife
//import com.facebook.drawee.backends.pipeline.Fresco
//import com.facebook.drawee.view.SimpleDraweeView
//import com.facebook.imagepipeline.common.ResizeOptions
//import com.facebook.imagepipeline.request.ImageRequestBuilder
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import com.example.whatsthisapp.Application.Companion.colortheme


class AskView1 : AppCompatActivity() {

//    lateinit var passPath: String
    lateinit var imageView: ImageView
//    private val CAPTURE_IMAGE_REQUEST = 1
//    @JvmField @BindView(R.id.image_view)
//    var imgvPhoto: SimpleDraweeView? = null
//    @JvmField @BindView(R.id.fab_capture)
//    var fabCapturePhoto: FloatingActionButton? = null
    val CAMERA_REQUEST_CODE = 100
    val IMAGE_PICK_CODE = 1000
    val PERMISSION_CODE = 1001
//    val PERMISSION_CODE1 = 1002
    lateinit var imageFilePath: String
    //lateinit var toolbar: ActionBar
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_ask -> {
                //  message.setText(R.string.title_ask)
                val intent = Intent(this, AskView1::class.java)
                this.startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_answer -> {
                //  message.setText(R.string.title_answer)
                val intent = Intent(this, AnswerView2::class.java)
                this.startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_explore -> {
                val intent = Intent(this, ExploreView1::class.java)
                this.startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_view1)

        //set color theme
//        var mApp = Application()
//        var bg = mApp.colortheme
        val bgcolor: ConstraintLayout = findViewById(R.id.container)
        if (colortheme == 2) bgcolor.background = resources.getDrawable(R.drawable.bgreen)
        if (colortheme == 3) bgcolor.background = resources.getDrawable(R.drawable.bred)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.isSelected
//        ButterKnife.bind(this)
        //title = "Ask A Question?"

        val cameraButton: Button = findViewById(R.id.camera_button)
//        val cameraButton: Button = findViewById(R.id.fab_capture)
        //val im: ImageView = findViewById(R.id.imageView4)
        var desc: EditText = findViewById(R.id.editText)
        desc.visibility = View.INVISIBLE
        //config back button
        var back: ImageButton = findViewById(R.id.backButton)
        back.setOnClickListener{
            onBackPressed()
        }
        //im.visibility = View.INVISIBLE

        cameraButton.setOnClickListener {

            val cameraCheckPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

            if (cameraCheckPermission != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) == true) {
                    val builder = AlertDialog.Builder(this)

                    builder.setMessage("I need to see you to work properly!!")
                        .setTitle("Permission required")
                        .setPositiveButton("OK") { _, _ ->
                            requestPermission()
                        }
                    val dialog = builder.create()
                    dialog.show()
                } else {
                    requestPermission()
                }
            } else {
                launchCamera()
            }
        }

        val galButton: Button = findViewById(R.id.gallery_button)
        galButton.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }
    }
    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


//    private fun addFragment(fragment: Fragment) {
//        supportFragmentManager
//            .beginTransaction()
//            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
//            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
//            .addToBackStack(fragment.javaClass.getSimpleName())
//            .commit()
//    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 765)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for ((index, permission) in permissions.withIndex()) {
            if (permission == Manifest.permission.CAMERA) {
                when(requestCode) {
                    PERMISSION_CODE -> {
                        if (grantResults.size > 0 && grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED
                        ) {
                            //permission from popup granted
                            pickImageFromGallery()
                        } else {
                            //permission from popup denied
                            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                        }
                    }
                    765 -> {
                        if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                            launchCamera()
                        }
                    }
                }
            }
        }
    }

    private fun launchCamera() {

        try {
            val imageFile = createImageFile()
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(callCameraIntent.resolveActivity(packageManager) != null) {
                val authorities = packageName + ".fileprovider"
                val imageUri = FileProvider.getUriForFile(this, authorities, imageFile)
                callCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            }
        } catch (e: IOException) {
            Toast.makeText(this, "Could not create file!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageView = findViewById<ImageView>(R.id.image_view)
        val stream = ByteArrayOutputStream()
/*                if(resultCode == Activity.RESULT_OK && data != null) {
                photoImageView.setImageBitmap(data.extras.get("data") as Bitmap)
            }*/
                if (resultCode == Activity.RESULT_OK) {
                    when(requestCode) {
                        CAMERA_REQUEST_CODE -> {
                            var passPath = imageFilePath
                            var imageData = setScaledBitmap()
                            imageData.compress(Bitmap.CompressFormat.PNG, 90, stream)
                            imageView.setImageBitmap(imageData)
                            imageView.rotation = 90.toFloat()

                            val cameraButton = findViewById<Button>(R.id.camera_button)
                            val galButton = findViewById<Button>(R.id.gallery_button)
                            cameraButton.visibility = View.GONE
                            galButton.visibility = View.GONE

                            val acceptButton = findViewById<ImageButton>(R.id.nextButton)
                            acceptButton.setImageResource(R.drawable.nextbutt)
                            //get description
                            var desc: EditText = findViewById(R.id.editText)
                            desc.visibility = View.VISIBLE
                            var post = Post()


                            acceptButton.setOnClickListener {
                                post.img = passPath
                                post.description = desc.text.toString()
                                val intent = Intent(this, PostView1::class.java)
                                intent.putExtra("post", post)
                                this.startActivity(intent)
                            }
                        }
                        IMAGE_PICK_CODE -> {
                            imageView.setImageURI(data?.data)

                            val cameraButton = findViewById<Button>(R.id.camera_button)
                            val galButton = findViewById<Button>(R.id.gallery_button)
                            cameraButton.visibility = View.GONE
                            galButton.visibility = View.GONE

                            val acceptButton = findViewById<ImageButton>(R.id.nextButton)
                            acceptButton.setImageResource(R.drawable.nextbutt)
                            //get description
                            var desc: EditText = findViewById(R.id.editText)
                            desc.visibility = View.VISIBLE
                            var post = Post()


                            acceptButton.setOnClickListener {
                                post.img = null
                                post.description = desc.text.toString()
                                val intent = Intent(this, PostView1::class.java)
                                intent.putExtra("post", post)
                                intent.putExtra("uri",data?.data)
                                this.startActivity(intent)
                            }
                        }
                    }

                }
            }
            //IMAGE_PICK_CODE ->
//            else -> {
//                Toast.makeText(this, "Unrecognized request code", Toast.LENGTH_SHORT).show()
//            }
//        }



    @Throws(IOException::class)
    fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName: String = "JPEG_" + timeStamp + "_"
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if(!storageDir.exists()) storageDir.mkdirs()
        val imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
        imageFilePath = imageFile.absolutePath
        return imageFile
    }
    fun setScaledBitmap(): Bitmap {
        val imageViewWidth = imageView.width
        val imageViewHeight = imageView.height

        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(imageFilePath, bmOptions)
        val bitmapWidth = bmOptions.outWidth
        val bitmapHeight = bmOptions.outHeight

        val scaleFactor = Math.min(bitmapWidth/imageViewWidth, bitmapHeight/imageViewHeight)

        bmOptions.inJustDecodeBounds = false
        bmOptions.inSampleSize = scaleFactor

        return BitmapFactory.decodeFile(imageFilePath, bmOptions)

    }

}



