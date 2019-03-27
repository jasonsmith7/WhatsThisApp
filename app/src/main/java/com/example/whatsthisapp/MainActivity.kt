package com.example.whatsthisapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    val askButton = findViewById<ImageButton>(R.id.askButton)
    val answerButton: ImageButton = findViewById(R.id.answerButton)
    val exploreButton: ImageButton = findViewById(R.id.exploreButton)

    askButton.setOnClickListener{
        val intent = Intent(this, AskView1::class.java)
        this.startActivity(intent)
    }

    answerButton.setOnClickListener{
        val intent = Intent(this, AnswerView1::class.java)
        this.startActivity(intent)
    }
    exploreButton.setOnClickListener{
        val intent = Intent(this, ExploreView1::class.java)
        this.startActivity(intent)
    }


    }
}
