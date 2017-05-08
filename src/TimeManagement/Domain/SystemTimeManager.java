package TimeManagement.Domain;

import sun.security.x509.AVA;

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

	public SystemTimeManager() {
	}

	public void AssignProjectLeader(Employee E, Project P) {
		ProjectLeader PL = new ProjectLeader(E.getID(), P.getProjectName(), P);
		P.setProjectLeader(PL);
		projectLeaders.add(PL);
		employees.remove(E);
	}

	public ArrayList<Employee> AvailableEmployeesForAGivenActivity(Activity A) {
		assert A != null && A.getStartWeek() >= 0 && A.getEndWeek() > A.getStartWeek();
		int startWeek = A.getStartWeek();
		int endWeek = A.getEndWeek();

		ArrayList<Employee> AvailableEmployees = new ArrayList<Employee>(0);
		for (Employee employee : employees) { //1
			if (employee.getCurrentProject() == null) { //2
				AvailableEmployees.add(employee);
			} else if (employee.getAssignedActivites().contains(A) || employee.getFutureAssignedActivties().contains(A)) { // 3
				// Already assigned this activity
			} else{
				ArrayList<Activity> ActivitiesInPeriod = new ArrayList<>();
				for (int j = 0; j < employee.getAssignedActivites().size(); j++) {
					int activityStartWeek = employee.getAssignedActivites().get(j).getStartWeek();
					int activityEndWeek = employee.getAssignedActivites().get(j).getEndWeek();
					if ((activityStartWeek < startWeek || activityStartWeek > endWeek) && (activityEndWeek < startWeek || activityEndWeek > endWeek)) {
						// Activity is not in the current period
					} else {
						ActivitiesInPeriod.add(employee.getAssignedActivites().get(j));
					}
				}
				if (!(ActivitiesInPeriod.size() > 10)) { //4
					AvailableEmployees.add(employee);
				}

			} if (employee.unableToWork) { //5
				AvailableEmployees.remove(employee);
			}

		}
		assert EmployeeTester(AvailableEmployees,A);
		return (AvailableEmployees);
	}
	private boolean EmployeeTester(ArrayList<Employee> x, Activity A) {
		boolean result = true;
		for (Employee employee : x) {
			ArrayList<Activity> Eactivities = employee.getAssignedActivites();
			if (!(Eactivities.size() < 10 && !employee.unableToWork && !Eactivities.contains(A))) {
				result = false;
			}
		}
		return result;
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

	public Project getProjectByID(String ID) {
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).getProjectName().equals(ID)) {
				return projects.get(i);
			}
		}
		return null;
	}

	public void newProject(String ProjectName, int StartWeek) throws NameAlreadyExistException {
		if (doesProjectIDExist(ProjectName) == true) {
			throw new NameAlreadyExistException("This name already exist.");
		} else {
			this.projects.add(new Project(ProjectName, StartWeek));
		}
	}

	public void newProject(String ProjectName, int StartWeek, Employee projectLeader) throws NameAlreadyExistException {
		if (doesProjectIDExist(ProjectName) == true) {
			throw new NameAlreadyExistException("This name already exist.");
		} else {
			Project P = new Project(ProjectName, StartWeek);
			this.employees.remove(projectLeader);
			ProjectLeader PL = new ProjectLeader(projectLeader.getID(), ProjectName, P);
			P.setProjectLeader(PL);
			projectLeaders.add(PL);
			projects.add(P);
		}
	}

	public ArrayList<Project> projectsWithoutAProjectLeader() {
		ArrayList<Project> tempArray = new ArrayList<>();

		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).getProjectLeader() == null) {
				tempArray.add(projects.get(i));
			}
		}
		return tempArray;
	}

	public Employee getEmployeeByID(String ID) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getID().equals(ID)) {
				return employees.get(i);
			}
		}
		return null;
	}

	public ProjectLeader getProjectLeaderByID(String ID) {
		for (int i = 0; i < projectLeaders.size(); i++) {
			if (projectLeaders.get(i).getID().equals(ID)) {
				return projectLeaders.get(i);
			}
		}
		return null;
	}

	public boolean doesProjectIDExist(String ID) {
		for (int i = 0; i < this.projects.size(); i++) {
			if (this.projects.get(i).getProjectName().equals(ID)) {
				return true;
			}
		}
		return false;
	}

	public int getCurrentWeek() {
		return CurrentWeek;
	}

	// Hardcode employees
	public void setUpEmployees() throws NameAlreadyExistException {
		Employee e1 = new Employee("Emil", this);
		employees.add(e1);
		Employee e2 = new Employee("William", this);
		employees.add(e2);
		Employee e3 = new Employee("Tobias",this);
		employees.add(e3);
		for (int i = 0; i < 10; i++) {
			Employee e = new Employee("E" + i, this);
			employees.add(e);
		}
	}

	public void nextWeek() {
		CurrentWeek = CurrentWeek + 1;
		for (int i = 0; i < this.employees.size(); i++) {
			this.employees.get(i).updateWeek();
		}
	}

	// Hardcoding of login for each employee
	public boolean checkLogin(String ID, String pass) {
		if (ID.equals("Emil") && pass.equals("123")) {
			return true;
		} else if (ID.equals("William") && pass.equals("321")) {
			return true;
		} else if(ID.equals("E0") && pass.equals("0")){
			return true;
		} else if(ID.equals("E1") && pass.equals("1")){
			return true;
		}
		else {
			return (ID.equals("Tobias") && pass.equals("TheBest"));
		}

	}
}
