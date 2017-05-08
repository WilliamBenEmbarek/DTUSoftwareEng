import TimeManagement.Domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by William Ben Embarek on 08/05/2017.
 */
public class TestEditHours extends SampleDataSetup{
	//Choice A in black box
	@Test
	public void testEditHoursNoInput() throws Exception {
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
		test.refreshActivties();
		test.editHours(1,1,0.0);
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(), 1), 0.0,0);
	}
	//Choice B
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
		// assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(), 1), 9,0); //Assign 4 hours to activity 1, 3 to actvity 6 and 2 to activity 0, total is 9

		test.editHours(6.0,1,8);
		test.editHours(1.0,1,2); //Edit the total number of hours for activities 6 and 1
		// assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(),1),12,0); //New total is now 12 hours

	}
	//Choice B1
	@Test
	public void testEditHoursOverwrite() throws Exception {
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
		test.registerHours(1, 1,5); //Register 10 hours on monday
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(), 1), 5,0);
		test.editHours(2, 1,4);
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(), 1), 9,0);
	}

	@Test
	public void testEditHoursOver24() throws Exception {
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
		test.refreshActivties();
		test.editHours(1,1,28.0);
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(), 1), 24.0,0);
	}

	@Test
	public void testEditHoursTotalOver24() throws Exception {
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
		test.refreshActivties();
		test.editHours(1,1,14.0);
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(), 1), 14.0,0);
		test.editHours(2,1,16.0);
		assertEquals(test.getHoursWorkedDay(stm.getCurrentWeek(), 1), 24.0,0);
		assertEquals(test.getHoursWorkedDayActivity(stm.getCurrentWeek(), 1,activities.get(2)), 10.0,0);
	}
	@Test
	public void testEditHoursNegative() throws Exception {
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
		test.refreshActivties();
		test.editHours(1,1,-3);
		Assert.fail("Number of hours cannot be negative.");
	}
}
