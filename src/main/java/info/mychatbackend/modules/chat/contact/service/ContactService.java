package info.mychatbackend.modules.chat.contact.service;

import info.mychatbackend.modules.chat.contact.model.ChatContact;
import info.mychatbackend.modules.chat.contact.repository.ChatContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private ChatContactRepository repository;

    public ContactService(ChatContactRepository repository) {
        this.repository = repository;
    }

    public List<ChatContact> search(String username) {
        return repository.search(username);
    }


}
