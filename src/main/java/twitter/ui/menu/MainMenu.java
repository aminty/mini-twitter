package twitter.ui.menu;

import twitter.ui.Printer;
import twitter.ui.menu.UserMenu;
import twitter.util.Constant;

import java.util.Scanner;

public class MainMenu {

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
