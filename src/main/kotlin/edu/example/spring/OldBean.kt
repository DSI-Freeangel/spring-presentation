package edu.example.spring

import org.springframework.stereotype.Component

@Component
@UseNewOne(reference = MrBean::class)
class OldBean : Persona {
    override fun doSomething() {
        println("Sorry I am too old to do anything")
    }
}