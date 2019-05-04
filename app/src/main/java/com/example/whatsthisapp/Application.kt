package com.example.whatsthisapp
import android.app.Application
//import com.facebook.drawee.backends.pipeline.Fresco

class Application: Application() {
//    var colortheme = 1
    companion object {
        lateinit var instance: com.example.whatsthisapp.Application
        var colortheme = 1
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
//        Fresco.initialize(this)
    }

    fun changeCT(x: Int){
        colortheme = x
    }
}