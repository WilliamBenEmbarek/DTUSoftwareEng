import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by William Ben Embarek on 18/04/2017.
 */
public class TestAssignActivities extends SampleDataSetup {
	@Test
	public void testAssignActivites() throws Exception {
		//Step 1
		ArrayList<Employee> employees = STM.getEmployees();
		String ID = "Test person";
		Employee test = STM.getEmployeeByID(ID);
		assertEquals(ID, test.getID());
		//Step 2
		int numActivites = test.getAssignedActivites().size();
		assertEquals(numActivites, 0); // Number Activities = 0
		//Step 3
		ProjectLeader p1 = STM.getProjectLeaders().get(0);
		Project testP = STM.getProjects().get(0);
		ArrayList<ProjectActivity> activities = testP.getActivities();
		p1.assignEmployee(test, activities.get(0));
		numActivites = test.getAssignedActivites().size();
		assertEquals(numActivites, 1);

	}
}
