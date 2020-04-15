package info.mychatbackend.modules.chatMessage.service;

import info.mychatbackend.modules.chatMessage.model.ChatMessage;

public interface ChatMessageOperation {

    ChatMessage create(ChatMessage message);

}
