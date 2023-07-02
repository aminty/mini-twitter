package twitter.repository;

import twitter.base.repository.BaseRepository;
import twitter.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends BaseRepository<User,Long>{


    boolean isUserExistsByUsername(String username);

    boolean  isUserExistsByEmail(String email);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    List<User> findUser(String title);
}
