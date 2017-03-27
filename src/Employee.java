/**
 * Created by Emil on 27/03/2017.
 */
public class Employee {
	String ID;
	String CurrentProject;
	int[][] TimeManagementArray;

	public Employee(String ID, int[][] timeManagementArray) {
		this.ID = ID;
		TimeManagementArray = timeManagementArray;
	}

	public Employee(String ID, String currentProject, int[][] timeManagementArray) {
		this.ID = ID;
		CurrentProject = currentProject;
		TimeManagementArray = timeManagementArray;
	}

	public void AddProject
}
