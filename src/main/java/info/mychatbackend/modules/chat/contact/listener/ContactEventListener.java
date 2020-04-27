package info.mychatbackend.modules.chat.contact.listener;

import info.mychatbackend.authorization.model.JwtUserDetails;
import info.mychatbackend.modules.chat.contact.model.ChatContact;
import info.mychatbackend.modules.chat.contact.repository.ChatContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class ContactEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ContactEventListener.class);

    private ChatContactRepository contactRepository;
    private SimpMessageSendingOperations messageSending;

    public ContactEventListener(ChatContactRepository contactRepository, SimpMessageSendingOperations messageSending) {
        this.contactRepository = contactRepository;
        this.messageSending = messageSending;
    }

    @EventListener({AuthenticationSuccessEvent.class, InteractiveAuthenticationSuccessEvent.class})
    public void processAuthenticationSuccessEvent(AbstractAuthenticationEvent e) {
        JwtUserDetails userDetails = (JwtUserDetails) e.getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        ChatContact contact = contactRepository.getByUsername(username);
        contact.setActive(true);
        contactRepository.update(contact);
        messageSending.convertAndSend("/topic/users", contact);
        logger.info("user logged: " + username);
    }


}
