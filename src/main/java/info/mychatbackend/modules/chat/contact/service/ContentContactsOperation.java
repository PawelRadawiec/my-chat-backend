package info.mychatbackend.modules.chat.contact.service;

import info.mychatbackend.modules.chat.contact.model.ChatContentContacts;
import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;

import java.util.Optional;

public interface ContentContactsOperation {

    Optional<ChatContentContacts> getByUsername(String username);
    ChatContentContacts create(ChatContentContacts user);
    ChatContentContacts create(ChatSystemUser user);

}
