package com.example.whatsthisapp
import java.util.*

//data class User(var username: String? = null, var id: Int? = null, var admin: Boolean? = null, var postPoints: Int?  = null, var answerPoints: Int?  = null)

//data class UserFull(var user: User?  = null, var availPoints: Int?  = null, var avatar: Set<String>?  = null) //not sure what to put for avatar

//data class UserNotifications(var notification: List<Notification>?  = null)

//data class BrowseItem(var id: Int?  = null, var imgLink: String?  = null, var description: String?  = null)

//data class BrowseList(var post: List<BrowseItem>?  = null)

//data class Classroom(var id: Int?  = null, var name: String?  = null, var admins: Array<User>?  = null, var users: Array<User>?  = null)

//data class Ask(var bI: BrowseItem?  = null, var answers: Array<Answer>?  = null)

//data class Answer(var answer: String?  = null, var poster: Poster?  = null, var postId: Int?  = null, var points: Int?  = null, var accepted: Boolean?  = null, var replies: Replies?  = null)

//data class Poster(var username: String?  = null, var id: Int?  = null)

//data class Replies(var id: Int?  = null, var poster: Poster?  = null, var message: String?  = null, var repliesToThis: TreeSet<Replies>?  = null) //not sure exactly what we want to use here


enum class NotificationEnum {
    STUFF
}

//data class Notification(var id: Int, var type: NotificationEnum, var read: Boolean, var message: String, var time: Date, var linkId: Int)

