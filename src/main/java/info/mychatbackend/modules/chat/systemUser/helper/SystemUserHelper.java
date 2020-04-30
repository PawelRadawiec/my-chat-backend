package info.mychatbackend.modules.chat.systemUser.helper;

import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SystemUserHelper {

    private PasswordEncoder passwordEncoder;

    public SystemUserHelper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setPasswordHash(ChatSystemUser systemUser) {
        systemUser.setPassword(passwordEncoder.encode(systemUser.getPassword()));
    }

}
