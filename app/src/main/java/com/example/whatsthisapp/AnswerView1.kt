package com.example.whatsthisapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_answer_view1.*
import android.text.method.Touch.onTouchEvent
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.support.v4.view.ViewCompat.setScaleY
import android.support.v4.view.ViewCompat.setScaleX
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView


class AnswerView1 : AppCompatActivity() {

    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var mScaleFactor = 1.0f
    private val mImageView: ImageView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_ask -> {
                val intent = Intent(this, AskView1::class.java)
                this.startActivity(intent)
               // return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_answer -> {

                return@OnNavigationItemSelectedListener true
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
        setContentView(R.layout.activity_answer_view1)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.isSelected

        val post_img: String? = intent.extras.get("Post_Img") as String
        val post_des: String? = intent.extras.get("Post_Desc") as String
        val post_th: Int? = intent.extras.get("Post_Thumbs") as Int
        val post_bu: Int? = intent.extras.get("Post_Bulbs") as Int
        //val imageView = findViewById<ImageView>(R.id.postImage)
        //postImage.setImageBitmap(BitmapFactory.decodeByteArray(post1?.img,0,post1?.img?.size!!))
        DownloadImageTask( findViewById(R.id.postImage)).execute(post_img)
        //grab view components
        val descr: TextView = findViewById(R.id.postDescription)
        val thumb: TextView = findViewById(R.id.thumbCount)
        val bulbs: TextView = findViewById(R.id.answerCount)
        //update components with info
        descr.text = post_des
        thumb.text = post_th.toString()
        bulbs.text = post_bu.toString()



    }
    private inner class DownloadImageTask(internal var bmImage: ImageView) : AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            val urldisplay = urls[0]
            var mIcon11: Bitmap? = null
            try {
                val `in` = java.net.URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error", e.message)
                e.printStackTrace()
            }

            return mIcon11
        }

        override fun onPostExecute(result: Bitmap) {
            bmImage.setImageBitmap(result)
        }
    }


}
