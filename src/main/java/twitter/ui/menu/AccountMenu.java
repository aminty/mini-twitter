package twitter.ui.menu;

import twitter.domain.*;
import twitter.ui.Printer;
import twitter.util.ApplicationContext;
import twitter.util.Constant;
import twitter.util.SecurityContext;

import java.util.*;

public class AccountMenu {

    static void editProfile() {
    }

    static void checkDirect() {
    }

    static void createNewTweet() {
        //        User user = ApplicationContext.getUserService().findById(SecurityContext.id).get();
        User user = new User();
        user.setId(SecurityContext.id);
        Scanner scanner = new Scanner(System.in);
        Printer.printDescription("Write your tweet: ");
        String tweetMsg = scanner.nextLine();
        Tweet tweet = new Tweet();

        tweet.setMessage(tweetMsg);
        tweet.setOwner(user);

        if (!tweetMsg.trim().isEmpty())
            ApplicationContext.getTweetService().saveOrUpdate(tweet);
        else
            Printer.printWarning("tweet should not be empty!");

    }

    static void home() {
        User user = ApplicationContext.getUserService().findById(SecurityContext.id).get();
        List<DirectMessage> directMessage =
                ApplicationContext.getDirectMessage().findAllMessageByReceiverById(user);
        Printer.printProfile(String.valueOf(user.getTweets().size())
                , String.valueOf(user.getFollower().size())
                , String.valueOf(user.getFollowing().size())
                , String.valueOf(directMessage.size()));

        Printer.printTweets(user.getTweets());


    }

    static void showNewTweets() {
        Optional<User> currentUser = ApplicationContext.getUserService().findById(SecurityContext.id);
        if (currentUser.isPresent()) {
            Set<User> following = currentUser.get().getFollowing();
            List<Tweet> allFollowingTweets = new ArrayList<>(currentUser.get().getTweets());
            for (User followingUser :
                    following) {
                List<Tweet> tweetsOfEachUser = followingUser.getTweets();
                allFollowingTweets.addAll(tweetsOfEachUser);
            }
            allFollowingTweets.sort(Comparator.comparing(Tweet::getCreatedAt));
            Printer.printTweets(allFollowingTweets);
        }

        getSelectedTweet();


    }

    public static void getSelectedTweet() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            Printer.printDescription("Enter the tweet id to show in detail: ");
            String tweetId = sc.next();
            if (tweetId.trim().matches("\\d+")) {
                Optional<Tweet> foundTweet =
                        ApplicationContext.getTweetService().findById(Long.valueOf(tweetId));
                if (foundTweet.isPresent())
                    setCommentOrLike(foundTweet);

            }
            if (tweetId.equals("back"))
                break;

        }
    }

    private static void setCommentOrLike(Optional<Tweet> foundTweet) {
        Scanner sc = new Scanner(System.in);
        Printer.printTweets(Collections.singletonList(foundTweet.get()));
        loop:
        while (true) {
            Printer.printItem(Constant.tweetOption, "option");
            Printer.printDescription("Choose once: ");
            switch (sc.next().trim()) {
                case "1":
                    setLike(foundTweet.get());
                    break;
                case "2":
                    setComment(foundTweet.get());
                    break;
                case "3":
                    Printer.showComments(foundTweet.get());
                    break;
                case "back":
                    break loop;
                default:
                    Printer.printWarning("Wrong input!");
                    break;
            }


        }

    }


    private static void setComment(Tweet tweet) {
        Scanner sc = new Scanner(System.in);
        Comment newComment = new Comment();
        //User commentOwner=new User();
        User commentOwner = ApplicationContext.getUserService().findById(SecurityContext.id).get();
        Printer.printDescription("Enter your comment: ");
        String comment = sc.nextLine();
        //commentOwner.setId(SecurityContext.id);
        newComment.setCommentOwner(commentOwner);
        newComment.setMessage(comment);
        newComment.setTweetComment(tweet);
        tweet.getComments().add(newComment);
        ApplicationContext.getCommentService().saveOrUpdate(newComment);
    }

    private static void setLike(Tweet tweet) {
        User likeOwner = ApplicationContext.getUserService().findById(SecurityContext.id).get();
        ApplicationContext.getLikeService().saveOrUpdate(new Like(likeOwner, tweet));
    }

    public static void searchAndFollow() {
        while (true) {
            Printer.printDescription("Enter firstname/lastname/username : ");
            Scanner sc = new Scanner(System.in);
            String title = sc.next();
            List<User> listOfFoundUser = ApplicationContext.getUserService().findUser(title);
            Printer.printUser(listOfFoundUser);
            Printer.printDescription("Who you want to follow: ");
            String selectedUser = sc.next();
            if (selectedUser.equals("back"))
                break;
            if (selectedUser.matches("\\d+"))
                listOfFoundUser.forEach(user -> {
                    if (Objects.equals(user.getId(), Long.valueOf(selectedUser)))
                        setFollowTable(user);
                });
            else
                Printer.printWarning("Bad format... try again!");

        }
    }

    private static void setFollowTable(User user) {
        User mainUser = ApplicationContext.getUserService().findById(SecurityContext.id).get();
        mainUser.getFollowing().add(user);
        user.getFollower().add(mainUser);

        ApplicationContext.getUserService().saveOrUpdate(mainUser);
        ApplicationContext.getUserService().saveOrUpdate(user);
    }
}
