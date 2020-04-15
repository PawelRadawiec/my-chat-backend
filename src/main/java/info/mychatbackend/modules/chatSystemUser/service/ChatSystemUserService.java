package info.mychatbackend.modules.chatSystemUser.service;

import info.mychatbackend.modules.chatSystemUser.model.ChatSystemUser;
import info.mychatbackend.modules.chatSystemUser.repository.ChatSystemUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatSystemUserService implements ChatSystemUserOperations {

    private ChatSystemUserRepository repository;

    public ChatSystemUserService(ChatSystemUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatSystemUser save(ChatSystemUser systemUser) {
        return repository.save(systemUser);
    }

    @Override
    public List<ChatSystemUser> getUserList() {
        return repository.getUserList();
    }


}
