import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by William Ben Embarek on 27/03/2017.
 */
public class SystemTimeManager {
	// Define ArrayLists
	ArrayList<Employee> Employees = new ArrayList<Employee>(0);
	ArrayList<Project> Projects = new ArrayList<Project>(0);

	public SystemTimeManager(ArrayList<Employee> employees, ArrayList<Project> projects) {
		Employees = employees;
		Projects = projects;
	}

	public void AssignProjectLeader(Employee E, Project P){

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
}
