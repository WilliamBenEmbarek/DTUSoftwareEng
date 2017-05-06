import TimeManagement.System.Employee;
import TimeManagement.System.Project;
import TimeManagement.System.ProjectLeader;
import TimeManagement.System.SystemTimeManager;
import org.junit.Before;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by William Ben Embarek on 18/04/2017.
 */
public class SampleDataSetup {
	SystemTimeManager STM = new SystemTimeManager();
	@Before
	public void setUp() throws Exception{
		Employee e1 = new Employee("Emil");
		STM.Employees.add(e1);
		Employee e2 = new Employee("William");
		STM.Employees.add(e2);
		Employee e3 = new Employee("Test person");
		STM.Employees.add(e3);
		for (int i  = 0; i< 10; i++){
			STM.Employees.add(new Employee("e"+i));
		}
		Project testProject = new Project("TestProject",1);
		STM.Projects.add(testProject);

		ProjectLeader projectManager = new ProjectLeader("TimeManagement.System.ProjectLeader","testProject",testProject);
		STM.ProjectLeaders.add(projectManager);
		for (int i = 0; i < 15; i++) {
			projectManager.addActivity("activity"+i,i,ThreadLocalRandom.current().nextInt(1,6),ThreadLocalRandom.current().nextInt(2,11));
		}

	}
}
