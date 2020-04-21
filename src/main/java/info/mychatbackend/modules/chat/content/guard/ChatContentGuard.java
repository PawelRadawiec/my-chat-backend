package info.mychatbackend.modules.chat.content.guard;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ChatContentGuard {

    public boolean checkUsername(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return username.equals(authentication.getName());
    }

}
