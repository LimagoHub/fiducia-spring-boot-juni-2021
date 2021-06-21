package de.limago.springdemo;

import de.limago.springdemo.math.Calculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;


import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {


	public static void main(String[] args) {


		//final AbstractApplicationContext context 	= new ClassPathXmlApplicationContext("Beans.xml");
		final AbstractApplicationContext context 	= new AnnotationConfigApplicationContext(App.class);
		context.registerShutdownHook();



	}
}
