package info.mychatbackend.modules.chat.message.controller;

import info.mychatbackend.modules.chat.message.model.ChatMessage;
import info.mychatbackend.modules.chat.message.service.ChatMessageOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/ws")
public class ChatMessageWsController {

    private ChatMessageOperation messageOperation;

    public ChatMessageWsController(ChatMessageOperation messageOperation) {
        this.messageOperation = messageOperation;
    }

    @MessageMapping("/send.message")
    public ResponseEntity create(@Payload ChatMessage message) {
        return new ResponseEntity<>(messageOperation.create(message), HttpStatus.OK);
    }


}
