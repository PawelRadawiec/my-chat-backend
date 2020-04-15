package info.mychatbackend.modules.chatContent.repository;

import info.mychatbackend.modules.chatContent.model.ChatContent;
import info.mychatbackend.model.SystemUser;
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

    public Optional<ChatContent> findByUsername(String username) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ChatContent> query = builder.createQuery(ChatContent.class);
        Root<ChatContent> chatContentRoot = query.from(ChatContent.class);
        Join<ChatContent, SystemUser> systemUserChatContentJoin = chatContentRoot.join("owner");
        query.where(
                builder.equal(systemUserChatContentJoin.get("username"), username)
        );
        TypedQuery<ChatContent> typedQuery = em.createQuery(query);
        return typedQuery.getResultList().stream().findFirst();
    }
}
