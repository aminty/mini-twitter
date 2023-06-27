package twitter.repository;

import twitter.base.repository.BaseRepository;
import twitter.base.repository.BaseRepositoryImpl;
import twitter.domain.DirectMessage;
import twitter.domain.User;

import java.util.List;

public interface DirectMessageRepository extends BaseRepository<DirectMessage,Long> {

        List<DirectMessage> findAllMessageByReceiverById(User user);

}
