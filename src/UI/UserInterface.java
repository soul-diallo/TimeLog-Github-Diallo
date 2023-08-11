package UI;

import backend.Employee;
import backend.TimeLogSystem;
import backend.testMain;
import utils.MenuUtils;

import java.util.Scanner;

import static backend.TimeLogSystem.authenticateAdmin;
import static backend.TimeLogSystem.authenticateUser;
import static utils.MenuUtils.getUserChoice;

public class UserInterface {
    static Scanner scanner = new Scanner(System.in);
    static testMain init = new testMain();
    static TimeLogSystem timeLogSystem = init.init();
    public static void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("****** Menu Principal ******");
            System.out.println("1. Se connecter en tant qu'administrateur");
            System.out.println("2. Se connecter en tant qu'employé");
            System.out.println("3. Quitter");

            int choice = getUserChoice();

            switch (choice) {
                case 1 -> authenticateAndDisplayAdminMenu();
                case 2 -> authenticateAndDisplayUserMenu();
                case 3 -> {
                    System.out.println("Au revoir !");
                    return;
                }
                default -> System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void authenticateAndDisplayAdminMenu() {
        Employee admin = authenticateAdmin();
        if (admin != null) {
            MenuUtils.displayAdminMenu(timeLogSystem, scanner);
        }
    }

    private static void authenticateAndDisplayUserMenu() {
        Employee user = authenticateUser();
        if (user != null) {
            timeLogSystem.setLoggedInUser(user);
            MenuUtils.displayUserMenu(timeLogSystem, scanner);
        }
    }
}
