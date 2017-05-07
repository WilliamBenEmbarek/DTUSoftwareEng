import TimeManagement.Domain.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Emil on 07/05/2017.
 */
public class TestNewActivity extends SampleDataSetup{

    /*
       Create new Activity
    */
    @Test
    public void testNewActivity() throws InvalidInputException, NameAlreadyExistException {
        Project testProject = stm.getProjects().get(0);
        int numberOfActivities = testProject.getActivities(stm.getCurrentWeek()).size();
        testProject.addActivity(new ProjectActivity("Test",1.0, stm.getCurrentWeek()));
        assertEquals( testProject.getActivities(stm.getCurrentWeek()).size(),numberOfActivities+1);
    }

    /*
        When creating a project activity, you have to make sure the start week of the activity is after project start week
     */
    @Test
    public void testNewActivityWithInvalidStartWeek() throws InvalidInputException{
        Project testProject = stm.getProjects().get(0);
        int startWeek = testProject.getStartWeek();

        try {
            testProject.addActivity(new ProjectActivity("Test",1.0, startWeek-1));
            fail("TimeManagement.Domain.InvalidInputException exception should have been thrown");
        } catch (InvalidInputException e) {

            assertEquals("The input is invalid.", e.getMessage());
            assertEquals("Try again", e.getOperation());
        } catch (NameAlreadyExistException e) {
            e.printStackTrace();
        }
    }
    /*
        The name of the Activity should be unique
     */
    @Test
    public void testNewActivityWithUniqueName() throws InvalidInputException, NameAlreadyExistException {
        Project testProject = stm.getProjects().get(0);
        testProject.addActivity(new ProjectActivity("Test",1.0, stm.getCurrentWeek()));

        try {
            testProject.addActivity(new ProjectActivity("Test",1.0, stm.getCurrentWeek()));
            fail("TimeManagement.Domain.NameAlreadyExistException exception should have been thrown");
        } catch (NameAlreadyExistException e) {

            assertEquals("This name already exist.", e.getMessage());
            assertEquals("Change name", e.getOperation());
        }


    }

}
