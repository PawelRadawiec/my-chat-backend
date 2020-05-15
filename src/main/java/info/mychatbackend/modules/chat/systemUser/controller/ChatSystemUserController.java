package info.mychatbackend.modules.chat.systemUser.controller;

import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import info.mychatbackend.modules.chat.systemUser.model.Registration;
import info.mychatbackend.modules.chat.systemUser.service.ChatSystemUserOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/chat-user")
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
    public ResponseEntity registration(@Valid @RequestBody ChatSystemUser systemUser) {
        return new ResponseEntity<>(operations.save(systemUser), HttpStatus.OK);
    }

    @GetMapping(value = "/search/{username}")
    public ResponseEntity search(@PathVariable String username) {
        return new ResponseEntity<>(operations.search(username), HttpStatus.OK);
    }

    @PostMapping(value = "/registration/step")
    public ResponseEntity<Registration> stepRegistration(@RequestBody Registration registration) {
        return new ResponseEntity<>(operations.save(registration), HttpStatus.OK);
    }


}
