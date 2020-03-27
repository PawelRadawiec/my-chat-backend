package info.mychatbackend.controller;

import info.mychatbackend.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/ws")
@CrossOrigin("*")
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
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        String sendTo = chatMessage.getTo();
        if (!StringUtils.isEmpty(sendTo)) {
            this.simpMessagingTemplate.convertAndSend("/topic/" + sendTo, chatMessage);
            this.simpMessagingTemplate.convertAndSend("/topic/" + chatMessage.getFrom(), chatMessage);
        } else {
            this.simpMessagingTemplate.convertAndSend("/topic", chatMessage);
        }
        return chatMessage;
    }


}
