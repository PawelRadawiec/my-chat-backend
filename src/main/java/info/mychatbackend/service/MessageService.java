package info.mychatbackend.service;

import info.mychatbackend.chatMessage.model.ChatMessage;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MessageService implements MyChatMessage {

    private SimpMessagingTemplate simpMessagingTemplate;

    public MessageService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public ChatMessage addUser(SimpMessageHeaderAccessor headerAccessor, ChatMessage message) {
        headerAccessor.getSessionAttributes().put("username", message.getFrom());
        return message;
    }

    @Override
    public ChatMessage sendMessage(ChatMessage message) {
        String sendTo = message.getTo();
        String topicUrl = "/topic/";
        if (!StringUtils.isEmpty(sendTo)) {
            this.simpMessagingTemplate.convertAndSend(topicUrl + sendTo, message);
            this.simpMessagingTemplate.convertAndSend(topicUrl + message.getFrom(), message);
        } else {
            this.simpMessagingTemplate.convertAndSend("/topic/message", message);
        }
        return message;
    }


}
