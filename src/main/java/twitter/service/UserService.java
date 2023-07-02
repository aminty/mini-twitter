package twitter.service;

import twitter.base.service.BaseService;
import twitter.domain.User;

import java.util.List;
import java.util.Set;

public interface UserService extends BaseService<User,Long> {


    boolean  isUserExistsByUsername(String username);

    boolean  isUserExistsByEmail(String email);

    boolean  isValidCredential (String... credential);

    boolean isValidEmailPattern(String email);

    List<User> findUser(String title);
}
