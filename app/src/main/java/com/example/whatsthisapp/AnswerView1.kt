package com.example.whatsthisapp

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.Image
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_answer_view1.*
import android.text.method.Touch.onTouchEvent
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.support.v4.view.ViewCompat.setScaleY
import android.support.v4.view.ViewCompat.setScaleX
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.answerdialog.view.*


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
        //toolbar.title = "Artists"
        val post_img: String? = intent.extras.get("Post_Img") as String
        val post_des: String? = intent.extras.get("Post_Desc") as String
        var post_th: Int = intent.extras.get("Post_Thumbs") as Int
        var post_bu: Int = intent.extras.get("Post_Bulbs") as Int
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

        val dunnoButt = findViewById<ImageButton>(R.id.dunnoButton)
        val answerButt = findViewById<ImageButton>(R.id.answerButton)
        var answerText: String = ""
        var ans: TextView = findViewById(R.id.answerView)

        dunnoButt.setOnClickListener{
            thumb.text = (post_th++).toString()
        }
        answerButt.setOnClickListener{
//
//            val artistsFragment = AnswerFragment.newInstance("","")
//            openFragment(artistsFragment)
            var customLayout = getLayoutInflater().inflate(R.layout.answerdialog, null);
            // Initialize a new instance of
            val builder = AlertDialog.Builder(this@AnswerView1)
            builder.setView(customLayout)
            var textbox: EditText= customLayout.editText4


            // Set the alert dialog title
            builder.setTitle("Submit Answer")

            // Display a message on alert dialog
            //builder.setMessage("Are you want to set the app background color to RED?")


            // Set a positive button and its click listener on alert dialog
            builder.setPositiveButton("Submit"){dialog, which ->
                // Do something when user press the positive button
                Toast.makeText(applicationContext,"Your answer has been submitted for review!",Toast.LENGTH_SHORT).show()
                answerText=textbox.text.toString()
                ans.text = answerText
                bulbs.text = (post_bu++).toString()
                // Change the app background color
                //container.setBackgroundColor(Color.RED)
            }


            // Display a negative button on alert dialog
//            builder.setNegativeButton("Cancel"){dialog,which ->
//                Toast.makeText(applicationContext,"Your answer has been canceled.",Toast.LENGTH_SHORT).show()
//            }


            // Display a neutral button on alert dialog
            builder.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(applicationContext,"You cancelled the dialog.",Toast.LENGTH_SHORT).show()
            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
        }








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
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        // val currFragment: Fragment? = getVisibleFragment()

        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.show(fragment)
        transaction.commit()
    }


}
