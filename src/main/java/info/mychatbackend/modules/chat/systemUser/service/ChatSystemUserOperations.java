package info.mychatbackend.modules.chat.systemUser.service;

import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;

import java.util.List;

public interface ChatSystemUserOperations {
    ChatSystemUser save(ChatSystemUser systemUser);
    ChatSystemUser getByUsername(String username);
    List<ChatSystemUser> getUserList();
}