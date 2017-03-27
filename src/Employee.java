/**
 * Created by Emil on 27/03/2017.
 */
public class Employee {
	String ID;
	String CurrentProject;
	TimeManager TimeManagementArray = new TimeManager();

	public Employee(String ID) {
		this.ID = ID;
	}

	public Employee(String ID, String currentProject) {
		this.ID = ID;
		CurrentProject = currentProject;
	}

	public void AddProject (String ProjectName, int StartWeek){
		SystemTimeManager.NewProject(ProjectName,StartWeek);
	}
}