package info.mychatbackend.controller;

import info.mychatbackend.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/ws")
public class ChatController {

    private SimpMessagingTemplate simpMessagingTemplate;

    public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/add/user")
    @SendTo("/app")
    public ChatMessage addUser(ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getFrom());
        return chatMessage;
    }

    @MessageMapping("/send/message")
    public Map<String, String> sendMessage(ChatMessage chatMessage) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("to", chatMessage.getTo());
        messageMap.put("from", chatMessage.getFrom());
        messageMap.put("message", chatMessage.getMessage());
        this.simpMessagingTemplate.convertAndSend("/topic" + chatMessage.getTo(), messageMap);
        this.simpMessagingTemplate.convertAndSend("/topic" + chatMessage.getFrom(), chatMessage);

        return messageMap;
    }


}
