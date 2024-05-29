package com.example.urinoirapp.Controller;



import com.example.urinoirapp.Model.TestData;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendTestDataUpdate(TestData testData) {
        simpMessagingTemplate.convertAndSend("/topic/testData", testData);
    }
}