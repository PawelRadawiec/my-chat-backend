package info.mychatbackend.modules.chat.contact.repository;

import info.mychatbackend.modules.chat.contact.model.ChatContact;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

    public List<ChatContact> search(String username) {
        Query query = em.createNamedQuery("chatContact.search");
        query.setParameter(1, "%" + username + "%");
        return (List<ChatContact>) query.getResultList();
    }


}
