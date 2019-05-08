package com.example.whatsthisapp

import android.util.Log
import java.util.*

class MockClient : ClientInterface {

    //browse items and list
    private val browseItem1 = BrowseItem(8858, "https://www.squawkfox.com/wp-content/uploads/2010/12/webkinz.jpg", "I want one of these toys! what is this?", 4, 0)
    private val browseItem2 = BrowseItem(8989, "https://d1ia71hq4oe7pn.cloudfront.net/photo/64829071-480px.jpg", "What kind of bird is this?", 1, 0)
    private val browseItem3 = BrowseItem(8990, "https://media.gettyimages.com/videos/circuit-board-top-shot-burns-out-video-id143290377?s=640x640", "This fell out of an old computer at my house, what is it?", 8, 0)
    private val browseItem4 = BrowseItem(8991, "https://pmdvod.nationalgeographic.com/NG_Video_DEV/944/95/beetle-dung-kids_480x360.jpg", "I saw this bug in the mud, what is it?", 13, 0)
    private val browseItem5 = BrowseItem(8995, "https://www.pets4homes.co.uk/images/classifieds/2019/02/18/2214684/thumbs/3-white-cute-holland-lop-baby-bunnies-5c6aea5f35213.jpg","It's sooo cute! What is it?", 5, 0)
    private val browseItem6 = BrowseItem(8995, "https://a0.amlimg.com/NGY4N2I5ZWNiYTQwMTEzNGQzM2QzYzI1OThlNDNmMDc7GLKBddJrIluspBRacbbwaHR0cDovL21lZGlhLmFkc2ltZy5jb20vNTJjODUyNDBmM2Q1NjFhNTc5NDZkZGNlMDRlZWVhODQ4MGEyOWVjYjdlZTRiZTZiODBiN2RlODNkOTA5Y2ExOS5qcGd8fHx8fHw0ODB4MzYwfGh0dHA6Ly93d3cuYWR2ZXJ0cy5pZS9zdGF0aWMvaS93YXRlcm1hcmsucG5nfHx8.jpg","What is this? Some type of computer chip?", 9, 0)
    private val browseItem7 = BrowseItem(8995, "https://i.ytimg.com/vi/oty1RJRdxx0/hqdefault.jpg","What is this?", 25, 0)
    private val browseItem8 = BrowseItem(8995, "https://www.cell2get.com/images/7290_01.jpg","What kind of calculator is this?", 12, 0)
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
    private val answerListHT: ArrayList<Answer> = arrayListOf(answer1HT, answer2HT)

    //bi2
    private val answer1Swan =  Answer("it's a bird", poster2, 2222, 13, false, reply2)
    private val answer2Swan =  Answer("it's a plane", poster2, 2221, 13, false, reply2)
    private val answer3Swan =  Answer("it's a swan", poster2, 2223, 13, false, reply2)
    private val answerListSwan: ArrayList<Answer> = arrayListOf(answer1Swan, answer2Swan, answer3Swan)

    //bi3
    private val answerRock1 =  Answer("It's an old PCB board!", poster1, 1235, 5, false, reply3)
    private val answerRock2 =  Answer("How old is the computer? It looks old!", poster1, 1234, 5, false, reply3)
    private val answerListRock: ArrayList<Answer> = arrayListOf(answerRock1, answerRock2)

    //bi4
    private val answerBug1 =  Answer("It's a beetle!", poster2, 2221, 6, false, reply4)
    private val answerBug2 =  Answer("That right there is a stink bug", poster2, 2226, 6, false, reply4)
    private val answerBug3 =  Answer("Is that poop?", poster2, 2222, 6, false, reply4)
    private val answerBug4 =  Answer("A stinky one", poster2, 2223, 6, false, reply4)
    private val answerBug5 =  Answer("It's a dung beetle!!!!!", poster2, 2224, 6, false, reply4)
    private val answerListBug : ArrayList<Answer> = arrayListOf(answerBug1, answerBug2, answerBug3, answerBug4, answerBug5)

    //bi5
    private val answerMonsterThing =  Answer("A toy monster", poster2, 2225, 6, false, reply4)
    private val answerListMonsterThing: ArrayList<Answer> = arrayListOf(answerMonsterThing)

    //bi6
    private val answerPicacat1 =  Answer("That looks like a computer chip!", poster2, 2227, 6, false, reply4)
    private val answerPicacat2 =  Answer("I think it is broken!", poster2, 2228, 6, false, reply4)
    private val answerListPicacat: ArrayList<Answer> = arrayListOf(answerPicacat1, answerPicacat2)

    //bi7
    private val answerIDK1 =  Answer("Magnetic balls?", poster2, 2229, 6, false, reply4)
    private val answerIDK2 =  Answer("Something meant to be in the shape of a snake", poster2, 2220, 6, false, reply4)
    private val answerListIDK:ArrayList<Answer> = arrayListOf(answerIDK1, answerIDK2)

    //bi8
    private val answerMetal1 =  Answer("Ummm, that's a palm pilot!!", poster2, 2516, 6, false, reply4)
    private val answerMetal2 =  Answer("How old are you? That is a Blackberry phone and I had one!", poster2, 2616, 6, false, reply4)
    private val answerListMetal:ArrayList<Answer> = arrayListOf(answerMetal1, answerMetal2)

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

    override fun httpGetAllUserAnswers(userId: Int): ArrayList<Answer>{
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

