package IOPackage;

import java.util.Scanner;

public class Basic {

    public Basic() {

    }

    public String getString() {
        Scanner input = new Scanner(System.in);
        String temp;

        while (true) {
            System.out.print("\n>");

            if (input.hasNext()) {
                temp = input.nextLine();

            } else {
                input.next();

                continue;

            }

            break;

        }

        input.close();
        
        return temp;

    }

    public int getInt() {
        Scanner input = new Scanner(System.in);
        int temp;

        while (true) {
            System.out.print("\n>");

            if (input.hasNextInt()) {
                temp = input.nextInt();

            } else {
                input.next();

                continue;

            }

            break;

        }

        input.close();

        return temp;

    }

    public double getDouble() {
        Scanner input = new Scanner(System.in);
        double temp;

        while (true) {
            System.out.print("\n>");

            if (input.hasNextDouble()) {
                temp = input.nextDouble();

            } else {
                input.next();

                continue;

            }

            break;

        }

        input.close();

        return temp;

    }

    public int getUniqueID() {
        Scanner input = new Scanner(System.in);
        int tempID;

        while (true) {
            System.out.print("\nEnter a unique numerical ID that is not already in use: ");

            if (input.hasNextInt()) {
                tempID = input.nextInt();

            } else {
                input.next();

                continue;

            }

            break;

        }

        input.close();

        return tempID;

    }
    
}
