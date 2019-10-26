package edu.example.spring

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class PostProxyAnnotationContextListener( val beanFactory: ConfigurableListableBeanFactory
) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val applicationContext = event.applicationContext
        beanFactory.beanDefinitionNames
            .forEach { definitionName ->
                val definition = beanFactory.getBeanDefinition(definitionName)
                val beanClass = Class.forName(definition.beanClassName)
                val methods = beanClass.declaredMethods
                    .filter { method -> method.isAnnotationPresent(PostProxy::class.java) }
                if(methods.isNotEmpty()) {
                    val bean = applicationContext.getBean(definitionName)
                    methods.forEach { method ->
                        bean::class.java
                            .getDeclaredMethod(method.name, *method.parameterTypes)
                            .invoke(bean)
                    }
                }
            }
    }
}