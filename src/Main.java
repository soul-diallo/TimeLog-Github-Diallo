import backend.*;
import user_interface.AdminUI;

import java.util.ArrayList;

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
    }
}