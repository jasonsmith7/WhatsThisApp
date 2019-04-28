package com.example.whatsthisapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

//data class Ask(var bI: BrowseItem?  = null,
 //              var answers: Array<Answer>?  = null)

data class Ask(var bI: BrowseItem?  = null,
               var answers: Answers  = null)