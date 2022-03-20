package mainGameLogic;

import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import MapPackage.*;
import UserPackage.*;
import VehiclePackage.*;
import GUIPackage.*;

public class mainGameLogic {

    public GUI gameWindow;
    public Map map;
    public HashSet<Player> players;

    /**
     * 
     * The different classes don't have any comments because they're empty placeholders only.
     * 
     * They will be commented properly after I've started implementing them because I'm still figuring out how exactly they'll function.
     * 
     * The return functions are self explanatory.
     * 
     * Currently the build (.jar) only prints the output of an input, it was done to only make sure the program builds correctly and isn't ment to have any actual functionality right now
     * 
     */
    public mainGameLogic() {
        Scanner input = new Scanner(System.in);
        map = new Map();
        String ch;

        while (true) {
            System.out.print("\nEnter an intersection name or 0 to exit: ");
            ch = input.nextLine();

            if (ch.equals("0")) {
                break;

            } else {
                // map.addIntersection(ch);
                System.out.println("You entered: " + ch);

            }

        }

        System.out.println((map.getAllIntersection()));

        input.close();

    }

    public static void main(String[] args) throws Exception {
        new mainGameLogic();
    }
}
