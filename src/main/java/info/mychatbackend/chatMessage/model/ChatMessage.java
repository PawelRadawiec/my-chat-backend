package info.mychatbackend.chatMessage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import info.mychatbackend.chatContent.model.ChatContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat_message")
public class ChatMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "to_user")
    private String to;

    @Column(name = "from_user")
    private String from;

    @Column(name = "message_content")
    private String message;

    @ManyToOne()
    @JsonBackReference
    private ChatContent content;

}
