package info.mychatbackend.chatMessage.service;

import info.mychatbackend.chatMessage.model.ChatMessage;
import info.mychatbackend.chatMessage.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;


@Service
public class ChatMessageService implements ChatMessageOperation {

    private ChatMessageRepository repository;

    public ChatMessageService(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatMessage create(ChatMessage message) {
        return repository.create(message);
    }


}
