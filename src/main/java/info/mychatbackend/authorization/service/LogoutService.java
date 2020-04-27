package info.mychatbackend.authorization.service;

import info.mychatbackend.modules.chat.contact.model.ChatContact;
import info.mychatbackend.modules.chat.contact.repository.ChatContactRepository;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    private ChatContactRepository contactRepository;
    private SimpMessageSendingOperations messageSending;

    public LogoutService(ChatContactRepository contactRepository, SimpMessageSendingOperations messageSending) {
        this.contactRepository = contactRepository;
        this.messageSending = messageSending;
    }

    public Boolean logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ChatContact contact = contactRepository.getByUsername(authentication.getName());
        contact.setActive(false);
        contactRepository.update(contact);
        messageSending.convertAndSend("/topic/users", contact);
        return true;
    }


}
