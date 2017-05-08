import TimeManagement.Domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by William Ben Embarek on 08/05/2017.
 */
public class TestPersonalActivity extends SampleDataSetup{
	@Test
	public void testAssignPersonalActivity() throws UnableToAssignException {
		//Step 0
		ArrayList<Employee> employees = stm.getEmployees();
		String ID = "Test person";
		Employee test = stm.getEmployeeByID(ID);
		ProjectLeader p1 = stm.getProjectLeaders().get(0);
		Project testP = stm.getProjects().get(0);
		ArrayList<ProjectActivity> activities = testP.getActivities(stm.getCurrentWeek()); //Set up employee, project leader, project and activities list
		//Step 1
		assertEquals(ID, test.getID()); //Check if the employee test's ID is acutally "Test person".
		try {
			p1.addPersonalActivity("Vacation",1.0,0,2,test);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		} catch (NameAlreadyExistException e) {
			e.printStackTrace();
		}
		employees.remove(test);
		assertEquals(stm.AvailableEmployeesForAGivenActivity(activities.get(0)),employees);
	}
}
