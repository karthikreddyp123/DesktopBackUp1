package com.spring.SpringFrameworkTest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryAccessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext appContext=new ClassPathXmlApplicationContext("spring.xml");
        Test test=(Test)appContext.getBean("test");
        System.out.println(test.getTemp());
        test.print();
    }
}
