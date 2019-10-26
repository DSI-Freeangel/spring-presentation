package edu.example.spring

import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component
import kotlin.random.Random
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

@Component
class InsertRandomIntAnnotationPostProcessor : BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        bean::class.memberProperties
            .forEach { field ->
                val annotation = field.findAnnotation<InsertRandomInt>()
                if(null != annotation && field is KMutableProperty<*>) {
                    field.isAccessible = true
                    println("Going to update field ${field.name} with new random value...")
                    field.setter.call(bean, Random.nextInt(annotation.min, annotation.max))
                }
            }
        return bean
    }

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        println("Bean $beanName already initialized!")
        return bean
    }

}