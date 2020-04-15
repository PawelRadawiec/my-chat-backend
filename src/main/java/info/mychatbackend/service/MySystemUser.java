package info.mychatbackend.service;

import info.mychatbackend.modules.chatSystemUser.model.ChatSystemUser;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface MySystemUser {

    ChatSystemUser addUser(ChatSystemUser user, SimpMessageHeaderAccessor accessor);

}
