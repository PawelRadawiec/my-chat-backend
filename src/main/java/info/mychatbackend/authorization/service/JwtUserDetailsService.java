package info.mychatbackend.authorization.service;

import info.mychatbackend.authorization.model.JwtUserDetails;
import info.mychatbackend.modules.chatSystemUser.model.ChatSystemUser;
import info.mychatbackend.modules.chatSystemUser.repository.ChatSystemUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private ChatSystemUserRepository userRepository;

    public JwtUserDetailsService(ChatSystemUserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<ChatSystemUser> chatSystemUser = userRepository.getByUsername(username);
        JwtUserDetails userDetails = null;
        if (chatSystemUser.isPresent()) {
            ChatSystemUser user = chatSystemUser.get();
            userDetails = new JwtUserDetails(
                    user.getId(), user.getUsername(),
                    user.getPassword(), "ROLE_USER_2"
            );
        }
        return userDetails;
    }


}
