package com.example.whatsthisapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_ask_view1.*
import android.graphics.Matrix;
//import com.example.whatsthisapp.ask
import android.R.attr.pivotY
import android.R.attr.pivotX
import android.R.attr.angle
import android.graphics.BitmapFactory
import android.media.Image
import android.widget.EditText
import java.io.Serializable
import android.R.attr.bitmap
import android.graphics.Camera
import android.support.v7.app.ActionBar
import java.io.ByteArrayOutputStream


class AskView1 : AppCompatActivity() {

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
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.isSelected
        //title = "Ask A Question?"

        val cameraButton: Button = findViewById(R.id.camera_button)
        val im: ImageView = findViewById(R.id.imageView4)
        var desc: EditText = findViewById(R.id.editText)
        desc.visibility = View.INVISIBLE
        //im.visibility = View.INVISIBLE

        cameraButton.setOnClickListener {

            val cameraCheckPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA )

            if( cameraCheckPermission != PackageManager.PERMISSION_GRANTED){
                if( ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) == true){
                    val builder = AlertDialog.Builder(this)

                    builder.setMessage("I need to see you to work properly!!")
                        .setTitle("Permission required")
                        .setPositiveButton("OK") { _, _ ->
                            requestPermission()
                        }
                    val dialog = builder.create()
                    dialog.show()
                }
                else{
                    requestPermission()
                }
            }
            else{
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

    private fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 765)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for ((index, permission) in permissions.withIndex()){
            if( permission == Manifest.permission.CAMERA){
                if( grantResults[index] == PackageManager.PERMISSION_GRANTED){
                    launchCamera()
                }
            }
        }
    }

    private fun launchCamera(){

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, 9090)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if( requestCode == 9090){
            if( data != null ) {
                val imageData1: Bitmap = data.extras.get("data") as Bitmap
                val stream = ByteArrayOutputStream()
                imageData1.compress(Bitmap.CompressFormat.PNG, 90, stream)
                var imageData: ByteArray = stream.toByteArray()
                val imageView = findViewById<ImageView>(R.id.image_view)
                imageView.setImageBitmap(imageData1)
                imageView.rotation = 90.toFloat()
//                val matrix = Matrix()
//                imageView.scaleType = ImageView.ScaleType.MATRIX   //required
//                matrix.postRotate(angle.toFloat(), pivotX.toFloat(), pivotY.toFloat())
//                imageView.imageMatrix = matrix

                val cameraButton = findViewById<Button>(R.id.camera_button)
                cameraButton.visibility = View.GONE
                val acceptButton = findViewById<ImageButton>(R.id.acceptButton)
                val cancelButton = findViewById<ImageButton>(R.id.cancelButton)
                acceptButton.visibility = View.VISIBLE
                cancelButton.visibility = View.VISIBLE


                //get description
                var desc: EditText = findViewById(R.id.editText)
                desc.visibility = View.VISIBLE
                var post = Post()


                acceptButton.setOnClickListener{
                    post.img=imageData
                    post.description=desc.text.toString()
                    val intent = Intent(this, PostView1::class.java)
                    intent.putExtra("newAsk", post as Serializable)
                    this.startActivity(intent)
                    finish()
                }
                cancelButton.setOnClickListener{
                    //val uri = "@drawable/ic_launcher_background.xml"
                    //val imageResource = resources.getIdentifier(uri, null, packageName)
                    //var res = getResources().getDrawable(imageResource,@drawable/i);
                    imageView.setImageResource(R.drawable.askicon);
                    imageView.rotation = -180.toFloat()
                    imageView.rotation = 0.toFloat()
                    desc.text.clear()

                    acceptButton.visibility = View.INVISIBLE
                    cancelButton.visibility = View.INVISIBLE
                    desc.visibility = View.INVISIBLE
                    cameraButton.visibility = View.VISIBLE
                }
            }
        }
    }
}
