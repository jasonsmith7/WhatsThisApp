package com.example.whatsthisapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_answer_view1.*
import android.view.ScaleGestureDetector
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.*
import kotlinx.android.synthetic.main.answerdialog.view.*
import android.view.View
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.animation.AnimatorSet as AnimatorSet1
import com.example.whatsthisapp.Application.Companion.colortheme


class AnswerView1 : AppCompatActivity() {

    lateinit var mScaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1.0f
    lateinit var mImageView: ImageView
    private var currentAnimator: Animator? = null
    private var shortAnimationDuration: Int = 0

   // lateinit var toolbar: ActionBar

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_ask -> {
                val intent = Intent(this, AskView1::class.java)
                this.startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_answer -> {

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

//        mImageView = findViewById(R.id.postImage)
//        mImageView.setOnClickListener({
//            zoomImageFromThumb(thumb1View, R.drawable.image1)
//        })
//        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
//        mImageView.bringToFront()


        navigation.selectedItemId = R.id.navigation_answer
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //set color theme
//        var mApp = Application()
//        var bg = mApp.colortheme
        val bgcolor: ConstraintLayout = findViewById(R.id.container)
        if (colortheme == 2) bgcolor.background = resources.getDrawable(R.drawable.bpink)
        if (colortheme == 3) bgcolor.background = resources.getDrawable(R.drawable.bred)

        //home button
        var home = findViewById<ImageButton>(R.id.homeButton)
        home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        title = "Answer Questions!"

        val post_img: String? = intent.extras.get("Post_Img") as String
        val post_des: String? = intent.extras.get("Post_Desc") as String
        var post_th: Int = intent.extras.get("Post_Thumbs") as Int
        var post_bu: Int = intent.extras.get("Post_Bulbs") as Int
        var i = intent.extras.get("index") as Int

//        val client = MockClient()
//        val posts = client.httpGetAllUserAsks()

        //start fragment and pass i
        val ansFragment = AnswerFragment.newInstance(i, null)
        openFragment(ansFragment)


        //program back button
        var back: ImageButton = findViewById(R.id.backButton)
        back.setOnClickListener{
//            onBackPressed()
//            finish()
            this.onBackPressed()
//            val intent = Intent(this, ExploreView1::class.java)
//            this.startActivity(intent)
//            finishActivity(44)
        }
        //val imageView = findViewById<ImageView>(R.id.postImage)
        //postImage.setImageBitmap(BitmapFactory.decodeByteArray(post1?.img,0,post1?.img?.size!!))
        DownloadImageTask( findViewById(R.id.postImage)).execute(post_img)
        mImageView = findViewById(R.id.postImage)

//        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        //grab view components
        val descr: TextView = findViewById(R.id.postDescription)
        val thumb: TextView = findViewById(R.id.thumbCount5)
//        val bulbs: TextView = findViewById(R.id.answerCount)
        //update components with info
        descr.text = post_des
        thumb.text = post_th.toString()
//        bulbs.text = post_bu.toString()

        val dunnoButt = findViewById<ImageButton>(R.id.dunnoButton5)
        val answerButt = findViewById<Button>(R.id.answerButton)
        var answerText: String = ""
//        var ans: TextView = findViewById(R.id.answerView)
        var count: Int = 0
        dunnoButt.setOnClickListener{
            if((count%2 == 0)) {
                thumb.text = (++post_th).toString()
                count++
               // dunnoButt.background.
                dunnoButt.setImageResource(R.drawable.thumbsclicked)
                dunnoButt.setBackgroundResource(R.drawable.bordertrans)
            }else{
                thumb.text = (--post_th).toString()
                count--
               // dunnoButt.background = R.drawable.my_button2 as Drawable
                dunnoButt.setImageResource(R.drawable.thumbs)
                dunnoButt.setBackgroundResource(R.drawable.my_button2)
            }
        }
        answerButt.setOnClickListener{
//
//            val artistsFragment = AnswerFragment.newInstance("","")
//            openFragment(artistsFragment)
            var customLayout = getLayoutInflater().inflate(R.layout.answerdialog, null)
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
                answerText = "Your Answer: \n" + answerText

                val ansFragment = AnswerFragment.newInstance(i, answerText)
                openFragment(ansFragment)

            }


            // Display a negative button on alert dialog
//            builder.setNegativeButton("Cancel"){dialog,which ->
//                Toast.makeText(applicationContext,"Your answer has been canceled.",Toast.LENGTH_SHORT).show()
//            }


            // Display a neutral button on alert dialog
            builder.setNeutralButton("Cancel"){_,_ ->
                Toast.makeText(applicationContext,"You cancelled your answer!!!",Toast.LENGTH_SHORT).show()
            }

            // Finally, make the alert dialog using builder
            val dialog: AlertDialog = builder.create()

            // focus keyboard potential hack from:
            // https://stackoverflow.com/questions/3455235/when-using-alertdialog-builder-with-edittext-the-soft-keyboard-doesnt-pop
            dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

            // Display the alert dialog on app interface
            dialog.show()
        }

        //for image enlarging
        mImageView.setOnClickListener {
            zoomImageFromThumb(mImageView, mImageView.drawable)
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
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.show(fragment)
        transaction.commit()
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = Math.max(
                0.1f,
                Math.min(mScaleFactor, 10.0f)
            )
            mImageView.setScaleX(mScaleFactor)
            mImageView.setScaleY(mScaleFactor)
            return true
        }
    }

//    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
//        mScaleGestureDetector.onTouchEvent(motionEvent)
//        return true
//    }

    private fun zoomImageFromThumb(thumbView: View, imageResId: Drawable) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        currentAnimator?.cancel()

        // Load the high-resolution "zoomed-in" image.
        val expandedImageView: ImageView = findViewById(R.id.expanded_image)
        expandedImageView.setImageDrawable(imageResId)

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBoundsInt)
        findViewById<View>(R.id.container)
            .getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            // Extend start bounds horizontally
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // Extend start bounds vertically
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.alpha = 0f
        expandedImageView.visibility = View.VISIBLE

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        currentAnimator = AnimatorSet1().apply {
            play(ObjectAnimator.ofFloat(
                expandedImageView,
                View.X,
                startBounds.left,
                finalBounds.left)
            ).apply {
                with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top, finalBounds.top))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    currentAnimator = null
                }
            })
            start()
        }

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        expandedImageView.setOnClickListener {
            currentAnimator?.cancel()

            // Animate the four positioning/sizing properties in parallel,
            // back to their original values.
            currentAnimator = AnimatorSet1().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = shortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }
                })
                start()
            }
        }
    }


}
