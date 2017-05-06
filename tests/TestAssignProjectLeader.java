import TimeManagement.Domain.Employee;
import TimeManagement.Domain.Project;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Emil on 18/04/2017.
 */
public class TestAssignProjectLeader extends SampleDataSetup{

    @Test
    public void testAssignProjectLeader() {
        assertTrue(STM.getProjectLeaders().size()==1);
        Employee employee = STM.getEmployeeByID("Emil");
        Project project = STM.projectsWithoutAProjectLeader().get(0);
        STM.AssignProjectLeader(employee,project);

        assertEquals(STM.getProjectLeaders().size(),2);
        assertEquals(STM.getProjectLeaderByID("Emil").getID(),employee.getID());

        // The employee by ID: Emil should not be a employee anymore
        assertEquals(STM.getEmployeeByID("Emil"),null);
    }

    /*
        Assigning a project leader to a project with a project leader already assigned
        should not be possible.
     */
    @Test
    public void testAssignProjectLeaderToAProjectWithAProjectLeader() {
        Project project = STM.projectsWithoutAProjectLeader().get(0);

        assertTrue(STM.projectsWithoutAProjectLeader().contains(project));

        STM.AssignProjectLeader(STM.getEmployeeByID("Emil"),project);

        assertFalse(STM.projectsWithoutAProjectLeader().contains(project));
    }

}
