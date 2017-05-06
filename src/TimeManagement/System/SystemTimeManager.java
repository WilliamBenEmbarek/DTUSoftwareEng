package TimeManagement.System;

import java.util.ArrayList;

/**
 * Created by William Ben Embarek on 27/03/2017.
 */
public class SystemTimeManager {
	// Define ArrayLists
	public static ArrayList<ProjectLeader> ProjectLeaders = new ArrayList<ProjectLeader>();
	public static ArrayList<Employee> Employees = new ArrayList<Employee>(0);
	public static ArrayList<Project> Projects = new ArrayList<Project>(0);
	private static int CurrentWeek = 0;
	private static int CurrentDay = 1;

	public SystemTimeManager(){
	}

	public void AssignProjectLeader(Employee E, Project P){
		ProjectLeader PL = new ProjectLeader(E.getID(),P.getProjectName(),P);
		P.setProjectLeader(PL);
		ProjectLeaders.add(PL);
		Employees.remove(E);
	}

	public ArrayList<Employee> AvailableEmployeesForAGivenWeek (int gWeek) {
		ArrayList<Employee> AvailableEmployees = new ArrayList<Employee>(0);
		for (int i = 0; i < Employees.size(); i++) {
			if (Employees.get(i).getCurrentProject() == null) {
				AvailableEmployees.add(Employees.get(i));
			} else if (Employees.get(i).getAssignedActivites().size() < 10) {
				AvailableEmployees.add(Employees.get(i));
			} else if (AvailableEmployees.size() < 5) {
				if (Employees.get(i).getAssignedActivites().size() < 15) {
					AvailableEmployees.add(Employees.get(i));
				}
			}

		}
		return (AvailableEmployees);
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

	public Project getProjectByID(String ID){
		for(int i = 0; i<Projects.size();i++){
			if(Projects.get(i).getProjectName().equals(ID)){
				return Projects.get(i);
			}
		}
		return null;
	}

	public static void newProject(String ProjectName, int StartWeek) throws NameAlreadyExistException {
		if(doesProjectIDExist(ProjectName)==true){
			throw new NameAlreadyExistException("This name already exist.");
		}
		else {
			Projects.add(new Project(ProjectName, StartWeek));
		}
	}

	public static void newProject(String ProjectName, int StartWeek, Employee projectLeader) throws NameAlreadyExistException {
		if(doesProjectIDExist(ProjectName)==true){
			throw new NameAlreadyExistException("This name already exist.");
		}
		else {
			Project P = new Project(ProjectName, StartWeek);
			Employees.remove(projectLeader);
			ProjectLeader PL = new ProjectLeader(projectLeader.getID(), ProjectName, P);
			P.setProjectLeader(PL);
			ProjectLeaders.add(PL);
			Projects.add(P);
		}
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

	public static boolean doesProjectIDExist(String ID){
		for (int i = 0; i<Projects.size(); i++){
			if(Projects.get(i).getProjectName().equals(ID)) {
				return true;
			}
		}
		return false;
	}

	public static int getCurrentWeek() {
		return CurrentWeek;
	}

	// Hardcode employees
	public void setUpEmployees(){
		Employee e1 = new Employee("Emil");
		Employees.add(e1);
		Employee e2 = new Employee("William");
		Employees.add(e2);
		Employee e3 = new Employee("Test person");
		Employees.add(e3);
	}

	public static int getCurrentDay() {
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
