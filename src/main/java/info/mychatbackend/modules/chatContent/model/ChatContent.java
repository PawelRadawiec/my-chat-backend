package info.mychatbackend.modules.chatContent.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import info.mychatbackend.modules.chatMessage.model.ChatMessage;
import info.mychatbackend.modules.chatSystemUser.model.ChatSystemUser;
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
@NamedQuery(name = "chatContent.getByUserName",
        query = "select cc from ChatContent cc" +
                "  join cc.owner ssu on ssu.username = ?1" +
                "  join cc.correspondent cor " +
                "  join cc.messages cm")
@Table(name = "chat_content")
public class ChatContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonManagedReference
    private ChatSystemUser owner;

    @OneToOne()
    @JoinColumn(name = "correspondent_id", referencedColumnName = "id")
    @JsonManagedReference
    private ChatSystemUser correspondent;

    @OneToMany(
            mappedBy = "content"
    )
    @JsonManagedReference
    private List<ChatMessage> messages;


}
