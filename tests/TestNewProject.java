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

    /*
        Create new project
     */
    @Test
    public void testNewProject() throws NameAlreadyExistException {
        int numberOfProjects = stm.getProjects().size();

        Employee E = stm.getEmployeeByID("Emil");

        E.AddProject("test",1);

        assertEquals( stm.getProjects().size(),numberOfProjects+1);
    }

    /*
       A project can not be created if the name already exists
     */

    @Test
    public void testNewProjectWithInvalidName() throws NameAlreadyExistException {
        Employee E = stm.getEmployeeByID("Emil");

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
        Employee E = stm.getEmployeeByID("Emil");

        E.AddProject("test1",2,E);

        ProjectLeader PL = stm.getProjectLeaderByID("Emil");

        assertEquals(stm.getProjectByID("test1").getProjectLeader(), PL);
    }

}
