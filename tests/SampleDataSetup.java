import TimeManagement.Domain.Employee;
import TimeManagement.Domain.Project;
import TimeManagement.Domain.ProjectLeader;
import TimeManagement.Domain.SystemTimeManager;
import org.junit.Before;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by William Ben Embarek on 18/04/2017.
 */
public class SampleDataSetup {
	SystemTimeManager stm = new SystemTimeManager();
	@Before
	public void setUp() throws Exception{
		Employee e1 = new Employee("Emil",stm);
		stm.employees.add(e1);
		Employee e2 = new Employee("William",stm);
		stm.employees.add(e2);
		Employee e3 = new Employee("Test person",stm);
		stm.employees.add(e3);
		for (int i  = 0; i< 10; i++){
			stm.employees.add(new Employee("e"+i,stm));
		}
		Project testProject = new Project("TestProject",1);
		stm.projects.add(testProject);

		ProjectLeader projectManager = new ProjectLeader("TimeManagement.Domain.ProjectLeader","testProject",testProject);
		stm.projectLeaders.add(projectManager);
		for (int i = 0; i < 15; i++) {
			projectManager.addActivity("activity"+i,i,0,ThreadLocalRandom.current().nextInt(2,11));
		}

	}
}
