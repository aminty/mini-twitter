package twitter.service.impl;

import twitter.base.service.BaseServiceImpl;
import twitter.domain.User;
import twitter.repository.UserRepository;
import twitter.service.UserService;
import twitter.util.SecurityContext;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }


    @Override
    public boolean isUserExistsByUsername(String username) {
        return repository.isUserExistsByUsername(username);
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return repository.isUserExistsByEmail(email);
    }


    @Override
    public boolean isValidCredential(String... credential) {
        Optional<User> foundUser;
        if (isValidEmailPattern(credential[0])){
            foundUser=repository.getUserByEmail(credential[0]);
            if (foundUser.isPresent()) {
                setDataToSecurityContext(foundUser.get());
                return credential[0].equals(foundUser.get().getEmail())
                        && credential[1].equals(foundUser.get().getPassword());
            }

        }else {
            foundUser=repository.getUserByUsername(credential[0]);
            if (foundUser.isPresent()) {
                setDataToSecurityContext(foundUser.get());
                return credential[0].equals(foundUser.get().getUsername())
                        && credential[1].equals(foundUser.get().getPassword());
            }
        }
        return false;
    }

    private void setDataToSecurityContext(User user) {
        SecurityContext.id=user.getId();
        SecurityContext.username=user.getUsername() ;
        SecurityContext.email=user.getEmail();
        SecurityContext.bio=user.getBio();
    }

    @Override
    public boolean isValidEmailPattern(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(emailRegex,email);
    }

    @Override
    public List<User> findUser(String title) {
        return repository.findUser(title);
    }
}
