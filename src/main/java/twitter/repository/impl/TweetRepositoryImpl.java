package twitter.repository.impl;

import twitter.base.repository.BaseRepositoryImpl;
import twitter.domain.Tweet;
import twitter.repository.TweetRepository;

import javax.persistence.EntityManager;

public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet,Long> implements TweetRepository {
    public TweetRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Tweet> getEntityClass() {
        return Tweet.class;
    }
}
