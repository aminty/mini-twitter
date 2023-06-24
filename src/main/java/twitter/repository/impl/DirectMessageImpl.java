package twitter.repository.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import twitter.base.repository.BaseRepositoryImpl;
import twitter.domain.DirectMessage;
import twitter.repository.DirectMessageRepository;
import javax.persistence.EntityManager;


public class DirectMessageImpl extends BaseRepositoryImpl<DirectMessage, Long> implements DirectMessageRepository {

    public DirectMessageImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<DirectMessage> getEntityClass() {
        return DirectMessage.class;
    }
}
