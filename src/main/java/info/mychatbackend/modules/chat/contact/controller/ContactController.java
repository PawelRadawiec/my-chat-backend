package info.mychatbackend.modules.chat.contact.controller;

import info.mychatbackend.modules.chat.contact.model.ChatContact;
import info.mychatbackend.modules.chat.contact.service.ContactService;
import info.mychatbackend.modules.chat.contact.service.ContentContactsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/contacts")
public class ContactController {

    private ContentContactsService contentContactsService;
    private ContactService contentService;

    public ContactController(
            ContentContactsService contentContactsService,
            ContactService contentService
    ) {
        this.contentContactsService = contentContactsService;
        this.contentService = contentService;
    }

    @GetMapping()
    private ResponseEntity getByUsername() {
        return new ResponseEntity<>(contentContactsService.getChatContact(), HttpStatus.OK);
    }

    @PostMapping(value = "/add/contact")
    private ResponseEntity addContact(@RequestBody ChatContact contact) {
        return new ResponseEntity<>(contentContactsService.addContact(contact), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    private ResponseEntity search(@RequestParam(required = false) String username) {
        return new ResponseEntity<>(contentService.search(username), HttpStatus.OK);
    }


}
