package info.mychatbackend.service;

import info.mychatbackend.chatMessage.model.ChatMessage;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface MyChatMessage {

    ChatMessage sendMessage(ChatMessage message);

    ChatMessage addUser(SimpMessageHeaderAccessor headerAccessor, ChatMessage message);


}
