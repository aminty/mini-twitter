package twitter.repository.impl;

import twitter.base.repository.BaseRepositoryImpl;
import twitter.domain.Comment;
import twitter.repository.CommentRepository;

import javax.persistence.EntityManager;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment,Long> implements CommentRepository {
    public CommentRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Comment> getEntityClass() {
        return Comment.class;
    }
}
