package info.mychatbackend.authorization.model;

import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {

    private static final long serialVersionUID = 8317676219297719109L;

    private final String token;

    private ChatSystemUser chatUser;

    public JwtTokenResponse(String token) {
        this.token = token;
    }

    public JwtTokenResponse(String token, ChatSystemUser chatUser) {
        this.token = token;
        this.chatUser = chatUser;
    }

    public String getToken() {
        return this.token;
    }

    public ChatSystemUser getUser() { return chatUser; }


}
