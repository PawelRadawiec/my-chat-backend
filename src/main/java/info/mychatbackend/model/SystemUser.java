package info.mychatbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import info.mychatbackend.chatContent.model.ChatContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat_system_user")
public class SystemUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    @OneToOne(mappedBy = "owner")
    @JsonBackReference
    private ChatContent content;

}
