import org.junit.Before;

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

		Project testProject = new Project("TestProject",1);

		ProjectLeader projectManager = new ProjectLeader("201701","testProject",testProject);
		STM.ProjectLeaders.add(projectManager);

	}
}
