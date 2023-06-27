package twitter.service;

import twitter.base.service.BaseService;
import twitter.domain.DirectMessage;
import twitter.domain.User;

import java.util.List;

public interface DirectMessageService extends BaseService<DirectMessage,Long> {

    List<DirectMessage> findAllMessageByReceiverById(User user);
}
