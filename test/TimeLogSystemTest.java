import backend.Employee;
import backend.Project;
import backend.TimeLogSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TimeLogSystemTest {
    private TimeLogSystem timeLogSystem;

    @BeforeEach
    public void setUp() {
        // Initialisation du système pour les tests
        Employee employee = null;
        timeLogSystem = new TimeLogSystem(employee, "admin", "admin");
        timeLogSystem.addEmployee(new Employee("user1", "password1", 10.0, 15.0));
        //timeLogSystem.addProject(new Project(new Date(), null, new HashMap<>(), "project1", 1));
    }


    @Test
    public void testAddProject() {
        // Ajouter un nouveau projet
        Project newProject = new Project(new Date(), null, new HashMap<>(), "newProject", 2);
        timeLogSystem.addProject(newProject);

        assertTrue(timeLogSystem.getProjects().contains(newProject));
    }


    @Test
    public void testAuthenticateAdmin() {
        // Authentifier l'admin avec des identifiants valides
        assertTrue(timeLogSystem.authenticateAdmin("admin", "admin"));
    }

    @Test
    public void testAddEmployee() {

        Employee employee = new Employee("user1", "12345", 10.0, 15.0, new Date(), null, "123-456-7890", "Developer", "password");

        timeLogSystem.addEmployee(employee);

        Employee retrievedEmployee = timeLogSystem.getEmployeeByUsername("user1");
        assertNotNull(retrievedEmployee);
        assertEquals("user1", retrievedEmployee.getUsername());
    }
}

