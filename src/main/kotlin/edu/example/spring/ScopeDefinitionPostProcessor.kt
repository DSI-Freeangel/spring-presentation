package edu.example.spring

import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
class ScopeDefinitionPostProcessor : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        println("Customization of bean factory ...")
        beanFactory.getBeanNamesForAnnotation(UseNewOne::class.java).forEach {
            val beanDefinition = beanFactory.getBeanDefinition(it)
            val originalClass = Class.forName(beanDefinition.beanClassName)
            val annotation = originalClass.getAnnotation(UseNewOne::class.java)
            val newClass = annotation.reference
            beanDefinition.beanClassName = newClass.java.canonicalName
        }
    }
}