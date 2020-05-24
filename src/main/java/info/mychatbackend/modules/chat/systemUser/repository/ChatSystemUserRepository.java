package info.mychatbackend.modules.chat.systemUser.repository;

import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class ChatSystemUserRepository {

    private EntityManager em;

    public ChatSystemUserRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public ChatSystemUser save(ChatSystemUser systemUser) {
        em.persist(systemUser);
        return systemUser;
    }

    @SuppressWarnings("unchecked")
    public Optional<ChatSystemUser> getByUsername(String username) {
        Query query = em.createNamedQuery("chatSystemUser.getByUsername");
        query.setParameter(1, username);
        List<ChatSystemUser> users = query.getResultList();
        return !users.isEmpty() ? Optional.of(users.get(0)) : Optional.empty();
    }

    @SuppressWarnings("unchecked")
    public Optional<String> checkIfEmailExist(String email) {
        Query query = em.createNamedQuery("chatSystemUser.checkIfEmailExist");
        query.setParameter(1, email);
        List<String> emails = query.getResultList();
        return !emails.isEmpty() ? Optional.of(emails.get(0)) : Optional.empty();
    }

    @SuppressWarnings("unchecked")
    public Optional<String> checkIfUsernameExist(String email) {
        Query query = em.createNamedQuery("chatSystemUser.checkIfUsernameExist");
        query.setParameter(1, email);
        List<String> usernameList = query.getResultList();
        return !usernameList.isEmpty() ? Optional.of(usernameList.get(0)) : Optional.empty();
    }

    public List<ChatSystemUser> getUserList() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ChatSystemUser> query = builder.createQuery(ChatSystemUser.class);
        Root<ChatSystemUser> root = query.from(ChatSystemUser.class);
        TypedQuery<ChatSystemUser> typedQuery = em.createQuery(query.select(root));
        return typedQuery.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ChatSystemUser> search(String username) {
        Query query = em.createNamedQuery("chatSystemUser.search");
        query.setParameter(1, "%" + username + "%");
        return (List<ChatSystemUser>)query.getResultList();
    }


}
