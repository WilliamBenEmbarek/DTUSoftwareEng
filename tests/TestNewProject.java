import org.junit.Test;

import java.util.Arrays;

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
            fail("NameAlreadyExistException exception should have been thrown");
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
}
