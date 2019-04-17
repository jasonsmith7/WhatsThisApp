package com.example.whatsthisapp
import android.app.Application
//import com.facebook.drawee.backends.pipeline.Fresco

class Application: Application() {
    companion object {
        lateinit var instance: com.example.whatsthisapp.Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
//        Fresco.initialize(this)
    }
}