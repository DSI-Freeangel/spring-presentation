package edu.example.spring

import kotlin.annotation.AnnotationRetention.RUNTIME

@Retention(RUNTIME)
annotation class InsertRandomInt(val min: Int = 0, val max: Int = Int.MAX_VALUE)
