package com.example.whatsthisapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_explore_view1.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView


class ExploreView1 : AppCompatActivity() {
   // lateinit var toolbar: ActionBar
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_ask -> {
                val intent = Intent(this, AskView1::class.java)
                this.startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_answer -> {
                val intent = Intent(this, AnswerView2::class.java)
                this.startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_explore -> {
                //message.setText(R.string.title_explore)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_view1)
//        title = "Click an image for post details"
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.isSelected
        val client = MockClient()
        val posts = client.httpGetAllUserAsks()
        var des1: TextView = findViewById(R.id.textView)
        var des2: TextView = findViewById(R.id.textView2)
        var des3: TextView = findViewById(R.id.textView3)
        var des4: TextView = findViewById(R.id.textView4)

        //convert url string to bitmap
        DownloadImageTask( findViewById(R.id.postImage1)).execute(posts[0].bI?.imgLink)
        DownloadImageTask( findViewById(R.id.postImage2)).execute(posts[1].bI?.imgLink)
        DownloadImageTask( findViewById(R.id.postImage3)).execute(posts[2].bI?.imgLink)
        DownloadImageTask( findViewById(R.id.postImage4)).execute(posts[3].bI?.imgLink)
        des1.text = posts[0].bI?.description
        des2.text = posts[1].bI?.description
        des3.text = posts[2].bI?.description
        des4.text = posts[3].bI?.description

        //init image buttons
        var butt1: ImageButton = findViewById(R.id.postImage1)
        var butt2: ImageButton = findViewById(R.id.postImage2)
        var butt3: ImageButton = findViewById(R.id.postImage3)
        var butt4: ImageButton = findViewById(R.id.postImage4)

        //clicklisteners for buttons
        butt1.setOnClickListener{
            val intent = Intent(this, AnswerView1::class.java)
            intent.putExtra("Post_Img", posts[0].bI?.imgLink)
            intent.putExtra("Post_Desc", posts[0].bI?.description)
            intent.putExtra("Post_Thumbs", posts[0].bI?.thumbs)
            intent.putExtra("Post_Bulbs", posts[0].bI?.bulbs)
            this.startActivity(intent)
            finish()
        }
        butt2.setOnClickListener{
            val intent = Intent(this, AnswerView1::class.java)
            intent.putExtra("Post_Img", posts[1].bI?.imgLink)
            intent.putExtra("Post_Desc", posts[1].bI?.description)
            intent.putExtra("Post_Thumbs", posts[1].bI?.thumbs)
            intent.putExtra("Post_Bulbs", posts[1].bI?.bulbs)
            this.startActivity(intent)
            finish()
        }
        butt3.setOnClickListener{
            val intent = Intent(this, AnswerView1::class.java)
            intent.putExtra("Post_Img", posts[2].bI?.imgLink)
            intent.putExtra("Post_Desc", posts[2].bI?.description)
            intent.putExtra("Post_Thumbs", posts[2].bI?.thumbs)
            intent.putExtra("Post_Bulbs", posts[2].bI?.bulbs)
            this.startActivity(intent)
            finish()
        }
        butt4.setOnClickListener{
            val intent = Intent(this, AnswerView1::class.java)
            intent.putExtra("Post_Img", posts[3].bI?.imgLink)
            intent.putExtra("Post_Desc", posts[3].bI?.description)
            intent.putExtra("Post_Thumbs", posts[3].bI?.thumbs)
            intent.putExtra("Post_Bulbs", posts[3].bI?.bulbs)
            this.startActivity(intent)
            finish()
        }





    }

    private inner class DownloadImageTask(internal var bmImage: ImageButton) : AsyncTask<String, Void, Bitmap>() {

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
