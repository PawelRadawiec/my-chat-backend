package info.mychatbackend.modules.chatSystemUser.service;

import info.mychatbackend.modules.chatSystemUser.model.ChatSystemUser;

import java.util.List;

public interface ChatSystemUserOperations {
    ChatSystemUser save(ChatSystemUser systemUser);
    ChatSystemUser getByUsername(String username);
    List<ChatSystemUser> getUserList();
}
