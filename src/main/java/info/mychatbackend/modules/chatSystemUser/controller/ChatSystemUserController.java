package info.mychatbackend.modules.chatSystemUser.controller;

import info.mychatbackend.modules.chatSystemUser.service.ChatSystemUserOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/chat-system-user")
public class ChatSystemUserController {

    private ChatSystemUserOperations operations;

    public ChatSystemUserController(ChatSystemUserOperations operations) {
        this.operations = operations;
    }

    @GetMapping(value = "/list")
    public ResponseEntity getUserList() {
        return new ResponseEntity<>(operations.getUserList(), HttpStatus.OK);
    }


}
