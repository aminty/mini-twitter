package twitter.ui.menu;

import twitter.domain.User;
import twitter.ui.Printer;
import twitter.util.ApplicationContext;
import twitter.util.Constant;
import twitter.util.SecurityContext;

import java.util.Scanner;

public class UserMenu {

    public static void signupMenu() {
        Scanner scanner=new Scanner(System.in);
        User newUser=new User();


        Printer.printDescription("Enter your firstname: ");
        newUser.setFirstname(scanner.next().trim());

        Printer.printDescription("Enter your lastname: ");
        newUser.setLastname(scanner.next().trim());

        scanner.nextLine();
        Printer.printDescription("Enter your bio: ");
        newUser.setBio(scanner.nextLine().trim());

        newUser.setUsername(getNewUsername(scanner));

        Printer.printDescription("Enter your password: ");
        newUser.setPassword(scanner.next().trim());

        newUser.setEmail(getNewEmail(scanner));

        ApplicationContext.getUserService().saveOrUpdate(newUser);
        Printer.printMessage("User saved successfully");

    }

    private static String getNewEmail(Scanner scanner) {
        while (true) {
            Printer.printDescription("Enter your email: ");
            String email = scanner.next().trim();
            if (email.trim().isEmpty()
                    ||ApplicationContext.getUserService().isUserExistsByEmail(email)
                    ||!ApplicationContext.getUserService().isValidEmailPattern(email))
                Printer.printWarning("use another email");
            else return email;
        }
    }

    private static String getNewUsername(Scanner scanner) {
        while (true) {
            Printer.printDescription("Enter your username: ");
            String username = scanner.next().trim();
            if (username.trim().isEmpty()
                    || ApplicationContext.getUserService().isUserExistsByUsername(username))
                Printer.printWarning("use another username");
            else return username;
        }
    }

    public static void loginMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Printer.printDescription("Enter your credentials in this format(username/email,password): ");
            String[] credentials = scanner.next().trim().split(",");
            if (credentials[0].equals("back")) break;
            if (credentials.length < 2)
                Printer.printWarning("Credential format is incorrect!");
            else {
                boolean validateCredential
                        = ApplicationContext.getUserService().isValidCredential(credentials[0], credentials[1]);
                if (validateCredential)
                    showProfileMenu();
                else
                    Printer.printWarning("Bad credentials... try again");
            }

        }
    }

    private static void showProfileMenu() {
        boolean isLoggedIn=true;
        while (isLoggedIn) {
            Printer.printItem(Constant.profileItem,"profile");
            Printer.printDescription("Choose an item: ");
            String chosenItem = new Scanner(System.in).next();
            switch (chosenItem) {
                case "1":
                    AccountMenu.showNewTweets();
                    break;
                case "2":
                    AccountMenu.home();
                    break;
                case "3":
                    AccountMenu.createNewTweet();
                case "4":
                    AccountMenu.checkDirect();
                    break;
                case "5":
                    AccountMenu.editProfile();
                    break;
                case "6" :{
                    signOut();
                    isLoggedIn=false;
                    break;
                }
                default:
                    Printer.printWarning("Wrong input");
                    break;
            }
        }
    }

    private static void signOut() {
        SecurityContext.id=0L;
        SecurityContext.username="";
        SecurityContext.email="";

    }
}
