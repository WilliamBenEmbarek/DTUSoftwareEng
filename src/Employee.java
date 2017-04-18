import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017.
 */
public class Employee {
	private String ID;
	private Project CurrentProject;
	private String CurrentProjectName;
	private ArrayList<ArrayList<ArrayList<Integer>>> week = new ArrayList<ArrayList<ArrayList<Integer>>>(); //Outer Arraylist. Keeps track of current week.
	private ArrayList<ArrayList<Integer>> projectWeek = new ArrayList<ArrayList<Integer>>(1); //Middle ArrayList. Keeps track of Activties
	private ArrayList<Activity> assignedActivites = new ArrayList<Activity>();
	private int currentWeek;
	private int currentDay;

	public Employee(String ID) {
		this.ID = ID;
		currentWeek = SystemTimeManager.getCurrentWeek();
		currentDay = SystemTimeManager.getCurrentDay();
		projectWeek.add(new ArrayList<Integer>());
		for (int i = 0; i <6; i++) {
			projectWeek.get(0).add(-1);
		}
		week.add(currentWeek, projectWeek);
	}

	public Employee(String ID, Project currentProject) {
		this.ID = ID;
		CurrentProject = currentProject;
		CurrentProjectName = currentProject.getProjectName();
		currentWeek = SystemTimeManager.getCurrentWeek();
		projectWeek.add(new ArrayList<Integer>());
		projectWeek.get(0).add(1);
		week.add(currentWeek, week.get(currentWeek)); //Fill up arraylist with activties
		for (int i = 0; i < currentProject.getNumberOfActivties(); i++) {
			week.get(currentWeek).add(new ArrayList<Integer>(1)); //Inner ArrayList. Keeps track of hours on what days.
		}
	}

	public void registerHours(int activityID, int hours) {
		int totalActivities = week.get(currentWeek).size();
		for (ArrayList<Integer> aProjectWeek : week.get(currentWeek)) {
			if (aProjectWeek.get(0) == activityID) { //Check if the first element in the arraylist is equal to the actvity we want to register hours on.
				aProjectWeek.set(currentDay, aProjectWeek.get(currentDay) + hours);
			}
		}
	}

	public void editHours(int activityID, int date, int hours) {
		int totalActivities = week.get(currentWeek).size();
		for (ArrayList<Integer> aProjectWeek : week.get(currentWeek)) {
			if (aProjectWeek.get(0) == activityID) { //Check if the first element in the arraylist is equal to the actvity we want to register hours on.
				aProjectWeek.set(date, hours);
			}
		}
	}
	public int getHoursWorkedDay(int gWeek, int gDate) {
		int hours = 0;
		for (ArrayList<Integer> aProjectWeek : week.get(gWeek)) {
				hours = hours + aProjectWeek.get(gDate);
			}
		return hours;
	}
	public ArrayList<ArrayList<Integer>> getTimeManagementArray(int gWeek) {
		return week.get(gWeek);
	}

	public void assignProject(Project project) {
		CurrentProject = project;
		week.add(currentWeek, week.get(currentWeek)); //Fill up arraylist with activties
		for (int i = 0; i < CurrentProject.getNumberOfActivties(); i++) {
			week.get(currentWeek).add(new ArrayList<Integer>(1)); //Inner ArrayList. Keeps track of hours on what days.
		}
		refreshActivties();
	}
	public void assignActivity(Activity a) {
		assignedActivites.add(a);
	}

	public void refreshActivties() { //Needs to be called everytime an activity is created / changed with the employee.
		int x = week.get(currentWeek).size();
		if (x != assignedActivites.size()) {
			for (int i = 0; i < assignedActivites.size()-x; i++) {
				week.get(currentWeek).add(new ArrayList<Integer>());
				week.get(currentWeek).get(week.get(currentWeek).size()-1).add(-1);
			}
		}
		for (int i = 0; i < assignedActivites.size(); i++) { //Loop through list of activities.
			if ((week.get(currentWeek).get(i).get(0) != (assignedActivites.get(i).getID()))) { // If the activity does not exist
				week.get(currentWeek).get(i).set(0,assignedActivites.get(i).getID());
			}
		}
	}

	public void AddProject (String ProjectName, int StartWeek) throws NameAlreadyExistException {
		SystemTimeManager.newProject(ProjectName,StartWeek);
	}

	public void AddProject (String ProjectName, int StartWeek, Employee projectLeader){
		SystemTimeManager.newProject(ProjectName,StartWeek,projectLeader);
	}

	public String getID() {
		return ID;
	}

	// To display the ID names in JComboBox java Swing
	public String toString(){
    	return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public Project getCurrentProject() {
		return CurrentProject;
	}

	public ArrayList<Activity> getAssignedActivites() {
		return assignedActivites;
	}
}