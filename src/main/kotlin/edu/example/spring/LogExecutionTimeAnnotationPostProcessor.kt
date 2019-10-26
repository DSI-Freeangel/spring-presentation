package edu.example.spring

import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component
import java.lang.reflect.Proxy
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

@Component
class LogExecutionTimeAnnotationPostProcessor : BeanPostProcessor {
    private val beanClasses = mutableMapOf<String, KClass<out Any>>()

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        val kClass = bean::class
        if (kClass.findAnnotation<LogExecutionTime>() != null) {
            beanClasses[beanName] = kClass
        }
        return bean
    }

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        beanClasses[beanName]?.let {
            return Proxy.newProxyInstance(it.java.classLoader, it.java.interfaces) { _, method, args ->
                println("Going to log method '${method.name}' execution time")
                val before = System.nanoTime()
                if (null != args) method.invoke(bean, *args) else method.invoke(bean)
                val after = System.nanoTime()
                println("Method '${method.name}' execution time = ${after - before} nanoseconds")
            }
        }
        return bean
    }
}