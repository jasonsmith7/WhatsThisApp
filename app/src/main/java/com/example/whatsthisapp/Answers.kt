package com.example.whatsthisapp

data class Answers (
	var id: Long = 0,
	var answer: Array<Answer>? = null,
	var poster_id: Int = 0,
	var ask_id: Int = 0,
	var accepted: Boolean = false,
	val points: Int = 0
	)