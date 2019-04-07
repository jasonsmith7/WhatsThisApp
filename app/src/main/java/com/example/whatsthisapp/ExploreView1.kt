package com.example.whatsthisapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_explore_view1.*
import com.example.whatsthisapp.MockClient
import java.net.URI
import android.provider.MediaStore
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView


class ExploreView1 : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_ask -> {
                val intent = Intent(this, AskView1::class.java)
                this.startActivity(intent)
                //return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_answer -> {
                val intent = Intent(this, AnswerView1::class.java)
                this.startActivity(intent)
                //return@OnNavigationItemSelectedListener true
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

        val client = MockClient()
        val posts = client.httpGetAllUserAsks()

        //var pV1: ImageView = findViewById(R.id.postImage1)
        DownloadImageTask( findViewById(R.id.postImage1)).execute(posts[0].bI?.imgLink)
        var des1: TextView = findViewById(R.id.textView)
        var des2: TextView = findViewById(R.id.textView2)
        des1.text = posts[0].bI?.description
        DownloadImageTask( findViewById(R.id.postImage2)).execute(posts[1].bI?.imgLink)
        des2.text = posts[1].bI?.description
        // var img1: String? = posts[0].bI?.imgLink.toString()
       // var img2: Uri = Uri.parse(img1)
//        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, posts[0].bI?.imgLink)
//        pV1.setImageBitmap(bitmap)
//        var pV2: ImageView = findViewById(R.id.postImage2)
//        val bitmap2 = MediaStore.Images.Media.getBitmap(this.contentResolver, posts[2].bI?.imgLink)
//        pV2.setImageBitmap(bitmap2)

        var butt1: ImageButton = findViewById(R.id.postImage1)
        var butt2: ImageButton = findViewById(R.id.postImage2)






        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
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
