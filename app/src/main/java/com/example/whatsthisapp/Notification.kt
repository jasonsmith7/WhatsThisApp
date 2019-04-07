package com.example.whatsthisapp
import java.util.*

data class Notification(var id: Int, var type: NotificationEnum, var read: Boolean, var message: String, var time: Date, var linkId: Int)