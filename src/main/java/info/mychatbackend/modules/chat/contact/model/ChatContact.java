package info.mychatbackend.modules.chat.contact.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(
        name = "chatContact.getByUsername",
        query = "select cc from ChatContact cc" +
                " where cc.username = ?1"
)
@NamedQuery(
        name = "chatContact.search",
        query = "select cc from ChatContact cc" +
                " where cc.username like ?1"
)
@Table(name = "chat_contacts")
public class ChatContact implements Serializable {

    public ChatContact(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private Boolean active;

    @ManyToMany(
//            cascade = {
//                    CascadeType.MERGE
//            },
            mappedBy = "contacts"
    )
    @JsonBackReference
    private List<ChatContentContacts> contentContacts;


}
