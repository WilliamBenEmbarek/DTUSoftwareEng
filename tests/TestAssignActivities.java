import TimeManagement.Domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by William Ben Embarek on 18/04/2017.
 */
public class TestAssignActivities extends SampleDataSetup {

	/*
		Assign employee to activty
	 */
	@Test
	public void testAssignActivites() throws UnableToAssignException {
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

	/*
		After assigned to activity the employee is assigned to project
	 */
	@Test
	public void testAssignActivityAndProject() throws InvalidInputException, NameAlreadyExistException, UnableToAssignException {
		Project testProject = new Project("testProject",stm.getCurrentWeek());
		ProjectLeader pL = new ProjectLeader("Leader","Test",testProject);
		ProjectActivity A = new ProjectActivity("Test",1.0, stm.getCurrentWeek());
		testProject.addActivity(A);

		Employee E = new Employee("Person",stm);

		pL.assignEmployee(E,A);

		assertEquals(E.getCurrentProject().toString(),"testProject");

	}
	/*
		An employee can not be assigned to an activity twice
	 */
	@Test
	public void testAssignTwice() throws InvalidInputException, NameAlreadyExistException, UnableToAssignException {
		Project testProject = new Project("testProject",stm.getCurrentWeek());
		ProjectLeader pL = new ProjectLeader("Leader","Test",testProject);
		ProjectActivity A = new ProjectActivity("Test",1.0, stm.getCurrentWeek());
		testProject.addActivity(A);

		Employee E = new Employee("Person",stm);

		pL.assignEmployee(E,A);

		try {
			pL.assignEmployee(E,A);
			fail("TimeManagement.Domain.UnableToAssignException exception should have been thrown");
		} catch (UnableToAssignException e) {

			assertEquals("Unable to assign exception.", e.getMessage());
			assertEquals("Try again", e.getOperation());
		}
	}
	/*
		If a employee has 10 activities assigned, they can not be assigned to one more
	 */
	@Test
	public void testEmployeeAssigned10Activities() throws InvalidInputException, NameAlreadyExistException, UnableToAssignException {
		Project testProject = new Project("testProject",stm.getCurrentWeek());
		ProjectLeader pL = new ProjectLeader("Leader","Test",testProject);
		Employee E = new Employee("Person",stm);

		// Add 10 activities to project activity and assign all activities to employee
		for(int i=0; i<10;i++){
			ProjectActivity tempA = new ProjectActivity("Test"+i,(double) i+.0, stm.getCurrentWeek());
			testProject.addActivity(tempA);
			pL.assignEmployee(E,tempA);
		}
		assertEquals(E.getAssignedActivites().size(),10);

		// Now he should not be avaliable for the next activity
		ProjectActivity A = new ProjectActivity("Test",11.0,stm.getCurrentWeek());
		assertFalse(stm.AvailableEmployeesForAGivenActivity(A).contains(E));
	}

}
