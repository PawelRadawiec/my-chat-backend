package info.mychatbackend.modules.chat.contact.service;

import info.mychatbackend.modules.chat.contact.model.ChatContentContacts;
import info.mychatbackend.modules.chat.contact.repository.ChatContactContactsRepository;
import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentContactsService implements ContentContactsOperation {

    private ChatContactContactsRepository contactsRepository;

    public ContentContactsService(ChatContactContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }


    @Override
    public Optional<ChatContentContacts> getByUsername(String username) {
        return contactsRepository.getByUsername(username);
    }

    @Override
    public ChatContentContacts create(ChatContentContacts user) {
        return null;
    }

    @Override
    public ChatContentContacts create(ChatSystemUser user) {
        ChatContentContacts contacts = new ChatContentContacts();
        contacts.setOwner(user);
        return contactsRepository.create(contacts);
    }


}
