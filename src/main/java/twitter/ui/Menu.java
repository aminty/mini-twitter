package twitter.ui;

import twitter.domain.User;
import twitter.ui.menu.UserMenu;
import twitter.util.ApplicationContext;
import twitter.util.Constant;
import twitter.util.SecurityContext;

import javax.swing.*;
import java.util.Scanner;

public class Menu {

    public static void run() {
        Printer.printBanner();
        while (true) {
        Printer.printItem(Constant.startupItem, "start");
            Printer.printDescription("Choose an item: ");
            String chosenItem = new Scanner(System.in).next();
            switch (chosenItem) {
                case "1":
                    UserMenu.loginMenu();
                    break;
                case "2":
                    UserMenu.signupMenu();
                    break;
                case "3":
                    System.exit(0);
                default:
                    Printer.printWarning("Wrong input");
                    break;
            }
        }
    }





}
