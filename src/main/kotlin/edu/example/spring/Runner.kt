package edu.example.spring

import org.springframework.context.annotation.AnnotationConfigApplicationContext

fun main(args: Array<String>) {
    val context = AnnotationConfigApplicationContext("edu.example.spring")
    context.getBean(Persona::class.java)
}