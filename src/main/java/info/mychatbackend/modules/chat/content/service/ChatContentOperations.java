package info.mychatbackend.modules.chat.content.service;

import info.mychatbackend.modules.chat.content.model.ChatContent;
import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;

import java.util.Optional;

public interface ChatContentOperations {

    Optional<ChatContent> create(ChatContent content);

    Optional<ChatContent> create(ChatSystemUser systemUser);

    Optional<ChatContent> update(ChatContent content);

    Optional<ChatContent> findById(Long content);

    Optional<ChatContent> findByUsername(String username);

}
