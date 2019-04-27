package com.example.whatsthisapp
import java.util.*


data class BrowseItem(var id: Int?  = null,
                      var imgLink: String?  = null,
                      var description: String?  = null,
                      var thumbs: Int = 0,
                      var bulbs: Int = 0)