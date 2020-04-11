package info.mychatbackend.chatMessage.controller;

import info.mychatbackend.chatMessage.model.ChatMessage;
import info.mychatbackend.chatMessage.service.ChatMessageOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/message")
public class ChatMessageController {

    private ChatMessageOperation messageOperation;

    public ChatMessageController(ChatMessageOperation messageOperation) {
        this.messageOperation = messageOperation;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody ChatMessage message) {
        return new ResponseEntity<>(messageOperation.create(message), HttpStatus.OK);
    }


}
