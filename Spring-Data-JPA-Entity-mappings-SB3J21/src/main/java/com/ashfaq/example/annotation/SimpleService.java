package com.ashfaq.example.annotation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleService {

	@Autowired
	private BatteryRepository batteryRepository;

	public void doSomething() {
		List<Battery> list = batteryRepository.findAll().stream().toList();
		list.forEach(System.out::println);

	}
}
