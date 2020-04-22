package info.mychatbackend.modules.chat.content.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import info.mychatbackend.modules.chat.message.model.ChatMessage;
import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
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
                "  join cc.owner ssu on ssu.id = cc.owner.id and ssu.username = ?1" +
                "  left join cc.messages cm")
@Table(name = "chat_content")
public class ChatContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonManagedReference(value = "content")
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
