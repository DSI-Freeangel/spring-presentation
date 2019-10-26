package edu.example.spring

import javax.annotation.PostConstruct

@LogExecutionTime
class MrBean: Persona {

    @InsertRandomInt(min = 1, max = 7)
    private var repeat: Int = 0

    init {
        println("I am alive!")
        println("My repeat value is equal to $repeat now")
    }

    @PostConstruct
    fun prepare() {
        println("Looking for repeat value ...")
        println("Going to repeat $repeat times")
    }

    @PostProxy
    override fun doSomething() {
        (1..repeat).forEach{ index -> println("Doing something important... $index") }
    }

}