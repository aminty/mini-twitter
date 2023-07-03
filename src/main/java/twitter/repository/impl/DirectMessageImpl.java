package twitter.repository.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import twitter.base.repository.BaseRepositoryImpl;
import twitter.domain.DirectMessage;
import twitter.domain.User;
import twitter.repository.DirectMessageRepository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


public class DirectMessageImpl extends BaseRepositoryImpl<DirectMessage, Long> implements DirectMessageRepository {

    public DirectMessageImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<DirectMessage> getEntityClass() {
        return DirectMessage.class;
    }

    @Override
    public List<DirectMessage> findAllMessageByReceiverById(User user) {
        TypedQuery<DirectMessage> query =
                em.createQuery("FROM DirectMessage  WHERE receiver= :user ",getEntityClass());
        return query.setParameter("user",user).getResultList();

    }
}
