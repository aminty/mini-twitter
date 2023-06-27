package twitter.service.impl;

import twitter.base.service.BaseServiceImpl;
import twitter.domain.DirectMessage;
import twitter.domain.User;
import twitter.repository.DirectMessageRepository;

import java.util.List;

public class DirectMessageServiceImpl extends BaseServiceImpl<DirectMessage,Long, DirectMessageRepository>
        implements twitter.service.DirectMessageService {
    public DirectMessageServiceImpl(DirectMessageRepository repository) {
        super(repository);
    }

    @Override
    public List<DirectMessage> findAllMessageByReceiverById(User user) {
        return repository.findAllMessageByReceiverById(user);
    }
}
