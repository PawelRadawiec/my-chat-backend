package info.mychatbackend.service;

import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SystemUserService implements MySystemUser {

    private SimpMessagingTemplate simpMessagingTemplate;

    public SystemUserService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public ChatSystemUser addUser(ChatSystemUser user, SimpMessageHeaderAccessor accessor) {
        Objects.requireNonNull(accessor.getSessionAttributes()).put("username", user.getUsername());
        simpMessagingTemplate.convertAndSend("/topic/users", user);
        return user;
    }
}
