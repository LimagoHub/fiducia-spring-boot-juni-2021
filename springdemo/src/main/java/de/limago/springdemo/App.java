package de.limago.springdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	// private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) {


		ApplicationContext context
				= new ClassPathXmlApplicationContext("Beans.xml");




	}
}
