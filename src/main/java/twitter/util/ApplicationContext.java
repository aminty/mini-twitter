package twitter.util;

import twitter.domain.DirectMessage;
import twitter.repository.CommentRepository;
import twitter.repository.DirectMessageRepository;
import twitter.repository.TweetRepository;
import twitter.repository.UserRepository;
import twitter.repository.impl.CommentRepositoryImpl;
import twitter.repository.impl.DirectMessageImpl;
import twitter.repository.impl.TweetRepositoryImpl;
import twitter.repository.impl.UserRepositoryImpl;
import twitter.service.CommentService;
import twitter.service.DirectMessageService;
import twitter.service.TweetService;
import twitter.service.UserService;
import twitter.service.impl.CommentServiceImpl;
import twitter.service.impl.DirectMessageServiceImpl;
import twitter.service.impl.TweetServiceImpl;
import twitter.service.impl.UserServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ApplicationContext {

     static final EntityManagerFactory emf;
     static final EntityManager em;

    private static final UserRepository userRepository;
    private static final UserService userService;
    private static final TweetRepository tweetRepository;
    private static final TweetService tweetService;
    private static final DirectMessageRepository directMsgRepository;
    private static final DirectMessageService directMsgService;
    private static final CommentRepository commentRepository;
    private static final CommentService commentService;


    static {
        emf = Persistence.createEntityManagerFactory("twitter");
       em = emf.createEntityManager();
       userRepository=new UserRepositoryImpl(em);
       userService=new UserServiceImpl(userRepository);
       tweetRepository=new TweetRepositoryImpl(em);
       tweetService=new TweetServiceImpl(tweetRepository);
       directMsgRepository=new DirectMessageImpl(em);
       directMsgService=new DirectMessageServiceImpl(directMsgRepository);
       commentRepository=new CommentRepositoryImpl(em);
       commentService=new CommentServiceImpl(commentRepository);
    }

    public static UserService getUserService(){
        return userService;
    }

    public static TweetService getTweetService(){
        return tweetService;
    }

    public static DirectMessageService getDirectMessage() {
        return directMsgService;
    }

    public static CommentService getCommentService(){
        return commentService;
    }
}
