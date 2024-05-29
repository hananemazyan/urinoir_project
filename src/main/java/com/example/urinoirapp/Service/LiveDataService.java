package com.example.urinoirapp.Service;

import com.example.urinoirapp.Model.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LiveDataService {

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedRate = 5000) // Example: sends message every 5 seconds
    public void sendTestData() {
        TestData testData = new TestData();
        testData.setSecond((int) (System.currentTimeMillis() / 1000));
        testData.setVolume(Math.random() * 100); // Example data
        template.convertAndSend("/topic/testData", testData);
    }
}
