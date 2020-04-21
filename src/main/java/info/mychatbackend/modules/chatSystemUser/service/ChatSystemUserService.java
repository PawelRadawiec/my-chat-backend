package info.mychatbackend.modules.chatSystemUser.service;

import info.mychatbackend.modules.chatContent.model.ChatContent;
import info.mychatbackend.modules.chatContent.service.ChatContentService;
import info.mychatbackend.modules.chatSystemUser.helper.SystemUserHelper;
import info.mychatbackend.modules.chatSystemUser.model.ChatSystemUser;
import info.mychatbackend.modules.chatSystemUser.repository.ChatSystemUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatSystemUserService implements ChatSystemUserOperations {

    private ChatSystemUserRepository repository;
    private SystemUserHelper systemUserHelper;
    private ChatContentService contentService;

    public ChatSystemUserService(
            ChatSystemUserRepository repository,
            SystemUserHelper systemUserHelper,
            ChatContentService contentService
    ) {
        this.repository = repository;
        this.systemUserHelper = systemUserHelper;
        this.contentService = contentService;
    }

    @Override
    @Transactional
    public ChatSystemUser save(ChatSystemUser systemUser) {
        systemUserHelper.setPasswordHash(systemUser);
        repository.save(systemUser);
        ChatContent content = contentService.create(systemUser).orElse(null);
        systemUser.setContent(content);
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


}
