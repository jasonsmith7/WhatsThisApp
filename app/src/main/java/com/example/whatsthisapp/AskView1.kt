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
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_ask_view1.*
import com.example.whatsthisapp.ask


class AskView1 : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_ask -> {
              //  message.setText(R.string.title_ask)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_answer -> {
              //  message.setText(R.string.title_answer)
                val intent = Intent(this, AnswerView1::class.java)
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
        setContentView(R.layout.activity_ask_view1)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val cameraButton: Button = findViewById(R.id.camera_button)


        cameraButton.setOnClickListener {

            val cameraCheckPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA )

            if( cameraCheckPermission != PackageManager.PERMISSION_GRANTED){
                if( ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) == true){
                    val builder = AlertDialog.Builder(this)

                    builder.setMessage("I need to see you to work properly!!")
                        .setTitle("Permission required")
                        .setPositiveButton("OK") { dialog, id ->
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

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 9090)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if( requestCode == 9090){

            if( data != null ) {
                val imageData: Bitmap = data.extras.get("data") as Bitmap

                val imageView = findViewById<ImageView>(R.id.image_view)
                imageView.setImageBitmap(imageData)
            }
        }
    }
}
