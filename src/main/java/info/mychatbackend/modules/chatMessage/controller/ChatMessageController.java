package info.mychatbackend.modules.chatMessage.controller;

import info.mychatbackend.modules.chatMessage.model.ChatMessage;
import info.mychatbackend.modules.chatMessage.service.ChatMessageOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ChatMessageController {

    private ChatMessageOperation messageOperation;

    public ChatMessageController(ChatMessageOperation messageOperation) {
        this.messageOperation = messageOperation;
    }

    @PostMapping(value = "/message/save")
    public ResponseEntity save(@RequestBody ChatMessage message) {
        return new ResponseEntity<>(messageOperation.create(message), HttpStatus.OK);
    }


}
