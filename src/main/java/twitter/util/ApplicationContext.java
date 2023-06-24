package twitter.util;

import twitter.repository.UserRepository;
import twitter.repository.impl.UserRepositoryImpl;
import twitter.service.UserService;
import twitter.service.impl.UserServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ApplicationContext {

    private static final EntityManagerFactory emf;
    private static final EntityManager em;

    private static final UserRepository userRepository;
    private static final UserService userService;


    static {
        emf = Persistence.createEntityManagerFactory("twitter");
       em = emf.createEntityManager();
       userRepository=new UserRepositoryImpl(em);
       userService=new UserServiceImpl(userRepository);
    }

    public static UserService getUserService(){
        return userService;
    }




}
