package info.mychatbackend.modules.chat.contact.service;

import info.mychatbackend.modules.chat.contact.model.ChatContentContacts;
import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;


public interface ContentContactsOperation {

    ChatContentContacts getChatContact();

    ChatContentContacts create(ChatContentContacts user);

    ChatContentContacts create(ChatSystemUser user);

}
