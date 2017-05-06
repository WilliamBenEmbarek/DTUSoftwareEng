import TimeManagement.Domain.Employee;
import TimeManagement.Domain.NameAlreadyExistException;
import TimeManagement.Domain.Project;
import TimeManagement.Domain.ProjectLeader;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Emil on 18/04/2017.
 */
public class TestNewProject extends SampleDataSetup{

    @Test
    public void testNewProject() throws NameAlreadyExistException {
        int numberOfProjects = STM.getProjects().size();

        Employee E = STM.getEmployeeByID("Emil");

        E.AddProject("test",1);

        assertEquals( STM.getProjects().size(),numberOfProjects+1);
    }

    /*
       A project can not be created if the name already exists
     */

    @Test
    public void testNewProjectWithInvalidName() throws NameAlreadyExistException {
        Employee E = STM.getEmployeeByID("Emil");

        E.AddProject("testName",1);

        try {
            E.AddProject("testName",2);
            fail("TimeManagement.Domain.NameAlreadyExistException exception should have been thrown");
        } catch (NameAlreadyExistException e) {

            assertEquals("This name already exist.", e.getMessage());
            assertEquals("Change name", e.getOperation());
        }


    }

    /*
        A new project can be created with a already decided project leader
     */

    @Test
    public void testNewProjectWithProjectLeader() throws NameAlreadyExistException {
        Employee E = STM.getEmployeeByID("Emil");

        E.AddProject("test1",2,E);

        ProjectLeader PL = STM.getProjectLeaderByID("Emil");

        assertEquals(STM.getProjectByID("test1").getProjectLeader(), PL);
    }

    @Test
    public void testNewProjectWithEmployees() throws NameAlreadyExistException { //Written by William
        Employee E = STM.getEmployeeByID("Emil");
        E.AddProject("test2",2,E);
        ProjectLeader PL = STM.getProjectLeaderByID("Emil");
		ArrayList<Employee> employees = STM.getEmployees();
		Project p = PL.getAssignedProject();
		for (int i = 0; i < employees.size();i++) {
			PL.assignProject(employees.get(i),p);
		}
    }
}
