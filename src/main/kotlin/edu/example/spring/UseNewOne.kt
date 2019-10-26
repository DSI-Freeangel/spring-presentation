package edu.example.spring

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
annotation class UseNewOne(val reference: KClass<out Any>)
