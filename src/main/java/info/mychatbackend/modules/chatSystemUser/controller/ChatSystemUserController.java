package info.mychatbackend.modules.chatSystemUser.controller;

import info.mychatbackend.modules.chatSystemUser.model.ChatSystemUser;
import info.mychatbackend.modules.chatSystemUser.service.ChatSystemUserOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/registration")
    public ResponseEntity registration(@RequestBody ChatSystemUser systemUser) {
        return new ResponseEntity<>(operations.save(systemUser), HttpStatus.OK);
    }


}
