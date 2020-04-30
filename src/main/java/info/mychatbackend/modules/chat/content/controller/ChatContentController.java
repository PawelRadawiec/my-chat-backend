package info.mychatbackend.modules.chat.content.controller;

import info.mychatbackend.modules.chat.content.model.ChatContent;
import info.mychatbackend.modules.chat.content.service.ChatContentOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/content")
public class ChatContentController {

    private ChatContentOperations operations;

    public ChatContentController(ChatContentOperations operations) {
        this.operations = operations;
    }

    @PostMapping(value = "/create")
    public ResponseEntity create(@RequestBody ChatContent content) {
        return new ResponseEntity<>(operations.create(content), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return new ResponseEntity<>(operations.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity getByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(operations.findByUsername(username), HttpStatus.OK);
    }


}
