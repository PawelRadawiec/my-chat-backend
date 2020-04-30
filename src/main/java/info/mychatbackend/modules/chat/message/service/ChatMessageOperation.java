package info.mychatbackend.modules.chat.message.service;

import info.mychatbackend.modules.chat.message.model.ChatMessage;

public interface ChatMessageOperation {

    ChatMessage create(ChatMessage message);

}
