package info.mychatbackend.modules.chat.contact.service;

import info.mychatbackend.modules.chat.contact.model.ChatContentContacts;
import info.mychatbackend.modules.chat.contact.repository.ChatContactContactsRepository;
import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class ContentContactsService implements ContentContactsOperation {

    private ChatContactContactsRepository contactsRepository;

    public ContentContactsService(ChatContactContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }


    @Override
    public ChatContentContacts getChatContact() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return contactsRepository.getByUsername(authentication.getName()).orElse(null);
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
