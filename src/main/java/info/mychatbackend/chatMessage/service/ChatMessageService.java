package info.mychatbackend.chatMessage.service;

import info.mychatbackend.chatMessage.model.ChatMessage;
import info.mychatbackend.chatMessage.repository.ChatMessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class ChatMessageService implements ChatMessageOperation {

    private ChatMessageRepository repository;
    private RabbitTemplate template;

    public ChatMessageService(
            ChatMessageRepository repository,
            RabbitTemplate template
    ) {
        this.repository = repository;
        this.template = template;
    }

    @Override
    @Transactional
    public ChatMessage create(ChatMessage message) {
        String topicUrl = "/topic/";
        String sendTo = message.getTo();
        if (!StringUtils.isEmpty(sendTo)) {
            this.template.convertAndSend(topicUrl + sendTo, message);
            this.template.convertAndSend(topicUrl + message.getFrom(), message);
        } else {
            this.template.convertAndSend("/topic/message", message);
        }
        repository.create(message);
        return message;
    }


}
