package info.mychatbackend.controller;

import info.mychatbackend.modules.chat.message.model.ChatMessage;
import info.mychatbackend.service.MessageService;
import info.mychatbackend.service.MyChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/ws")
@CrossOrigin("*")
public class ChatController {

    private MyChatMessage messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/add/user")
    @SendTo("/app")
    public ChatMessage addUser(ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        return messageService.addUser(headerAccessor, chatMessage);
    }


}
