package twitter.util;

import twitter.domain.DirectMessage;
import twitter.repository.*;
import twitter.repository.impl.*;
import twitter.service.*;
import twitter.service.impl.*;

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
    private static final LikeRepository likeRepository;
    private static final LikeService likeService;


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
       likeRepository=new LikeRepositoryImpl(em);
       likeService=new LikeServiceImpl(likeRepository);
    }


    public static LikeService getLikeService (){
        return likeService;
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
