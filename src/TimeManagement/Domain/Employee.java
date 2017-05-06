package TimeManagement.Domain;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;

/**
 * Created by Emil on 27/03/2017.
 */
public class Employee {
	private String ID;
	private Project CurrentProject;
	private String CurrentProjectName;
	private ArrayList<ArrayList<ArrayList<Double>>> week = new ArrayList<ArrayList<ArrayList<Double>>>(); //Outer Arraylist. Keeps track of current week.
	private ArrayList<ArrayList<Double>> projectWeek = new ArrayList<ArrayList<Double>>(1); //Middle ArrayList. Keeps track of Activties
	private ArrayList<Activity> assignedActivites = new ArrayList<Activity>();
	private int currentWeek;
	private int currentDay;
	private SystemTimeManager stm;

	public Employee(String ID,SystemTimeManager stm) {
		this.ID = ID;
		currentWeek = stm.getCurrentWeek();
		currentDay = stm.getCurrentDay();
		projectWeek.add(new ArrayList<Double>());
		for (int i = 0; i <6; i++) {
			projectWeek.get(0).add(0.0);
		}
		week.add(currentWeek, projectWeek);
		this.stm = stm;
	}

	public void registerHours(int activityID, double hours) {
		int totalActivities = week.get(currentWeek).size();
		for (ArrayList<Double> aProjectWeek : week.get(currentWeek)) {
			if (aProjectWeek.get(0) == activityID) { //Check if the first element in the arraylist is equal to the actvity we want to register hours on.
				aProjectWeek.set(currentDay, aProjectWeek.get(currentDay) + hours);
			}
		}
	}

	public void editHours(double activityID, int date, double hours) {
		int totalActivities = week.get(currentWeek).size();
		for (ArrayList<Double> aProjectWeek : week.get(currentWeek)) {
			if (aProjectWeek.get(0) == activityID) { //Check if the first element in the arraylist is equal to the actvity we want to register hours on.
				aProjectWeek.set(date, hours);
			}
		}
	}
	public double getHoursWorkedDay(int gWeek, int gDate) {
		double hours = 0;
		for (ArrayList<Double> aProjectWeek : week.get(gWeek)) {
				hours = hours + aProjectWeek.get(gDate);
			}
		return hours;
	}

	public void assignProject(Project project) {
		CurrentProject = project;
		week.add(currentWeek, week.get(currentWeek)); //Fill up arraylist with activties
		for (int i = 0; i < CurrentProject.getNumberOfActivties(); i++) {
			week.get(currentWeek).add(new ArrayList<Double>(1)); //Inner ArrayList. Keeps track of hours on what days.
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
				week.get(currentWeek).add(new ArrayList<Double>());
				for (int y = 0; y <6; y++) {
					week.get(currentWeek).get(week.get(currentWeek).size()-1).add(0.0);
				}
			}
		}
		for (int i = 0; i < assignedActivites.size(); i++) { //Loop through list of activities.
			if ((week.get(currentWeek).get(i).get(0) != (assignedActivites.get(i).getID()))) { // If the activity does not exist
				week.get(currentWeek).get(i).set(0,assignedActivites.get(i).getID());
			}
		}
	}

	public void AddProject (String ProjectName, int StartWeek) throws NameAlreadyExistException {
		stm.newProject(ProjectName,StartWeek);
	}

	public void AddProject (String ProjectName, int StartWeek, Employee projectLeader) throws NameAlreadyExistException {
		stm.newProject(ProjectName,StartWeek,projectLeader);
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