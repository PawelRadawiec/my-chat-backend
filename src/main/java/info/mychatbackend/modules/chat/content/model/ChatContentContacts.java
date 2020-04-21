package info.mychatbackend.modules.chat.content.model;

import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatContentContacts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ChatSystemUser owner;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "chat_content_contacts_relation",
            joinColumns = {@JoinColumn(name = "chat_content_contacts_id")},
            inverseJoinColumns = {@JoinColumn(name = "chat_contact_id")}
    )
    private List<ChatContact> contacts;


}
