package TimeManagement.Domain;

import java.util.ArrayList;

/**
 * Created by William Ben Embarek on 27/03/2017.
 */
public class SystemTimeManager {
	// Define ArrayLists
	public ArrayList<ProjectLeader> projectLeaders = new ArrayList<ProjectLeader>();
	public ArrayList<Employee> employees = new ArrayList<Employee>(0);
	public ArrayList<Project> projects = new ArrayList<Project>(0);
	private int CurrentWeek = 0;
	private int CurrentDay = 1;

	public SystemTimeManager(){
	}

	public void AssignProjectLeader(Employee E, Project P){
		ProjectLeader PL = new ProjectLeader(E.getID(),P.getProjectName(),P);
		P.setProjectLeader(PL);
		projectLeaders.add(PL);
		employees.remove(E);
	}

	public ArrayList<Employee> AvailableEmployeesForAGivenWeek (int gWeek) {
		ArrayList<Employee> AvailableEmployees = new ArrayList<Employee>(0);
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getCurrentProject() == null) {
				AvailableEmployees.add(employees.get(i));
			} else if (employees.get(i).getAssignedActivites().size() < 10) {
				AvailableEmployees.add(employees.get(i));
			} else if (AvailableEmployees.size() < 2) {
				if (employees.get(i).getAssignedActivites().size() < 15) {
					AvailableEmployees.add(employees.get(i));
				}
			}

		}
		return (AvailableEmployees);
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public ArrayList<ProjectLeader> getProjectLeaders() {
		return projectLeaders;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public Project getProjectByID(String ID){
		for(int i = 0; i< projects.size(); i++){
			if(projects.get(i).getProjectName().equals(ID)){
				return projects.get(i);
			}
		}
		return null;
	}

	public void newProject(String ProjectName, int StartWeek) throws NameAlreadyExistException {
		if(doesProjectIDExist(ProjectName)==true){
			throw new NameAlreadyExistException("This name already exist.");
		}
		else {
			this.projects.add(new Project(ProjectName, StartWeek));
		}
	}

	public void newProject(String ProjectName, int StartWeek, Employee projectLeader) throws NameAlreadyExistException {
		if(doesProjectIDExist(ProjectName)==true){
			throw new NameAlreadyExistException("This name already exist.");
		}
		else {
			Project P = new Project(ProjectName, StartWeek);
			this.employees.remove(projectLeader);
			ProjectLeader PL = new ProjectLeader(projectLeader.getID(), ProjectName, P);
			P.setProjectLeader(PL);
			projectLeaders.add(PL);
			projects.add(P);
		}
	}

	public ArrayList<Project> projectsWithoutAProjectLeader(){
		ArrayList<Project> tempArray = new ArrayList<>();

		for(int i = 0; i< projects.size(); i++){
			if(projects.get(i).getProjectLeader()==null){
				tempArray.add(projects.get(i));
			}
		}
		return tempArray;
	}

	public Employee getEmployeeByID(String ID){
		for(int i = 0; i< employees.size(); i++){
			if(employees.get(i).getID().equals(ID)){
				return employees.get(i);
			}
		}
		return null;
	}

	public ProjectLeader getProjectLeaderByID(String ID){
		for(int i = 0; i< projectLeaders.size(); i++){
			if(projectLeaders.get(i).getID().equals(ID)){
				return projectLeaders.get(i);
			}
		}
		return null;
	}

	public boolean doesProjectIDExist(String ID){
		for (int i = 0; i< this.projects.size(); i++){
			if(this.projects.get(i).getProjectName().equals(ID)) {
				return true;
			}
		}
		return false;
	}

	public int getCurrentWeek() {
		return CurrentWeek;
	}

	// Hardcode employees
	public void setUpEmployees(){
		Employee e1 = new Employee("Emil",this);
		employees.add(e1);
		Employee e2 = new Employee("William",this);
		employees.add(e2);
		Employee e3 = new Employee("Test person",this);
		employees.add(e3);
	}
	public void nextWeek(){
		CurrentWeek = CurrentWeek+1;
	}
	public int getCurrentDay() {
		return CurrentDay;
	}

	// Hardcoding of login for each employee
	public  boolean checkLogin(String ID, String pass){

		if(ID.equals("Emil") && pass.equals("123")){
			return true;
		}
		if(ID.equals("William") && pass.equals("321")){
			return true;
		}
		return false;
	}
}
