package info.mychatbackend.modules.chat.contact.repository;

import info.mychatbackend.modules.chat.contact.model.ChatContact;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ChatContactRepository {

    private EntityManager em;

    public ChatContactRepository(EntityManager em) {
        this.em = em;
    }

    public ChatContact create(ChatContact contact) {
        em.persist(contact);
        return contact;
    }


}
