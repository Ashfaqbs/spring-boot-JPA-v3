package com.ashfaq.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashfaq.example.entity.TriggerLog;
import com.ashfaq.example.repo.TriggerLogRepository;

@Service
public class TriggerLogService {

    @Autowired
    private TriggerLogRepository triggerLogRepository;

    public List<TriggerLog> getAllLogs() {
        return triggerLogRepository.findAll();
    }
}
