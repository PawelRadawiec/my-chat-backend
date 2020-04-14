package info.mychatbackend.chatContent.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import info.mychatbackend.chatMessage.model.ChatMessage;
import info.mychatbackend.model.SystemUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat_content")
public class ChatContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonManagedReference
    private SystemUser owner;

    @OneToMany(
            mappedBy = "content"
    )
    @JsonManagedReference
    private List<ChatMessage> messages;

}
