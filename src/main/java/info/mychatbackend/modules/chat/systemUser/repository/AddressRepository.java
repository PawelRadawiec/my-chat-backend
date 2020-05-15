package info.mychatbackend.modules.chat.systemUser.repository;

import info.mychatbackend.modules.chat.systemUser.model.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AddressRepository {

    private EntityManager em;

    public AddressRepository(EntityManager em) {
        this.em = em;
    }

    public Address save(Address address) {
        em.persist(address);
        return address;
    }

}
