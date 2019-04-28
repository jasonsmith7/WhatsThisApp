package com.example.whatsthisapp

import android.util.Log
import java.util.*

class MockClient : ClientInterface {

    //browse items and list
    private val browseItem1 = BrowseItem(8858, "https://interactives.dallasnews.com/2018/the-disappearing-horny-toad/images/_lizards-poster.jpg", "What the heck is this type of dog?", 4, 0)
    private val browseItem2 = BrowseItem(8989, "https://www.wonderplugin.com/videos/demo-image0.jpg", "This bird is beautiful!!! What is it and why is it swimming???", 1, 0)
    private val browseItem3 = BrowseItem(8990, "https://amp.businessinsider.com/images/5bacdeec254699e6078b4568-750-445.jpg", "This is a cool rock I found on the field trip. It glows!!", 8, 0)
    private val browseItem4 = BrowseItem(8991, "https://i.pinimg.com/originals/5e/24/9c/5e249ce44709a5675ed9b98998ea8ec7.jpg", "I saw this yesterday in Yosemite Nation Park. What kind of bug is this??", 13, 0)
    private val browseItem5 = BrowseItem(8995, "https://creativebeacon.com/wp-content/uploads/2012/11/creature-cool-stuff-for-designers.jpg","I saw this at the toy store! Is is a Furby??", 5, 0)
    private val browseItem6 = BrowseItem(8995, "https://wallpaper21.com/wp-content/uploads/2017/08/Finding-cool-stuff-from-other-places-in-the-world-wallpaper-wpt7404542.jpg","I don't even know how to explain this one!", 9, 0)
    private val browseItem7 = BrowseItem(8995, "https://i.ytimg.com/vi/oty1RJRdxx0/hqdefault.jpg","What is this?", 25, 0)
    private val browseItem8 = BrowseItem(8995, "https://1.bp.blogspot.com/_eeYhBGkKfS0/S0o2M4Mh5wI/AAAAAAAAGqA/S6hwP50gJak/s400/metalwork+2010+030.jpg","What is this and why do we use it?", 12, 0)
    //posters
    private val poster1 = Poster("poster 1", 4567)
    private val poster2 = Poster("poster 2", 4938)

    //for the reply
    private val set = TreeSet<Replies>()

    //replies
    private val reply1 = Replies(7895, poster1,"reply", set) //not sure exactly what we want to use here
    private val reply2 = Replies(7895, poster1,"reply", set) //not sure exactly what we want to use here
    private val reply3 = Replies(7892, poster2,"reply", set) //not sure exactly what we want to use here
    private val reply4 = Replies(7893, poster2,"reply", set) //not sure exactly what we want to use here
    private val reply5 = Replies(7893, poster2,"reply", set)
    private val reply6 = Replies(7893, poster2,"reply", set)
    private val reply7 = Replies(7893, poster2,"reply", set)
    private val reply8 = Replies(7893, poster2,"reply", set)
    //answers, individual and list of them
    //bi1
    private val answer1HT =  Answer("I don't think that's a dog...", poster1, 1234, 10, false, reply1)
    private val answer2HT =  Answer("That's a horned lizard!", poster2, 1234, 10, false, reply1)
    private val answerListHT: Array<Answer> = arrayOf(answer1HT, answer2HT)

    //bi2
    private val answer1Swan =  Answer("it's a bird", poster2, 2222, 13, false, reply2)
    private val answer2Swan =  Answer("it's a plane", poster2, 2221, 13, false, reply2)
    private val answer3Swan =  Answer("it's a swan", poster2, 2223, 13, false, reply2)
    private val answerListSwan: Array<Answer> = arrayOf(answer1Swan, answer2Swan, answer3Swan)

    //bi3
    private val answerRock1 =  Answer("It's some type of gem", poster1, 1235, 5, false, reply3)
    private val answerRock2 =  Answer("you sure it's real?", poster1, 1234, 5, false, reply3)
    private val answerListRock: Array<Answer> = arrayOf(answerRock1, answerRock2)

    //bi4
    private val answerBug1 =  Answer("a flat one", poster2, 2221, 6, false, reply4)
    private val answerBug2 =  Answer("a cool one", poster2, 2226, 6, false, reply4)
    private val answerBug3 =  Answer("a red one", poster2, 2222, 6, false, reply4)
    private val answerBug4 =  Answer("a pink one", poster2, 2223, 6, false, reply4)
    private val answerBug5 =  Answer("a redish pink, cool, flat bug", poster2, 2224, 6, false, reply4)
    private val answerListBug : Array<Answer> = arrayOf(answerBug1, answerBug2, answerBug3, answerBug4, answerBug5)

    //bi5
    private val answerMonsterThing =  Answer("A toy monster", poster2, 2225, 6, false, reply4)
    private val answerListMonsterThing: Array<Answer> = arrayOf(answerMonsterThing)

    //bi6
    private val answerPicacat1 =  Answer("Picacat", poster2, 2227, 6, false, reply4)
    private val answerPicacat2 =  Answer("photoshop", poster2, 2228, 6, false, reply4)
    private val answerListPicacat: Array<Answer> = arrayOf(answerPicacat1, answerPicacat2)

    //bi7
    private val answerIDK1 =  Answer("magnetic balls?", poster2, 2229, 6, false, reply4)
    private val answerIDK2 =  Answer("something meant to be in the shape of a snake", poster2, 2220, 6, false, reply4)
    private val answerListIDK:Array<Answer> = arrayOf(answerIDK1, answerIDK2)

    //bi8
    private val answerMetal1 =  Answer("A part of a chair", poster2, 2516, 6, false, reply4)
    private val answerMetal2 =  Answer("Metal", poster2, 2616, 6, false, reply4)
    private val answerListMetal:Array<Answer> = arrayOf(answerMetal1, answerMetal2)

    //asks, individual and list
    private val ask1  = Ask(browseItem1, answerListHT)
    private val ask2  = Ask(browseItem2, answerListSwan)
    private val ask3  = Ask(browseItem3, answerListRock)
    private val ask4  = Ask(browseItem4, answerListBug)
    private val ask5  = Ask(browseItem5, answerListMonsterThing)
    private val ask6  = Ask(browseItem6, answerListPicacat)
    private val ask7  = Ask(browseItem7, answerListIDK)
    private val ask8  = Ask(browseItem8, answerListMetal)
    private val allAsk: Array<Ask> = arrayOf(ask1, ask2, ask3, ask4, ask5, ask6, ask7, ask8)

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

//    override  fun httpGetAllAsks(): Array<Ask> {
//        return allAsks
//    }

    override  fun httpGetAllAsks(id: Int): Ask {
        return ask1
    }

    override fun httpGetAllUserAsks(): Array<Ask>{
        return allAsk
    }

    override fun httpGetAllUserAnswers(userId: Int): Array<Answer>{
        return answerListSwan
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

