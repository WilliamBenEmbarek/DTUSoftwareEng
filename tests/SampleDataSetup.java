import org.junit.Before;

/**
 * Created by William Ben Embarek on 18/04/2017.
 */
public class SampleDataSetup {
	SystemTimeManager STM = new SystemTimeManager();
	Employee e1 = new Employee("Emil");
	Employee e2 = new Employee("William");
	Employee e3 = new Employee("Test person");
	Project testProject = new Project("TestProject",1);
	ProjectLeader projectManager = new ProjectLeader("201701","testProject",testProject);
	@Before
	public void setUp() throws Exception{

		STM.Employees.add(e1);
		STM.Employees.add(e2);
		STM.Employees.add(e3);
		STM.ProjectLeaders.add(projectManager);

	}
}
