package info.mychatbackend.modules.chat.contact.controller;

import info.mychatbackend.modules.chat.contact.service.ContentContactsService;
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

    @GetMapping(value = "/{username}")
    private ResponseEntity getByUsername(@PathVariable() String username) {
        return new ResponseEntity<>(contactsService.getByUsername(username), HttpStatus.OK);
    }


}
