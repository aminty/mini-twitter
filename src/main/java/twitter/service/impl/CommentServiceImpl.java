package twitter.service.impl;

import twitter.base.repository.BaseRepository;
import twitter.base.service.BaseServiceImpl;
import twitter.domain.Comment;
import twitter.repository.CommentRepository;
import twitter.service.CommentService;

public class CommentServiceImpl extends BaseServiceImpl<Comment,Long, CommentRepository>
        implements CommentService {


    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }
}
