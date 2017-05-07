import TimeManagement.Domain.InvalidInputException;
import TimeManagement.Domain.NameAlreadyExistException;
import TimeManagement.Domain.Project;
import TimeManagement.Domain.ProjectActivity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Emil on 07/05/2017.
 */
public class TestEditActivity extends SampleDataSetup{
    /*
        The start week can not be change to a week number bigger than the end week
        or something smaller than the project start week
     */
    @Test
    public void testNewStartWeek() throws InvalidInputException, NameAlreadyExistException {
        Project testProject = stm.getProjects().get(0);
        ProjectActivity A = new ProjectActivity("Test",1.0, 1,2);
        testProject.addActivity(A);

        try {
            A.setStartWeek(3);
            fail("TimeManagement.Domain.InvalidInputException exception should have been thrown");
        } catch (InvalidInputException e) {

            assertEquals("The input is invalid.", e.getMessage());
            assertEquals("Try again", e.getOperation());
        }

        // We check if the input is smaller than the project start week in the GUI
    }
    /*
        The new end week can not be before start week of a project
     */
    @Test
    public void testNewEndWeek() throws InvalidInputException, NameAlreadyExistException {
        Project testProject = stm.getProjects().get(0);
        ProjectActivity A = new ProjectActivity("Test",1.0, 1,2);
        testProject.addActivity(A);

        try {
            A.setEndWeek(0);
            fail("TimeManagement.Domain.InvalidInputException exception should have been thrown");
        } catch (InvalidInputException e) {

            assertEquals("The input is invalid.", e.getMessage());
            assertEquals("Try again", e.getOperation());
        }
    }
}
