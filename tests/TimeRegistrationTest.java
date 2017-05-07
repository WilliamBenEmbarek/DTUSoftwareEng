import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import TimeManagement.Domain.Employee;
import TimeManagement.Domain.Project;
import TimeManagement.Domain.ProjectActivity;
import TimeManagement.Domain.ProjectLeader;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017
 */
public class TimeRegistrationTest extends SampleDataSetup{

	@Test
	public void testRegisterHours() throws Exception {
		//Step 0
		ArrayList<Employee> employees = stm.getEmployees();
		String ID = "Test person";
		Employee test = stm.getEmployeeByID(ID);
		ProjectLeader p1 = stm.getProjectLeaders().get(0);
		Project testP = stm.getProjects().get(0); //Set up employees
		ArrayList<ProjectActivity> activities = testP.getActivities(stm.getCurrentWeek()); // Get a list of the activities

		for (ProjectActivity activity : activities) { // For every activity in the activities have Project Leader P1 assign it to employee Test
			p1.assignEmployee(test, activity);
		}
		//Step 1
		test.refreshActivties(); // refresh the activities in the employees personal 3d array
		test.registerHours(1, 1,10.0); //Register 10 hours on monday
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(), 1), 10,0);

	}

	@Test
	public void testEditHours() throws Exception {
		ArrayList<Employee> employees = stm.getEmployees();
		String ID = "Test person";
		Employee test = stm.getEmployeeByID(ID);
		ProjectLeader p1 = stm.getProjectLeaders().get(0);
		Project testP = stm.getProjects().get(0);
		ArrayList<ProjectActivity> activities = testP.getActivities(stm.getCurrentWeek()); //Same as before
		for (ProjectActivity activity : activities) {
			p1.assignEmployee(test, activity);
		}
		//Step 1
		test.refreshActivties();
		test.registerHours(1.0, 1,4.0);
		test.registerHours(6.0,1,3.0);
		test.registerHours(0.0,1,2.0);
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(), 1), 9,0); //Assign 4 hours to activity 1, 3 to actvity 6 and 2 to activity 0, total is 9

		test.registerHours(6.0,1,8);
		test.registerHours(1.0,1,2); //Edit the total number of hours for activities 6 and 1
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(),1),12,0); //New total is now 12 hours

	}

}
