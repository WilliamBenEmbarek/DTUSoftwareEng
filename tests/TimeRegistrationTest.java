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
		// assertEquals(test.getHoursWorkedDayActivity(stm.getCurrentWeek(), 1), 10,0);

	}



}
