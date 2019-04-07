package com.example.whatsthisapp

import android.content.Intent
import android.graphics.BitmapFactory
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
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text
import java.io.Serializable


class PostView1 : AppCompatActivity() {

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
//            R.id.navigation_explore -> {
//                val intent = Intent(this, ExploreView1::class.java)
//                this.startActivity(intent)
//               // return@OnNavigationItemSelectedListener true
//            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view1)
        title = "Your Post"
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.isSelected
            //val intent: Intent = getIntent()
        val post1: Post? = intent.extras.get("newAsk") as? Post
        val imageView = findViewById<ImageView>(R.id.postImage)
        postImage.setImageBitmap(BitmapFactory.decodeByteArray(post1?.img,0,post1?.img?.size!!))
        val descr: TextView = findViewById(R.id.postDescription)
        descr.text = post1?.description.toString()

    }


}
