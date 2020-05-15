package info.mychatbackend.modules.chat.systemUser.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;

    private String city;

    private String street;

    private String postalCode;

    @OneToOne(mappedBy = "address")
    private ChatSystemUser user;


}
