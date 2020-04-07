package info.mychatbackend.service;

import info.mychatbackend.model.SystemUser;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public interface MySystemUser {

    SystemUser addUser(SystemUser user, SimpMessageHeaderAccessor accessor);

}
