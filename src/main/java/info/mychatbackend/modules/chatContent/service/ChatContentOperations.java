package info.mychatbackend.modules.chatContent.service;

import info.mychatbackend.modules.chatContent.model.ChatContent;

import java.util.Optional;

public interface ChatContentOperations {

    Optional<ChatContent> create(ChatContent content);
    Optional<ChatContent> update(ChatContent content);
    Optional<ChatContent> findById(Long content);
    Optional<ChatContent> findByUsername(String username);

}
