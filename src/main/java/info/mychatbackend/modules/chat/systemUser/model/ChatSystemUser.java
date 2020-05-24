package info.mychatbackend.modules.chat.systemUser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import info.mychatbackend.modules.chat.contact.model.ChatContentContacts;
import info.mychatbackend.modules.chat.content.model.ChatContent;
import info.mychatbackend.modules.chat.systemUser.validator.UsernameUnique;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQuery(
        name = "chatSystemUser.getByUsername",
        query = "select csu from ChatSystemUser csu where csu.username = ?1"
)
@NamedQuery(
        name = "chatSystemUser.checkIfEmailExist",
        query = "select csu.email from ChatSystemUser csu where csu.email = ?1"
)
@NamedQuery(
        name = "chatSystemUser.checkIfUsernameExist",
        query = "select csu.username from ChatSystemUser csu where csu.username = ?1"
)
@NamedQuery(
        name = "chatSystemUser.search",
        query = "select csu from ChatSystemUser csu where csu.username like ?1"
)
@Table(name = "chat_system_user")
public class ChatSystemUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @UsernameUnique
    private String username;

    private String firstName;

    private String lastName;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private String activationCode;

    @OneToMany(mappedBy = "owner")
    @JsonBackReference(value = "content")
    private List<ChatContent> content;

    @OneToOne(mappedBy = "owner")
    @JsonBackReference(value = "content-contacts")
    private ChatContentContacts chatContentContacts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


}
