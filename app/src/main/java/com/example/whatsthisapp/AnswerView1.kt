package com.example.whatsthisapp

import android.content.Intent
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



    }


}
