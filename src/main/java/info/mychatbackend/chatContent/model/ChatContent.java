package info.mychatbackend.chatContent.model;

import info.mychatbackend.chatMessage.model.ChatMessage;
import info.mychatbackend.model.SystemUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat_content")
public class ChatContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private SystemUser owner;

    @OneToMany(
            mappedBy = "content",
            cascade = CascadeType.ALL
    )
    private List<ChatMessage> messages;

}
