/**
 * Created by Emil on 27/03/2017.
 */
public class Employee {
	private String ID;
	private String CurrentProject;
	private TimeManager TimeManagementArray = new TimeManager();

	public Employee(String ID) {
		this.ID = ID;
	}

	public Employee(String ID, String currentProject) {
		this.ID = ID;
		CurrentProject = currentProject;
	}

	public void AddProject (String ProjectName, int StartWeek){
		SystemTimeManager.newProject(ProjectName,StartWeek);
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
}