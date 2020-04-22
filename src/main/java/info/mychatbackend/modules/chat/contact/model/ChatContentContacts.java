package info.mychatbackend.modules.chat.contact.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@NamedQuery(name = "chatContentContacts.getByUsername",
        query = "select ccc from ChatContentContacts ccc" +
                "  join ccc.owner o on o.id = ccc.owner.id and o.username = ?1" +
                "  left join ccc.contacts c")
public class ChatContentContacts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonManagedReference(value = "content-contacts")
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
