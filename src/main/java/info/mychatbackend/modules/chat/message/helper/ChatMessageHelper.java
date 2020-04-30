package info.mychatbackend.modules.chat.message.helper;

import info.mychatbackend.modules.chat.content.model.ChatContent;
import info.mychatbackend.modules.chat.message.model.ChatMessage;

public class ChatMessageHelper {

    private ChatMessageHelper() {
        throw new IllegalStateException("cannot create instance");
    }

    public static ChatMessage messageOf(ChatMessage ownerMessage, ChatContent content) {
        ChatMessage message = new ChatMessage();
        message.setTo(ownerMessage.getTo());
        message.setFrom(ownerMessage.getFrom());
        message.setMessage(ownerMessage.getMessage());
        message.setContent(content);
        return message;
    }


}
