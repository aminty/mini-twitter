package twitter.repository;

import twitter.base.repository.BaseRepository;
import twitter.domain.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User,Long>{


    boolean isUserExistsByUsername(String username);

    boolean  isUserExistsByEmail(String email);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);
}
