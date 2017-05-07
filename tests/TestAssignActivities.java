import TimeManagement.Domain.Employee;
import TimeManagement.Domain.Project;
import TimeManagement.Domain.ProjectActivity;
import TimeManagement.Domain.ProjectLeader;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by William Ben Embarek on 18/04/2017.
 */
public class TestAssignActivities extends SampleDataSetup {

	/*
		Assign employee to Activty
	 */
	@Test
	public void testAssignActivites() throws Exception {
		//Step 0
		ArrayList<Employee> employees = stm.getEmployees();
		String ID = "Test person";
		Employee test = stm.getEmployeeByID(ID);
		ProjectLeader p1 = stm.getProjectLeaders().get(0);
		Project testP = stm.getProjects().get(0);
		ArrayList<ProjectActivity> activities = testP.getActivities(stm.getCurrentWeek()); //Set up employee, project leader, project and activities list
		//Step 1
		assertEquals(ID, test.getID()); //Check if the employee test's ID is acutally "Test person".

		//Step 2
		int numActivites = test.getAssignedActivites().size(); //Check total number of assigned activities should be 0
		assertEquals(numActivites, 0); // Number Activities = 0
		// step 3
		for (int i = 0;i<activities.size();i++) {
			p1.assignEmployee(test,activities.get(i));
		}
		test.refreshActivties();
		assertEquals(activities,test.getAssignedActivites()); //Check if the employees assigned activities matches the activities list.
	}

}
