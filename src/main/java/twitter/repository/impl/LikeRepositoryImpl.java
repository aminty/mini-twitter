package twitter.repository.impl;

import twitter.base.repository.BaseRepositoryImpl;
import twitter.domain.Like;
import twitter.repository.LikeRepository;
import javax.persistence.EntityManager;

public class LikeRepositoryImpl extends BaseRepositoryImpl<Like,Long> implements LikeRepository {
    public LikeRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Like> getEntityClass() {
        return Like.class;
    }
}
