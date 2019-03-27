import android.util.Log

interface MockClientInterface {

    fun httpGetStatus(): Boolean

    fun httpGetAllAsks(): Array<String>

    fun httpGetAllUserAsks(): Array<String>

    fun httpGetAllUserAnswers(): Array<String>

    fun httpGetUserData(): String

    fun httpGetAskByID(): String

    fun httpGetClassroomByID(): String

    fun httpGetUserNotifications(): Array<String>

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

class MockClient : MockClientInterface {

    private val asks: Array<String> = arrayOf( "https://interactives.dallasnews.com/2018/the-disappearing-horny-toad/images/_lizards-poster.jpg", "")

    private val answers: Array<String> = arrayOf("horney toad", "horned lizard")

    private val notifications: Array<String> = arrayOf("notification1", "notification2", "notification3")


    override fun httpGetStatus(): Boolean{
        return true
    }

    override  fun httpGetAllAsks(): Array<String>{
        return asks

    }

    override fun httpGetAllUserAsks(): Array<String>{
        return asks

    }

    override fun httpGetAllUserAnswers(): Array<String>{
        return answers

    }

    override fun httpGetUserData(): String{
        return "my name????"

    }

    override fun httpGetAskByID(): String{
        var ask = "https://interactives.dallasnews.com/2018/the-disappearing-horny-toad/images/_lizards-poster.jpg"
        return ask
    }

    override fun httpGetClassroomByID(): String{
        return "my classroom"

    }

    override fun httpGetUserNotifications(): Array<String>{
        return notifications

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
