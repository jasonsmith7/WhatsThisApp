package com.example.whatsthisapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.Serializable

//data class Ask(var bI: BrowseItem?  = null,
 //              var answers: Array<Answer>?  = null)

data class Ask(var bI: BrowseItem?  = null,
//               var answers: Answers  = null)
               var answers: ArrayList<Answer>?  = null) : Serializable