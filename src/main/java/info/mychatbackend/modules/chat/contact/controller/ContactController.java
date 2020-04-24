package info.mychatbackend.modules.chat.contact.controller;

import info.mychatbackend.modules.chat.contact.model.ChatContact;
import info.mychatbackend.modules.chat.contact.service.ContentContactsService;
import info.mychatbackend.modules.chat.content.model.ChatContent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/contacts")
public class ContactController {

    private ContentContactsService contactsService;

    public ContactController(ContentContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @GetMapping()
    private ResponseEntity getByUsername() {
        return new ResponseEntity<>(contactsService.getChatContact(), HttpStatus.OK);
    }

    @PostMapping(value = "/add/contact")
    private ResponseEntity addContact(@RequestBody ChatContact contact) {
        return new ResponseEntity<>(contactsService.addContact(contact), HttpStatus.OK);
    }


}
