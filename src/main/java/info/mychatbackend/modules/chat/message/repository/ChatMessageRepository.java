package info.mychatbackend.modules.chat.message.repository;

import info.mychatbackend.modules.chat.message.model.ChatMessage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ChatMessageRepository {

    private EntityManager em;

    public ChatMessageRepository(EntityManager em) {
        this.em = em;
    }

    public ChatMessage create(ChatMessage message) {
        em.persist(message);
        return message;
    }


}
