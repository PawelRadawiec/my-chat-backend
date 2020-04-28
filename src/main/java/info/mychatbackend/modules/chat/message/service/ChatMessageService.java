package info.mychatbackend.modules.chat.message.service;

import info.mychatbackend.modules.chat.content.model.ChatContent;
import info.mychatbackend.modules.chat.content.repository.ChatRepository;
import info.mychatbackend.modules.chat.message.helper.ChatMessageHelper;
import info.mychatbackend.modules.chat.message.model.ChatMessage;
import info.mychatbackend.modules.chat.message.repository.ChatMessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class ChatMessageService implements ChatMessageOperation {

    private RabbitTemplate template;
    private ChatRepository contentRepository;
    private ChatMessageRepository messageRepository;

    public ChatMessageService(
            RabbitTemplate template,
            ChatRepository contentRepository,
            ChatMessageRepository messageRepository
    ) {
        this.template = template;
        this.contentRepository = contentRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public ChatMessage create(ChatMessage message) {
        ChatContent correspondentContent = contentRepository.findByUsername(message.getTo(), message.getFrom()).orElse(new ChatContent());
        String topicUrl = "/topic/";
        String sendTo = message.getTo();
        if (!StringUtils.isEmpty(sendTo)) {
            this.template.convertAndSend(topicUrl + sendTo, message);
            this.template.convertAndSend(topicUrl + message.getFrom(), message);
        } else {
            this.template.convertAndSend("/topic/message", message);
        }
        messageRepository.create(ChatMessageHelper.messageOf(message, correspondentContent));
        messageRepository.create(message);
        return message;
    }


}
