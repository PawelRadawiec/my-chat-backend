package info.mychatbackend.modules.chatSystemUser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import info.mychatbackend.modules.chatContent.model.ChatContent;
import info.mychatbackend.modules.chatSystemUser.validator.UsernameUnique;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @UsernameUnique
    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @OneToOne(mappedBy = "owner")
    @JsonBackReference
    private ChatContent content;


}
