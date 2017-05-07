import TimeManagement.Domain.InvalidInputException;
import TimeManagement.Domain.NameAlreadyExistException;
import TimeManagement.Domain.Project;
import TimeManagement.Domain.ProjectActivity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Emil on 07/05/2017.
 */
public class TestWeekExpired extends SampleDataSetup{
    /*
      Test the change of a week
     */
    @Test
    public void testNextWeek(){
        int thisWeek = stm.getCurrentWeek();
        stm.nextWeek();
        assertEquals(stm.getCurrentWeek(),thisWeek+1);
    }
    /*
        An activity can expire and thereby no longer be assigned
     */
    @Test
    public void testActivtyNoLongerExistAfterEndWeek() throws InvalidInputException, NameAlreadyExistException {
        Project testProject = stm.getProjects().get(0);
        ProjectActivity A = new ProjectActivity("Test",1.0, 0,1);
        testProject.addActivity(A);
        assertTrue(testProject.getActivities(0).contains(A));

        assertFalse(testProject.getActivities(2).contains(A));

    }

}
