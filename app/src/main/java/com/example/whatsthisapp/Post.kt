package com.example.whatsthisapp

import android.graphics.Bitmap
import java.io.Serializable
import java.util.*

data class Post (
   // var id: Long = 0,
    var img: ByteArray? = null,
    var description: String? = null
//    var poster_id: Int = 0,
//    var class_id: Int = 0,
//    var time: Date? = null,
//    var answered: Boolean = false,
//    val points: Int = 0
) : Serializable