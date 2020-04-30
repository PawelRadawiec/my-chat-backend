package info.mychatbackend.modules.chat.message.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.mychatbackend.modules.chat.content.model.ChatContent;
import info.mychatbackend.modules.chat.content.repository.ChatRepository;
import info.mychatbackend.modules.chat.message.helper.ChatMessageHelper;
import info.mychatbackend.modules.chat.message.model.ChatMessage;
import info.mychatbackend.modules.chat.message.repository.ChatMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ChatMessageService implements ChatMessageOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatMessageService.class);

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
        String messageJson = null;
        String exchange = "amq.topic";
        try {
            ObjectMapper mapper = new ObjectMapper();
            messageJson = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            LOGGER.info("JsonProcessingException", e);
        }
        this.template.convertAndSend(exchange,  "message." + message.getTo(), messageJson);
        this.template.convertAndSend(exchange, "message." + message.getFrom(), messageJson);

        messageRepository.create(ChatMessageHelper.messageOf(message, correspondentContent));
        messageRepository.create(message);
        return message;
    }


}
