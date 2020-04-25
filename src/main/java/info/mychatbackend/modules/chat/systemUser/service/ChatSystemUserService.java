package info.mychatbackend.modules.chat.systemUser.service;

import info.mychatbackend.modules.chat.contact.model.ChatContact;
import info.mychatbackend.modules.chat.contact.repository.ChatContactRepository;
import info.mychatbackend.modules.chat.contact.service.ContentContactsService;
import info.mychatbackend.modules.chat.systemUser.helper.SystemUserHelper;
import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import info.mychatbackend.modules.chat.systemUser.repository.ChatSystemUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatSystemUserService implements ChatSystemUserOperations {

    private ChatSystemUserRepository repository;
    private SystemUserHelper systemUserHelper;
    private ContentContactsService contactsService;
    private ChatContactRepository contactRepository;

    public ChatSystemUserService(
            ChatSystemUserRepository repository,
            SystemUserHelper systemUserHelper,
            ContentContactsService contactsService, ChatContactRepository contactRepository) {
        this.repository = repository;
        this.systemUserHelper = systemUserHelper;
        this.contactsService = contactsService;
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional
    public ChatSystemUser save(ChatSystemUser systemUser) {
        systemUserHelper.setPasswordHash(systemUser);
        repository.save(systemUser);
        contactsService.create(systemUser);
        contactRepository.create(new ChatContact(systemUser.getUsername()));
        // todo send activation mail
        return systemUser;
    }

    @Override
    public ChatSystemUser getByUsername(String username) {
        return repository.getByUsername(username).orElse(new ChatSystemUser());
    }

    @Override
    public List<ChatSystemUser> getUserList() {
        return repository.getUserList();
    }

    @Override
    public List<ChatSystemUser> search(String username) {
        return repository.search(username);
    }


}
