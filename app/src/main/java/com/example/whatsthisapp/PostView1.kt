package com.example.whatsthisapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_post_view1.*
import android.view.ScaleGestureDetector
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_answer_view1.postImage
import kotlinx.android.synthetic.main.activity_post_view1.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_answer_view1.navigation as navigation1
import com.example.whatsthisapp.Application.Companion.colortheme


class PostView1 : AppCompatActivity() {

    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var mScaleFactor = 1.0f
    private val mImageView: ImageView? = null
    lateinit var imageView: ImageView
    lateinit var imageFilePath: String

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_ask -> {
                val intent = Intent(this, AskView1::class.java)
                this.startActivity(intent)
                // return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_answer -> {
                val intent = Intent(this, AnswerView2::class.java)
                this.startActivity(intent)
                //return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_explore -> {
                val intent = Intent(this, ExploreView1::class.java)
                this.startActivity(intent)
               // return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view1)
        //title = "Your Post"
        navigation.selectedItemId = R.id.navigation_ask
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //set color theme
//        var mApp = Application()
//        var bg = mApp.colortheme
        val bgcolor: ConstraintLayout = findViewById(R.id.container)
        if (colortheme == 2) bgcolor.background = resources.getDrawable(R.drawable.bgreen)
        if (colortheme == 3) bgcolor.background = resources.getDrawable(R.drawable.bred)

        val intent: Intent = getIntent()
        //val post1: Post? = intent.extras.get("newAsk") as? Post
        val post1 = intent.extras.get("post") as Post
//        val descript = intent.extras.get("desc") as String
//        val bundle = intent.extras as Bundle
//        var imageData1 = bundle.getByteArray("post") as ByteArray
        imageFilePath= post1.img.toString()
        imageView = findViewById<ImageView>(R.id.postImage)
        var imageData= setScaledBitmap()

        //config back button
        var back: ImageButton = findViewById(R.id.backButton)
        back.setOnClickListener{
            onBackPressed()
        }

//        val stream = ByteArrayOutputStream()
//        imageData.run {
//            compress(Bitmap.CompressFormat.PNG, 90, stream)
//        }
//        var imageData1: ByteArray = stream.toByteArray()

        //make post object
       // val post1 = Post()
        //post1.img=imageData1
        //post1.description=descript

//        imageView = findViewById<ImageView>(R.id.postImage)
//        postImage.setImageBitmap(BitmapFactory.decodeByteArray(imageData1,0,imageData1.size!!))
//        imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageData1,0,imageData1.size))

        imageView.setImageBitmap(imageData)
        imageView.rotation = 90.toFloat()

        Toast.makeText(applicationContext,"Your Post has been submitted!!!!!", Toast.LENGTH_LONG).show()

        val descr: TextView = findViewById(R.id.postDescription)
        descr.text = post1?.description.toString()
//        descr.text = "made it"
    }
    fun setScaledBitmap(): Bitmap {
        val imageViewWidth = imageView.width
        val imageViewHeight = imageView.height

        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(imageFilePath, bmOptions)
        val bitmapWidth = bmOptions.outWidth
        val bitmapHeight = bmOptions.outHeight

//        val scaleFactor = Math.min(bitmapWidth/imageViewWidth, bitmapHeight/imageViewHeight)
        val scaleFactor = 3
        bmOptions.inJustDecodeBounds = false
        bmOptions.inSampleSize = scaleFactor

        return BitmapFactory.decodeFile(imageFilePath, bmOptions)

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
}
