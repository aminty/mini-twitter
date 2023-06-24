package twitter.service.impl;

import twitter.base.service.BaseServiceImpl;
import twitter.domain.DirectMessage;
import twitter.repository.DirectMessageRepository;

public class DirectMessageServiceImpl extends BaseServiceImpl<DirectMessage,Long, DirectMessageRepository>
        implements twitter.service.DirectMessageService {
    public DirectMessageServiceImpl(DirectMessageRepository repository) {
        super(repository);
    }
}
