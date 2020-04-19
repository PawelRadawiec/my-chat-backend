package info.mychatbackend.modules.chatSystemUser.service;

import info.mychatbackend.modules.chatSystemUser.helper.SystemUserHelper;
import info.mychatbackend.modules.chatSystemUser.model.ChatSystemUser;
import info.mychatbackend.modules.chatSystemUser.repository.ChatSystemUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatSystemUserService implements ChatSystemUserOperations {

    private ChatSystemUserRepository repository;
    private SystemUserHelper systemUserHelper;

    public ChatSystemUserService(ChatSystemUserRepository repository, SystemUserHelper systemUserHelper) {
        this.repository = repository;
        this.systemUserHelper = systemUserHelper;
    }

    @Override
    public ChatSystemUser save(ChatSystemUser systemUser) {
        // create password hash
        systemUserHelper.setPasswordHash(systemUser);
        // send activation mail

        // create chat content
        return repository.save(systemUser);
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
