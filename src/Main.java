import backend.*;
import UI.AdminUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AdminUI ui = new AdminUI(
                new TimeLogSystem(),
                new Admin(
                        1,
                        "soul",
                        "1234",
                        Role.ADMIN)
        );
        ui.start();
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("1: ");
//        String na = scanner.nextLine();
//        System.out.print("2: ");
//        String bn = scanner.nextLine();
//        System.out.println("3: ");
//        int n = scanner.nextInt();
//
//        System.out.println(na);
//        System.out.println(bn);
//        System.out.println(n);
    }
}