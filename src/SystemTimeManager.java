import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by William Ben Embarek on 27/03/2017.
 */
public class SystemTimeManager {
	// Define ArrayLists
	public static ArrayList<ProjectLeader> ProjectLeaders = new ArrayList<ProjectLeader>();
	public static ArrayList<Employee> Employees = new ArrayList<Employee>(0);
	public static ArrayList<Project> Projects = new ArrayList<Project>(0);


	public SystemTimeManager(){

	}

	public void AssignProjectLeader(Employee E, Project P){
		ProjectLeader PL = new ProjectLeader(E.getID(),P.getProjectName());
		ProjectLeaders.add(PL);
		Employees.remove(E);
		P.projectLeader = PL;
	}

	public ArrayList<Employee> AvailableEmployees (int week) {
		ArrayList<Employee> AvailableEmployees = new ArrayList<Employee>(0);


		return(AvailableEmployees);
	}

	public ArrayList<Employee> getEmployees() {
		return Employees;
	}

	public ArrayList<ProjectLeader> getProjectLeaders() {
		return ProjectLeaders;
	}

	public ArrayList<Project> getProjects() {
		return Projects;
	}

	public static void newProject(String ProjectName, int StartWeek) {
		Projects.add(new Project(ProjectName,StartWeek));
	}

	public static void newProject(String ProjectName, int StartWeek, Employee projectLeader) {
		Employees.remove(projectLeader);
		ProjectLeader PL = new ProjectLeader(projectLeader.getID(), ProjectName);
		ProjectLeaders.add(PL);
		Projects.add(new Project(ProjectName,StartWeek,PL));
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
		return null;
	}

	public ProjectLeader getProjectLeaderByID(String ID){
		for(int i = 0; i<ProjectLeaders.size();i++){
			if(ProjectLeaders.get(i).getID().equals(ID)){
				return ProjectLeaders.get(i);
			}
		}
		return null;
	}

	public boolean doesProjectIDExist(String ID){
		for (int i = 0; i<Projects.size(); i++){
			if(Projects.get(i).getProjectName().equals(ID)) {
				return true;
			}
		}
		return false;
	}
}
