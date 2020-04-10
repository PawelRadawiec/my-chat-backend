package info.mychatbackend.model;

import info.mychatbackend.chatContent.model.ChatContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat_system_user")
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @OneToOne(mappedBy = "owner")
    private ChatContent content;

}
