package info.mychatbackend.modules.chatContent.repository;

import info.mychatbackend.modules.chatContent.model.ChatContent;
import info.mychatbackend.modules.chatMessage.model.ChatMessage;
import info.mychatbackend.modules.chatSystemUser.model.ChatSystemUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Optional;

@Repository
@Transactional
public class ChatRepository {

    private EntityManager em;

    public ChatRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<ChatContent> save(ChatContent content) {
        em.persist(content);
        return findById(content.getId());
    }

    public Optional<ChatContent> findById(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ChatContent> query = builder.createQuery(ChatContent.class);
        Root<ChatContent> root = query.from(ChatContent.class);
        query.where(
                builder.equal(root.get("id"), id)
        );
        TypedQuery<ChatContent> typedQuery = em.createQuery(query);
        return typedQuery.getResultList().stream().findFirst();
    }

//    public Optional<ChatContent> findByUsername(String username) {
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<ChatContent> query = builder.createQuery(ChatContent.class);
//        Root<ChatContent> chatContentRoot = query.from(ChatContent.class);
//        Join<ChatContent, ChatSystemUser> systemUserChatContentJoin = chatContentRoot.join("owner");
//        Join<ChatContent, ChatMessage> messageChatContentJoin = chatContentRoot.join("messages");
//
//        systemUserChatContentJoin.on(builder.equal(systemUserChatContentJoin.get("username"), username));
//        messageChatContentJoin.on(builder.equal(messageChatContentJoin.get("from"), username));
//
//        TypedQuery<ChatContent> typedQuery = em.createQuery(query);
//        return typedQuery.getResultList().stream().findFirst();
//    }

    // just for test
        public Optional<ChatContent> findByUsername (String username){
            TypedQuery<ChatContent> query = em.createQuery(
                    "select cc from ChatContent cc" +
                            "  join cc.owner ssu on ssu.username = ?1" +
                            "  join cc.correspondent cor " +
                            "  join cc.messages cm", ChatContent.class);
            query.setParameter(1, username);
            return query.getResultList().stream().findFirst();
        }


}
