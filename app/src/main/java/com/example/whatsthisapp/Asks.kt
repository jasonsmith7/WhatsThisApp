package com.example.whatsthisapp

import java.util.*

data class Asks (
	var id: Long = 0,
	var img_link: String? = null,
	var description: String? = null,
	var poster_id: Int = 0,
	var class_id: Int = 0,
	var time: Date? = null,
	var answered: Boolean = false,
	val points: Int = 0
	)