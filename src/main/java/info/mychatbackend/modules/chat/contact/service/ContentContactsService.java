package info.mychatbackend.modules.chat.contact.service;

import info.mychatbackend.modules.chat.contact.model.ChatContact;
import info.mychatbackend.modules.chat.contact.model.ChatContentContacts;
import info.mychatbackend.modules.chat.contact.repository.ChatContactContactsRepository;
import info.mychatbackend.modules.chat.content.model.ChatContent;
import info.mychatbackend.modules.chat.content.service.ChatContentService;
import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import info.mychatbackend.modules.chat.systemUser.repository.ChatSystemUserRepository;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ContentContactsService implements ContentContactsOperation {

    private ChatContactContactsRepository contactsRepository;
    private ChatSystemUserRepository userRepository;
    private ChatContentService contentService;
    private SimpMessageSendingOperations messagingTemplate;

    public ContentContactsService(ChatContactContactsRepository contactsRepository, ChatSystemUserRepository userRepository, ChatContentService contentService, SimpMessageSendingOperations messagingTemplate) {
        this.contactsRepository = contactsRepository;
        this.userRepository = userRepository;
        this.contentService = contentService;
        this.messagingTemplate = messagingTemplate;
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

    @Override
    @Transactional
    public List<ChatContact> addContact(ChatContact contact) {
        // 1. update contacts
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String ownerUsername = authentication.getName();
        String correspondentName = contact.getUsername();

        ChatContentContacts ownerContentContacts = contactsRepository.getByUsername(ownerUsername).orElseGet(ChatContentContacts::new);
        ChatContact correspondentContact = contactsRepository.getChatContactByUsername(correspondentName).orElseGet(ChatContact::new);
        ownerContentContacts.getContacts().add(correspondentContact);

        ChatContentContacts correspondentContentContacts = contactsRepository.getByUsername(correspondentName).orElseGet(ChatContentContacts::new);
        ChatContact ownerContact = contactsRepository.getChatContactByUsername(ownerUsername).orElseGet(ChatContact::new);
        correspondentContentContacts.getContacts().add(ownerContact);

        // 2.create new owner content
        ChatSystemUser owner = userRepository.getByUsername(ownerUsername).orElseGet(ChatSystemUser::new);
        ChatSystemUser correspondent = userRepository.getByUsername(correspondentName).orElseGet(ChatSystemUser::new);

        ChatContent ownerContent = new ChatContent();
        ownerContent.setOwner(owner);
        ownerContent.setCorrespondent(correspondent);

        // create new correspondent content
        ChatContent correspondentContent = new ChatContent();
        correspondentContent.setOwner(correspondent);
        correspondentContent.setCorrespondent(owner);

        contentService.create(ownerContent);
        contentService.create(correspondentContent);

        messagingTemplate.convertAndSend("/topic/users", contact);

        return ownerContentContacts.getContacts();
    }


}
