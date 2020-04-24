package info.mychatbackend.modules.chat.systemUser.service;

import info.mychatbackend.modules.chat.contact.service.ContentContactsService;
import info.mychatbackend.modules.chat.content.model.ChatContent;
import info.mychatbackend.modules.chat.content.service.ChatContentService;
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

    public ChatSystemUserService(
            ChatSystemUserRepository repository,
            SystemUserHelper systemUserHelper,
            ContentContactsService contactsService) {
        this.repository = repository;
        this.systemUserHelper = systemUserHelper;
        this.contactsService = contactsService;
    }

    @Override
    @Transactional
    public ChatSystemUser save(ChatSystemUser systemUser) {
        systemUserHelper.setPasswordHash(systemUser);
        repository.save(systemUser);
        contactsService.create(systemUser);
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
