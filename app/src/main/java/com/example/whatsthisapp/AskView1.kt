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
//    private var mCurrentPhotoPath: String = ""
//    private var filePath: Uri? = null
//    private val TAKE_PHOTO_REQUEST = 101
//    private var photoFile: File? = null
    lateinit var imageView: ImageView
//    private val CAPTURE_IMAGE_REQUEST = 1
//    @JvmField @BindView(R.id.image_view)
//    var imgvPhoto: SimpleDraweeView? = null
//    @JvmField @BindView(R.id.fab_capture)
//    var fabCapturePhoto: FloatingActionButton? = null
val CAMERA_REQUEST_CODE = 0
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
                if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                    launchCamera()
                }
            }
        }
    }

//    private fun launchCamera() {
//        val values = ContentValues(1)
//        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
//        val fileUri = contentResolver
//            .insert(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                values
//            )
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        if (intent.resolveActivity(packageManager) != null) {
//            mCurrentPhotoPath = fileUri.toString()
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
//            intent.addFlags(
//                Intent.FLAG_GRANT_READ_URI_PERMISSION
//                        or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//            )
//            startActivityForResult(intent, TAKE_PHOTO_REQUEST)
//        }
//    }

    //    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (resultCode == Activity.RESULT_OK && requestCode == TAKE_PHOTO_REQUEST) {
//            processCapturedPhoto()
//        } else {
//            super.onActivityResult(requestCode, resultCode, data)
//        }
//    }
    private fun launchCamera() {

//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                startActivityForResult(takePictureIntent, 9090)
//                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                intent.putExtra(MediaStore.EXTRA_OUTPUT
//                currentPhotoFilename = MediaUtil.getOutputMediaFileUri()
//
//            }
//        }
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


//        fun getRealPathFromURI(uri: Uri): Uri? {
//            val proj = arrayOf(MediaStore.Images.Media.DATA)
//            val cursor = getContentResolver().query(uri, proj, null, null, null)
//
//            cursor.moveToFirst()
//            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
//            return Uri.parse(cursor.getString(idx))
//        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageView = findViewById<ImageView>(R.id.image_view)
        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
/*                if(resultCode == Activity.RESULT_OK && data != null) {
                photoImageView.setImageBitmap(data.extras.get("data") as Bitmap)
            }*/
                if (resultCode == Activity.RESULT_OK) {
                    var passPath = imageFilePath
                    var imageData= setScaledBitmap()
                    imageView.setImageBitmap(imageData)
                    imageView.rotation = 90.toFloat()

                    val stream = ByteArrayOutputStream()
                    imageData.compress(Bitmap.CompressFormat.PNG, 90, stream)
//                    var imageData1: ByteArray = stream.toByteArray()

                    val cameraButton = findViewById<Button>(R.id.camera_button)
                    cameraButton.visibility = View.GONE
                    val acceptButton = findViewById<ImageButton>(R.id.nextButton)
//                    val cancelButton = findViewById<ImageButton>(R.id.cancelButton)
//                    acceptButton.visibility = View.VISIBLE
//                    cancelButton.visibility = View.VISIBLE


                    //get description
                    var desc: EditText = findViewById(R.id.editText)
                    desc.visibility = View.VISIBLE
                    var post = Post()


                    acceptButton.setOnClickListener {
                        post.img = passPath
                        post.description = desc.text.toString()
                        val intent = Intent(this, PostView1::class.java)
//                        var bundle = Bundle()
//                        bundle.putByteArray("image",imageData1)
//                        var baos = ByteArrayOutputStream()
//                        imageData.compress(Bitmap.CompressFormat.PNG, 100, baos)
//                        var b = baos.toByteArray()
//                        intent.putExtra("post", b)
                        intent.putExtra("post", post)
//                        intent.putExtra("image", imageFilePath)
//                        intent.putExtra("desc", post.description)
                        this.startActivity(intent)
                        //finish()

                    }
//                    cancelButton.setOnClickListener {
//                        //val uri = "@drawable/ic_launcher_background.xml"
//                        //val imageResource = resources.getIdentifier(uri, null, packageName)
//                        //var res = getResources().getDrawable(imageResource,@drawable/i);
//                        imageView.setImageResource(R.drawable.placeholder)
////                      imageView.rotation = -180.toFloat()
//                        imageView.rotation = 0.toFloat()
//                        desc.text.clear()
//
//                        acceptButton.visibility = View.INVISIBLE
//                        cancelButton.visibility = View.INVISIBLE
//                        desc.visibility = View.INVISIBLE
//                        cameraButton.visibility = View.VISIBLE
//                    }

                }
            }
            else -> {
                Toast.makeText(this, "Unrecognized request code", Toast.LENGTH_SHORT).show()
            }
        }

