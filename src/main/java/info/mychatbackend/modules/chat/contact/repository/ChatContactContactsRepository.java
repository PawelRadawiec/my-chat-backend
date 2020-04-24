package info.mychatbackend.modules.chat.contact.repository;

import info.mychatbackend.modules.chat.contact.model.ChatContact;
import info.mychatbackend.modules.chat.contact.model.ChatContentContacts;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class ChatContactContactsRepository {

    private EntityManager em;

    public ChatContactContactsRepository(EntityManager em) {
        this.em = em;
    }


    public ChatContentContacts create(ChatContentContacts contact) {
        em.persist(contact);
        return contact;
    }

    public Optional<ChatContentContacts> getByUsername(String username) {
        Query query = em.createNamedQuery("chatContentContacts.getByUsername");
        query.setParameter(1, username);
        return query.getResultList().stream().findFirst();
    }

    public Optional<ChatContact> getChatContactByUsername(String username) {
        Query query = em.createQuery("chatContact.getByUsername");
        query.setParameter(1, username);
        return query.getResultList().stream().findFirst();
    }


}
