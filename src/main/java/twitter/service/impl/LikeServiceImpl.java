package twitter.service.impl;

import twitter.base.service.BaseServiceImpl;
import twitter.domain.Like;
import twitter.repository.LikeRepository;
import twitter.service.LikeService;

public class LikeServiceImpl extends BaseServiceImpl<Like,Long, LikeRepository> implements LikeService {
    public LikeServiceImpl(LikeRepository repository) {
        super(repository);
    }
}
