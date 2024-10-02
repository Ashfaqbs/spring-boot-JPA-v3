package com.ashfaq.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.example.entity.TriggerLog;
import com.ashfaq.example.service.TriggerLogService;

@RestController
@RequestMapping("/api/trigger-logs")
public class TriggerLogController {

	@Autowired
	private TriggerLogService triggerLogService;

	private static final Logger logger = LoggerFactory.getLogger(TriggerLogController.class);

	@GetMapping
	public ResponseEntity<List<TriggerLog>> fetchLogs() {
		List<TriggerLog> logs = triggerLogService.getAllLogs();

		// Log the data
		logs.forEach(log -> logger.info("Trigger Log: Action = {}, Time = {}, Description = {}", log.getActionType(),
				log.getTriggeredAt(), log.getDescription()));

		return new ResponseEntity<>(logs, HttpStatus.OK);
	}
}
