import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import TimeManagement.Domain.Employee;
import TimeManagement.Domain.Project;
import TimeManagement.Domain.ProjectActivity;
import TimeManagement.Domain.ProjectLeader;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017.
 */
public class TimeRegistrationTest extends SampleDataSetup{

	@Test
	public void testRegisterHours() throws Exception {
		//Step 0
		ArrayList<Employee> employees = stm.getEmployees();
		String ID = "Test person";
		Employee test = stm.getEmployeeByID(ID);
		ProjectLeader p1 = stm.getProjectLeaders().get(0);
		Project testP = stm.getProjects().get(0);
		ArrayList<ProjectActivity> activities = testP.getActivities();

		for (ProjectActivity activity : activities) {
			p1.assignEmployee(test, activity);
		}
		//Step 1
		test.refreshActivties();
		test.registerHours(1, 10);
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(), 1), 10);


	}

	@Test
	public void testEditHours() throws Exception {
		ArrayList<Employee> employees = stm.getEmployees();
		String ID = "Test person";
		Employee test = stm.getEmployeeByID(ID);
		ProjectLeader p1 = stm.getProjectLeaders().get(0);
		Project testP = stm.getProjects().get(0);
		ArrayList<ProjectActivity> activities = testP.getActivities();
		for (ProjectActivity activity : activities) {
			p1.assignEmployee(test, activity);
		}
		//Step 1
		test.refreshActivties();
		test.registerHours(1, 4);
		test.registerHours(6,2);
		test.registerHours(0,3);
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(),1),9);

		test.editHours(6,stm.getCurrentDay(),8);
		test.editHours(1,stm.getCurrentDay(),2);
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(),1),13);

	}
}
