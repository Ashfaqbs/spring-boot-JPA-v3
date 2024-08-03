package com.ashfaq.example.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleRunner implements CommandLineRunner {

@Autowired
SimpleService service;

	@Override
	public void run(String... args) throws Exception {
//        Battery battery1 = new Battery();
//        battery1.setBrand("Duracell");
//        battery1.setType("AA");
//        battery1.setCapacity(1500);
//
//        Battery battery2 = new Battery();
//        battery2.setBrand("Energizer");
//        battery2.setType("AAA");
//        battery2.setCapacity(1200);
//
//        batteryRepository.save(battery1);
//        batteryRepository.save(battery2);

//		batteryRepository.findAll().stream().forEach(System.out::println);
		
		service.doSomething();
	}
}
