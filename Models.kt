import java.util.*

data class User(var username: String, var id: Int, var admin: Boolean, var postPoints: Int, var answerPoints: Int)

data class UserFull(var user: User, var availPoints: Int, var avatar: Set<String>) //not sure what to put for avatar

data class UserNotifications(var notification: List<Notification>)

data class BrowseItem(var id: Int, var imgLink: String, var description: String)

data class BrowseList(var post: List<BrowseItem>)

data class Classroom(var id: Int, var name: String, var admins: Array<User>, var users: Array<User>)

data class Ask(var bI: BrowseItem, var answers: List<Answer> )

data class Answer(var answer: String, var poster: Poster, var postId: Int, var points: Int, var accepted: Boolean, var replies: Replies)

data class Poster(var username: String, var id: Int)

data class Replies(var id: Int, var poster: Poster, var message: String, var repliesToThis: TreeSet<Replies>) //not sure exactly what we want to use here


enum class NotificationEnum {
    STUFF
}

data class Notification(var id: Int, var type: NotificationEnum, var read: Boolean, var message: String, var time: Date, var linkId: Int)

