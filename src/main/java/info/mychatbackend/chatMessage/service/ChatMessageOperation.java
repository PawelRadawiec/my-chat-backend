package info.mychatbackend.chatMessage.service;

import info.mychatbackend.chatMessage.model.ChatMessage;

public interface ChatMessageOperation {

    ChatMessage create(ChatMessage message);

}
