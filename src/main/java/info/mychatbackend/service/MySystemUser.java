package info.mychatbackend.service;

import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface MySystemUser {

    ChatSystemUser addUser(ChatSystemUser user, SimpMessageHeaderAccessor accessor);

}
