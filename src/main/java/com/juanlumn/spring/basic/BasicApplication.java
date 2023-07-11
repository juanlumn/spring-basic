package com.juanlumn.spring.basic;

import com.juanlumn.spring.basic.service.binarysearch.BinarySearchServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		// We need to get the Bean from the Application Context
		ApplicationContext applicationContext = SpringApplication.run(BasicApplication.class, args);
		BinarySearchServiceImpl bean = applicationContext.getBean(BinarySearchServiceImpl.class);

		System.out.println(bean.binarySearch(new int[]{2,5,1,6,123}, 5));
	}

}
