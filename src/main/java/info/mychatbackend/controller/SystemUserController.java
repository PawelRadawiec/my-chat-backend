package info.mychatbackend.controller;

import info.mychatbackend.model.SystemUser;
import info.mychatbackend.service.MySystemUser;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/ws")
@CrossOrigin("*")
public class SystemUserController {

    private MySystemUser service;

    public SystemUserController(MySystemUser service) {
        this.service = service;
    }

    @MessageMapping("/send/user")
    @SendTo("/app")
    public SystemUser sendMessage(SystemUser systemUser,
                                  SimpMessageHeaderAccessor headerAccessor) {
        return service.addUser(systemUser, headerAccessor);
    }


}
