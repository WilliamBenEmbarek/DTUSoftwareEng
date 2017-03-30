import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by William Ben Embarek on 27/03/2017.
 */
public class SystemTimeManager {
	// Define ArrayLists
	public static ArrayList<String> ProjectLeaderIDs = new ArrayList<String>();
	public ArrayList<Employee> Employees = new ArrayList<Employee>(0);
	public static ArrayList<Project> Projects = new ArrayList<Project>(0);

	public SystemTimeManager(){
		this.ProjectLeaderIDs.add("Emil");
	}

	public SystemTimeManager(ArrayList<Employee> employees, ArrayList<Project> projects) {
		Employees = employees;
		Projects = projects;
	}

	public void AssignProjectLeader(Employee E, Project P){
		ProjectLeaderIDs.add(E.getID());

	}

	public boolean canTheIDBeAssignToProjectLeader(String ID){
		for(int i = 0; i<this.Employees.size(); i++){
			if(this.Employees.get(i).getID().equals(ID) && !this.ProjectLeaderIDs.contains(ID)){
				return true;
			}
		}
		return false;
	}

	public ArrayList<Employee> AvailableEmployees (int week) {
		ArrayList<Employee> AvailableEmployees = new ArrayList<Employee>(0);


		return(AvailableEmployees);
	}

	public ArrayList<Employee> getEmployees() {
		return Employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		Employees = employees;
	}

	public ArrayList<Project> getProjects() {
		return Projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		Projects = projects;
	}

	public static void newProject(String ProjectName, int StartWeek) {
		Projects.add(new Project(ProjectName,StartWeek));
	}

	public static void newProject(String ProjectName, int StartWeek, String projectLeader) {
		ProjectLeaderIDs.add(projectLeader);
		Projects.add(new Project(ProjectName,StartWeek,projectLeader));
	}
}
