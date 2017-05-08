import TimeManagement.Domain.Employee;
import TimeManagement.Domain.Project;
import TimeManagement.Domain.ProjectActivity;
import TimeManagement.Domain.ProjectLeader;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by William Ben Embarek on 08/05/2017.
 */
public class TestEditHours extends SampleDataSetup{
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

		test.editHours(6.0,1,8);
		test.editHours(1.0,1,2); //Edit the total number of hours for activities 6 and 1
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(),1),12,0); //New total is now 12 hours

	}
}
