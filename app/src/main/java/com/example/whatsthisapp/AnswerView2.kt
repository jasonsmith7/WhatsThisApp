package com.example.whatsthisapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_answer_view1.*
import android.view.ScaleGestureDetector
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.*
import kotlinx.android.synthetic.main.answerdialog.view.*
import kotlinx.android.synthetic.main.activity_answer_view2.navigation as navigation1


class AnswerView2 : AppCompatActivity() {

    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var mScaleFactor = 1.0f
    private val mImageView: ImageView? = null
    // lateinit var toolbar: ActionBar

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_ask -> {
                val intent = Intent(this, AskView1::class.java)
               // intent.putExtra()
                this.startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_answer -> {
//                val intent = Intent(this, AnswerView2::class.java)
//                this.startActivity(intent)
                return@OnNavigationItemSelectedListener true
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
        setContentView(R.layout.activity_answer_view1)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.isSelected
//        title = "Answer Questions!"

        val client = MockClient()
        val posts = client.httpGetAllUserAsks()

        //config back button
        var back: ImageButton = findViewById(R.id.backButton)
        back.setOnClickListener{
            onBackPressed()
        }
        //var des1: TextView = findViewById(R.id.postDescription)
        //DownloadImageTask( findViewById(R.id.postImage)).execute(posts[0].bI?.imgLink)
        val i = (0 until 8).random()
        val post_img: String? = posts[i].bI?.imgLink as String
        val post_des: String? = posts[i].bI?.description as String
        var post_th: Int = posts[i].bI?.thumbs as Int
        var post_bu: Int = posts[i].bI?.bulbs as Int
        //val imageView = findViewById<ImageView>(R.id.postImage)
        //postImage.setImageBitmap(BitmapFactory.decodeByteArray(post1?.img,0,post1?.img?.size!!))
        DownloadImageTask(findViewById(R.id.postImage)).execute(post_img)
        //grab view components
        //des1.text = posts[0].bI?.description
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
        var count: Int = 0

        dunnoButt.setOnClickListener{
            if((count%2 == 0)) {
                thumb.text = (++post_th).toString()
                count++
                //dunnoButt.background = R.drawable.border as Drawable
                dunnoButt.setImageResource(R.drawable.thumbsclicked)
            }else{
                thumb.text = (--post_th).toString()
                count--
                //dunnoButt.background = R.drawable.my_button2 as Drawable
                dunnoButt.setImageResource(R.drawable.thumbs)
            }
        }
        answerButt.setOnClickListener{
            //
//            val artistsFragment = AnswerFragment.newInstance("","")
//            openFragment(artistsFragment)
            var customLayout = getLayoutInflater().inflate(R.layout.answerdialog, null);
            // Initialize a new instance of
            val builder = AlertDialog.Builder(this@AnswerView2)
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
                bulbs.text = (++post_bu).toString()
                if((count%2 != 0)) {
                    thumb.text = (--post_th).toString()
                    --count
                }
                // Change the app background color
                //container.setBackgroundColor(Color.RED)
            }


            // Display a negative button on alert dialog
//            builder.setNegativeButton("Cancel"){dialog,which ->
//                Toast.makeText(applicationContext,"Your answer has been canceled.",Toast.LENGTH_SHORT).show()
//            }


            // Display a neutral button on alert dialog
            builder.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(applicationContext,"What you doin' ova there ova here",Toast.LENGTH_SHORT).show()
            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()
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

    companion object {
        private class DownloadImageTask(val bmImage: ImageView?) : AsyncTask<String, Void, Bitmap>() {

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
                bmImage?.setImageBitmap(result)
            }
        }
    }


}
