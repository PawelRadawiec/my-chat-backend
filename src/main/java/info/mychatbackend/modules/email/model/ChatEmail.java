package info.mychatbackend.modules.email.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatEmail {

    private String from;
    private String to;
    private String subject;
    private String message;
    private String templateName;
    private String templateLocation;
    private String emailedMessage;

}
