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

@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQuery(
        name = "chatSystemUser.getByUsername",
        query = "select csu from ChatSystemUser csu where csu.username = ?1"
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

    @OneToOne(mappedBy = "owner")
    @JsonBackReference(value = "content")
    private ChatContent content;

    @OneToOne(mappedBy = "owner")
    @JsonBackReference(value = "content-contacts")
    private ChatContentContacts chatContentContacts;


}
