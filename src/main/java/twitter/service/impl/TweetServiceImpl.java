package twitter.service.impl;

import twitter.base.service.BaseServiceImpl;
import twitter.domain.Tweet;
import twitter.repository.TweetRepository;
import twitter.service.TweetService;

public class TweetServiceImpl extends BaseServiceImpl<Tweet,Long, TweetRepository> implements TweetService{
    public TweetServiceImpl(TweetRepository repository) {
        super(repository);
    }
}
