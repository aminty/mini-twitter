package twitter.ui.menu;

import twitter.domain.DirectMessage;
import twitter.domain.Tweet;
import twitter.domain.User;
import twitter.ui.Printer;
import twitter.util.ApplicationContext;
import twitter.util.SecurityContext;

import java.util.List;
import java.util.Scanner;

public class AccountMenu {

    static void editProfile() {
    }

    static void checkDirect() {
    }

    static void createNewTweet() {
        User user=new User();
        user.setId(SecurityContext.id);
        Scanner scanner=new Scanner(System.in);
        Printer.printDescription("Write your tweet: ");
        String tweetMsg=scanner.nextLine();
        Tweet tweet=new Tweet();

        tweet.setMessage(tweetMsg);
        tweet.setOwner(user);

        if (!tweetMsg.trim().isEmpty())
            ApplicationContext.getTweetService().saveOrUpdate(tweet);
        else
            Printer.printWarning("tweet should not be empty!");

    }

    static void home() {
        User user=ApplicationContext.getUserService().findById(SecurityContext.id).get();
        List<DirectMessage> directMessage=
                ApplicationContext.getDirectMessage().findAllMessageByReceiverById(user);
        Printer.printProfile(String.valueOf(user.getTweets().size())
                , String.valueOf(user.getFollower().size())
                , String.valueOf(user.getFollowing().size())
                , String.valueOf(directMessage.size()));

        Printer.printTweets(user.getTweets());



    }
    static void showTweets() {


    }
}
