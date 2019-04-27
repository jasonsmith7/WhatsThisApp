package com.example.whatsthisapp

data class Answer (
    var answer: String?  = null,
    var poster: Poster?  = null,
    var postId: Int?  = null,
    var points: Int?  = null,
    var accepted: Boolean?  = null,
    var replies: Replies?  = null
)
