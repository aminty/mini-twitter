package twitter.ui;

public class Printer {

    public static void printBanner(){
        System.out.println("" +
                " ______  __    __  ____  ______  ______    ___  ____  \n" +
                "|      T|  T__T  Tl    j|      T|      T  /  _]|    \\ \n" +
                "|      ||  |  |  | |  T |      ||      | /  [_ |  D  )\n" +
                "l_j  l_j|  |  |  | |  | l_j  l_jl_j  l_jY    _]|    / \n" +
                "  |  |  l  `  '  ! |  |   |  |    |  |  |   [_ |    \\ \n" +
                "  |  |   \\      /  j  l   |  |    |  |  |     T|  .  Y\n" +
                "  l__j    \\_/\\_/  |____j  l__j    l__j  l_____jl__j\\_j"
        );

    }

    public static void printItem(String [] item,String title){

            System.out.println("----------------------------");
            System.out.println("          "+title+"         ");
            System.out.println("----------------------------");
        for (int i = 0; i < item.length; i++) {
            System.out.println("|"+(i+1) +  " - " + item[i]) ;
        }

    }


    public static void printWarning(String input) {
        System.out.println(input);
    }

    public static void printDescription(String input) {
        System.out.print(input);
    }

    public static void printMessage(String input) {
        System.out.println(input);
    }
}
