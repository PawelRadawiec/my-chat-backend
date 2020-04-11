package info.mychatbackend.chatContent.service;


import info.mychatbackend.chatContent.model.ChatContent;
import info.mychatbackend.chatContent.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatContentService implements ChatContentOperations {

    private ChatRepository repository;

    public ChatContentService(ChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ChatContent> create(ChatContent content) {
        return repository.save(content);
    }

    @Override
    public Optional<ChatContent> update(ChatContent content) {
        return repository.save(content);
    }

    @Override
    public Optional<ChatContent> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<ChatContent> findByUsername(String username) {
        return repository.findByUsername(username);
    }


}
