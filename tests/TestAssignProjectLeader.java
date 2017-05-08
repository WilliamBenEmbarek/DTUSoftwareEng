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

    /*
        Assign a leader, and thereby remove the person as Employee
     */
    @Test
    public void testAssignProjectLeader() {
        assertTrue(stm.getProjectLeaders().size()==1);
        Employee employee = stm.getEmployeeByID("Emil");
        Project project = stm.projectsWithoutAProjectLeader().get(0);
        stm.AssignProjectLeader(employee,project);

        assertEquals(stm.getProjectLeaders().size(),2);
        assertEquals(stm.getProjectLeaderByID("Emil").getID(),employee.getID());

        // The employee by ID: Emil should not be a employee anymore
        assertEquals(stm.getEmployeeByID("Emil"),null);

        // See if the project leader is assign
        assertEquals(stm.getProjectLeaderByID("Emil").getAssignedProject(),project);
    }

    /*
        Assigning a project leader to a project with a project leader already assigned
        should not be possible.
     */
    @Test
    public void testAssignProjectLeaderToAProjectWithAProjectLeader() {
        Project project = stm.projectsWithoutAProjectLeader().get(0);

        assertTrue(stm.projectsWithoutAProjectLeader().contains(project));

        stm.AssignProjectLeader(stm.getEmployeeByID("Emil"),project);

        assertFalse(stm.projectsWithoutAProjectLeader().contains(project));
    }

}
