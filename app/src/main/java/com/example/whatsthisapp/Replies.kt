package com.example.whatsthisapp
import java.util.*

data class Replies(var id: Int?  = null, var poster: Poster?  = null, var message: String?  = null, var repliesToThis: TreeSet<Replies>?  = null) //not sure exactly what we want to use here
