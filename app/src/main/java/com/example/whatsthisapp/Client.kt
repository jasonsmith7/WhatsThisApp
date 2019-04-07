import android.util.Log
import java.util.*
import java.util.Collections.EMPTY_SET
interface ClientInterface {

    fun httpGetStatus(): Boolean

    fun httpGetAllAsks(id: Int): Ask

    fun httpGetAllUserAsks(userId: Int): Array<Ask>

    fun httpGetAllUserAnswers(userId: Int): Array<Answer>

    fun httpGetUserData(id: Int): User

    fun httpGetAskByID(id: Int): Ask

    fun httpGetClassroomByID(id: Int): Classroom

    fun httpGetUserNotifications(userId: Int): Array<Notification>

    fun httpPostCreateUser()

    fun httpPostCreateAsks()

    fun httpPostAnswer()

    fun httpPostReply()

    fun httpPostNewClassroom()

    fun httpPostVote()

    fun httpPutUpdateAdminClassroom()

    fun httpPutUpdateUserClassroom()

    fun httpPutSetNotificationRead()

}

class MockClient : ClientInterface {

    //browse items and list
    private val browseItem1 = BrowseItem(8858, "https://interactives.dallasnews.com/2018/the-disappearing-horny-toad/images/_lizards-poster.jpg", "description1")
    private val browseItem2 = BrowseItem(8989, "https://www.wonderplugin.com/videos/demo-image0.jpg", "description2")

    //posters
    private val poster1 = Poster("poster 1", 4567)
    private val poster2 = Poster("poster 2", 4938)

    //for the reply
    private val set = TreeSet<Replies>()

    //replies
    private val reply1 = Replies(7895, poster1,"reply", set) //not sure exactly what we want to use here
    private val reply2 = Replies(7895, poster1,"reply", set) //not sure exactly what we want to use here

    //answers, individual and list of them
    private val answer1 =  Answer("answer 1", poster1, 1234, 10, false, reply1)
    private val answer2 =  Answer("answer 2", poster2, 2222, 13, false, reply2)
    private val listAnswers1: Array<Answer> = arrayOf(answer1)
    private val listAnswers2: Array<Answer> = arrayOf(answer1, answer2)


    //asks, individual and list
    private val ask1  = Ask(browseItem1, listAnswers1)
    private val ask2  = Ask(browseItem2, listAnswers2)
    private val allAsk: Array<Ask> = arrayOf(ask1, ask2)

    //user
    private val user1 = User("name", 4567, false, 22, 10)
    private val admin = User("adminuser", 9999, true, 0, 0)
    private val userList: Array<User> = arrayOf(user1)
    private val adminList: Array<User> = arrayOf(admin)

    private val date = Date()
    //notifications
    private val notification1 = Notification(3333, NotificationEnum.STUFF, false, "notification", date, 4567)
    private val notification2 = Notification(3334, NotificationEnum.STUFF, true, "notification2", date, 4567)
    private val listNotifications: Array<Notification> = arrayOf(notification1, notification2)

    //classroom
    private val classroom = Classroom(5555, "class", adminList, userList)

    override fun httpGetStatus(): Boolean {
        return true
    }

    override  fun httpGetAllAsks(id: Int): Ask {
        return ask1
    }

    override fun httpGetAllUserAsks(userId: Int): Array<Ask>{
        return allAsk
    }

    override fun httpGetAllUserAnswers(userId: Int): Array<Answer>{
        return listAnswers2
    }

    override fun httpGetUserData(id: Int): User{
        return user1
    }

    override fun httpGetAskByID(id: Int): Ask{
        return ask1
    }

    override fun httpGetClassroomByID(id: Int): Classroom  {
        return classroom
    }

    override fun httpGetUserNotifications(userId: Int): Array<Notification>{
        return listNotifications
    }

    override fun httpPostCreateUser(){
        Log.i("","called Post for Answering" )
    }

    override fun httpPostCreateAsks(){
        Log.i("","called Post for Answering" )
    }

    override fun httpPostAnswer(){
        Log.i("","called Post for Answering" )
    }

    override fun httpPostReply(){
        Log.i("","called Post for new reply" )

    }

    override fun httpPostNewClassroom(){
        Log.i("","called Post for creating classroom" )

    }

    override fun httpPostVote(){
        Log.i("","called Post for up vote" )

    }

    override fun httpPutUpdateAdminClassroom(){
        Log.i("","called Put for updating admin for classroom" )

    }

    override fun httpPutUpdateUserClassroom(){
        Log.i("","called Put for updated users in classroom" )
    }

    override fun httpPutSetNotificationRead(){
        Log.i("","called Put for changing read variable" )

    }
}
