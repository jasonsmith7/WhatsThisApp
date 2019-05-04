package com.example.whatsthisapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.whatsthisapp.Application.Companion.colortheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get app to save theme
//        var mApp = Application()
//        var bg = mApp.colortheme

        title = "What's This?"
        val askButton = findViewById<ImageButton>(R.id.askButton)
        val answerButton: ImageButton = findViewById(R.id.answerButton)
        val exploreButton: ImageButton = findViewById(R.id.exploreButton)
        //theme buttons
        val gtheme: Button = findViewById(R.id.gTheme)
        val btheme: Button = findViewById(R.id.bTheme)
        val rtheme: Button = findViewById(R.id.rTheme)
        val bgcolor: ConstraintLayout = findViewById(R.id.content)
        var ctext: TextView = findViewById(R.id.colorText)

        askButton.setOnClickListener{
            val intent = Intent(this, AskView1::class.java)
            this.startActivity(intent)
        }

        answerButton.setOnClickListener{
            val intent = Intent(this, AnswerView2::class.java)
            this.startActivity(intent)
        }
        exploreButton.setOnClickListener{
            val intent = Intent(this, ExploreView1::class.java)
            this.startActivity(intent)
        }

        //set themes
        btheme.setOnClickListener{
            bgcolor.background = resources.getDrawable(R.drawable.bgv2)
//           mApp.changeCT(1)
            colortheme = 1
        }
        gtheme.setOnClickListener{
            bgcolor.background = resources.getDrawable(R.drawable.bgv2green)
//            mApp.changeCT(2)
            colortheme = 2
        }
        rtheme.setOnClickListener{
            bgcolor.background = resources.getDrawable(R.drawable.bgv2red)
//            mApp.changeCT(3)
            colortheme = 3
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
