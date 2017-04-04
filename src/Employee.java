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
	private int currentWeek;
	private int currentDay;

	public Employee(String ID) {
		this.ID = ID;
		currentWeek = SystemTimeManager.getCurrentWeek();
		week.add(currentWeek, projectWeek);
	}

	public Employee(String ID, Project currentProject) {
		this.ID = ID;
		CurrentProject = currentProject;
		CurrentProjectName = currentProject.getProjectName();
		currentWeek = SystemTimeManager.getCurrentWeek();
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
	}
	public void refreshActivties() {

	}

	public void AddProject (String ProjectName, int StartWeek){
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
}