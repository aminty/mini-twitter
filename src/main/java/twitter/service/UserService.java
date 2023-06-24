package twitter.service;

import twitter.base.service.BaseService;
import twitter.domain.User;

import java.util.Optional;

public interface UserService extends BaseService<User,Long> {


    boolean  isUserExistsByUsername(String username);

    boolean  isUserExistsByEmail(String email);

    boolean  isValidCredential (String... credential);

    boolean isValidEmailPattern(String email);



}
