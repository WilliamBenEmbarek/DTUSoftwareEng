import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by William Ben Embarek on 27/03/2017.
 */
public class SystemTimeManager {
	// Define ArrayLists
	public static ArrayList<Employee> ProjectLeaders = new ArrayList<Employee>();
	public static ArrayList<Employee> Employees = new ArrayList<Employee>(0);
	public static ArrayList<Project> Projects = new ArrayList<Project>(0);


	public SystemTimeManager(){

	}

	public SystemTimeManager(ArrayList<Employee> employees, ArrayList<Project> projects) {
		Employees = employees;
		Projects = projects;
	}

	public void AssignProjectLeader(Employee E, Project P){
		ProjectLeaders.add(E);
		Employees.remove(E);
		P.projectLeader = E;
	}

	public boolean canTheIDBeAssignToProjectLeader(String ID){
		for(int i = 0; i<this.Employees.size(); i++){
			if(this.Employees.get(i).getID().equals(ID)){
				return true;
			}
		}
		return false;
	}

	public boolean doesTheProjectExist(String projectName){
		for(int i = 0; i<this.Projects.size();i++){
			if(this.Projects.get(i).getProjectName().equals(projectName)){
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

	public static void newProject(String ProjectName, int StartWeek, Employee projectLeader) {
		ProjectLeaders.add(projectLeader);
		Employees.remove(projectLeader);
		Projects.add(new Project(ProjectName,StartWeek,projectLeader));
	}

	public ArrayList<Project> projectsWithoutAProjectLeader(){
		ArrayList<Project> tempArray = new ArrayList<>();

		for(int i = 0; i<Projects.size();i++){
			if(Projects.get(i).getProjectLeader()==null){
				tempArray.add(Projects.get(i));
			}
		}
		return tempArray;
	}

	public Employee getEmployeeByID(String ID){
		for(int i = 0; i<Employees.size();i++){
			if(Employees.get(i).getID().equals(ID)){
				return Employees.get(i);
			}
		}
		for(int i = 0; i<ProjectLeaders.size();i++){
			if(ProjectLeaders.get(i).getID().equals(ID)){
				return ProjectLeaders.get(i);
			}
		}
		return null;
	}
}
