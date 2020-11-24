package com.example.chatdemo.Controller;

import com.example.chatdemo.Model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    private ChatHistory chatHistory;
    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/send/message")
    @SendTo("/message")
    public ResponseEntity<?> sendMessage(Content content){
        Map<String,String> data = new HashMap<>();
        data.put("username",content.getUsername());
        data.put("content",content.getContent());
        data.put("timestamp", Long.toString(System.currentTimeMillis()));
        chatHistory.save(data);
//        System.out.println(message);
//        System.out.println(content);
//        System.out.println("message");
//        this.template.convertAndSend("/message",  message);
        return ResponseEntity.ok(content);
    }
    @RequestMapping("/history")
    @CrossOrigin("http://localhost:3000")
    public List<Map<String, String>> getChatHistory() {
        return chatHistory.get();
    }
}
