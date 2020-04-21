package info.mychatbackend.modules.chatSystemUser.helper;

import info.mychatbackend.modules.chatSystemUser.model.ChatSystemUser;
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
