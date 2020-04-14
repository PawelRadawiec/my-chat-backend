package info.mychatbackend.chatMessage.controller;

import info.mychatbackend.chatMessage.model.ChatMessage;
import info.mychatbackend.chatMessage.service.ChatMessageOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/ws")
public class ChatMessageController {

    private ChatMessageOperation messageOperation;

    public ChatMessageController(ChatMessageOperation messageOperation) {
        this.messageOperation = messageOperation;
    }

    @MessageMapping("/send/message")
    @SendTo("/topic/message")
    public ResponseEntity create(@Payload ChatMessage message) {
        return new ResponseEntity<>(messageOperation.create(message), HttpStatus.OK);
    }


}