//        if (resultCode != Activity.RESULT_CANCELED) {
//            if (requestCode == 9090) {
//                //val imageData1: Bitmap = data?.extras?.get("data") as Bitmap
////                currentPhotoFileName
//                val thumbnail = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
//                val stream = ByteArrayOutputStream()
////                imageData1.compress(Bitmap.CompressFormat.PNG, 90, stream)
//                thumbnail.compress(Bitmap.CompressFormat.PNG, 90, stream)
//                var imageData: ByteArray = stream.toByteArray()
////                val bitmapData = BitmapFactory.decodeFile(currentPhotoFileName.)

////                imageView.setImageBitmap(imageData1)
//                imageView.setImageBitmap(thumbnail)
//                imageView.rotation = 90.toFloat()
//
//                val cameraButton = findViewById<Button>(R.id.camera_button)
//                cameraButton.visibility = View.GONE
//                val acceptButton = findViewById<ImageButton>(R.id.acceptButton)
//                val cancelButton = findViewById<ImageButton>(R.id.cancelButton)
//                acceptButton.visibility = View.VISIBLE
//                cancelButton.visibility = View.VISIBLE
//
//
//                //get description
//                var desc: EditText = findViewById(R.id.editText)
//                desc.visibility = View.VISIBLE
//                var post = Post()
//
//
//                acceptButton.setOnClickListener {
//                    post.img = imageData
//                    post.description = desc.text.toString()
//                    val intent = Intent(this, PostView1::class.java)
//                    intent.putExtra("newAsk", post as Serializable)
//                    this.startActivity(intent)
//                    finish()
//                }
//                cancelButton.setOnClickListener {
//                    //val uri = "@drawable/ic_launcher_background.xml"
//                    //val imageResource = resources.getIdentifier(uri, null, packageName)
//                    //var res = getResources().getDrawable(imageResource,@drawable/i);
//                    imageView.setImageResource(R.drawable.askicon);
//                    imageView.rotation = -180.toFloat()
//                    imageView.rotation = 0.toFloat()
//                    desc.text.clear()
//
//                    acceptButton.visibility = View.INVISIBLE
//                    cancelButton.visibility = View.INVISIBLE
//                    desc.visibility = View.INVISIBLE
//                    cameraButton.visibility = View.VISIBLE
//                }
//            }
//        }

        }
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

//private fun processCapturedPhoto() {
////    val cursor = contentResolver.query(Uri.parse(mCurrentPhotoPath),
////        Array(1) {android.provider.MediaStore.Images.ImageColumns.DATA},
////        null, null, null)
////    cursor.moveToFirst()
////    val photoPath = cursor.getString(0)
////    cursor.close()
////    val file = File(photoPath)
////    val uri = Uri.fromFile(file)
////
////    val height = resources.getDimensionPixelSize(R.dimen.photo_height)
////    val width = resources.getDimensionPixelSize(R.dimen.photo_width)
////
////    val request = ImageRequestBuilder.newBuilderWithSource(uri)
////        .setResizeOptions(ResizeOptions(width, height))
////        .build()
////    val controller = Fresco.newDraweeControllerBuilder()
////        .setOldController(imgvPhoto?.controller)
////        .setImageRequest(request)
////        .build()
////    imgvPhoto?.controller = controller
////}
//}


