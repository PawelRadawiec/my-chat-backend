package info.mychatbackend.modules.chatSystemUser.repository;

import info.mychatbackend.modules.chatSystemUser.model.ChatSystemUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ChatSystemUserRepository {

    private EntityManager em;

    public ChatSystemUserRepository(EntityManager em) {
        this.em = em;
    }

    public ChatSystemUser save(ChatSystemUser systemUser) {
        em.persist(systemUser);
        return systemUser;
    }

    public List<ChatSystemUser> getUserList() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ChatSystemUser> query = builder.createQuery(ChatSystemUser.class);
        Root<ChatSystemUser> root = query.from(ChatSystemUser.class);
        TypedQuery<ChatSystemUser> typedQuery = em.createQuery(query.select(root));
        return typedQuery.getResultList();
    }


}
