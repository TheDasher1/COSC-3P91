import java.util.Arrays;
import java.util.Scanner;

public class mainGame {
    
    private Map map;

    public mainGame() {
        Scanner input = new Scanner(System.in);
        map = new Map();
        String ch;

        while (true) {
            System.out.print("\nEnter an intersection name or 0 to exit: ");
            ch = input.nextLine();

            if (ch.equals("0")) {
                break;

            } else {
                map.addIntersection(ch);

            }

        }

        System.out.println(Arrays.toString(map.getAllLocations()));

        input.close();

    }

    public static void main(String[] args) {
        // mainGame a = new mainGame();
        // a.startA1();
        new mainGame();
        
    }

    
    
}