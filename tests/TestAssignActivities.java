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
	@Test
	public void testAssignActivites() throws Exception {
		//Step 0
		ArrayList<Employee> employees = STM.getEmployees();
		String ID = "Test person";
		Employee test = STM.getEmployeeByID(ID);
		ProjectLeader p1 = STM.getProjectLeaders().get(0);
		Project testP = STM.getProjects().get(0);
		ArrayList<ProjectActivity> activities = testP.getActivities();
		//Step 1
		assertEquals(ID, test.getID());

		//Step 2
		int numActivites = test.getAssignedActivites().size();
		assertEquals(numActivites, 0); // Number Activities = 0
		// step 3
		for (int i = 0;i<activities.size();i++) {
			p1.assignEmployee(test,activities.get(i));
		}
		test.refreshActivties();
		assertEquals(activities,test.getAssignedActivites());

	}
}
