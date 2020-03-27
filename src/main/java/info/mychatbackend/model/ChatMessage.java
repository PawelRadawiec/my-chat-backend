package info.mychatbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {

    private String message;
    private String from;
    private String to;

}
