package TimeManagement.Domain;

import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017.
 */
public class Employee {
	private String ID;
	private Project CurrentProject;
	private ArrayList<ArrayList<ArrayList<Double>>> week = new ArrayList<ArrayList<ArrayList<Double>>>(); //Outer Arraylist. Keeps track of current week.
	private ArrayList<ArrayList<Double>> projectWeek = new ArrayList<ArrayList<Double>>(1); //Middle ArrayList. Keeps track of Activties
	private ArrayList<Activity> assignedActivites = new ArrayList<Activity>();
	private ArrayList<Activity> futureAssignedActivties = new ArrayList<Activity>();
	private int currentWeek;
	private SystemTimeManager stm;
	boolean unableToWork = false;
	public Employee(String ID,SystemTimeManager stm) {
		this.ID = ID;
		currentWeek = stm.getCurrentWeek();
		projectWeek.add(new ArrayList<Double>());
		for (int i = 0; i <6; i++) {
			projectWeek.get(0).add(0.0);
		}
		week.add(currentWeek, projectWeek);
		this.stm = stm;
	}

	public void registerHours(double activityID, int date, double hours) {
		if (hours > 24) {
			hours = 24;
		}
		for (ArrayList<Double> aProjectWeek : week.get(currentWeek)) {
			if (aProjectWeek.get(0) == activityID) { //Check if the first element in the arraylist is equal to the actvity we want to register hours on.
				double x = aProjectWeek.get(date);
				x = x+hours;
				if (x > 24) {
					x = 24;
				}
				if (getHoursWorkedDay(currentWeek,date) + x > 24) {
					x = 24-getHoursWorkedDay(currentWeek,date);
				}
				aProjectWeek.set(date,x);
				System.out.println(date);
				System.out.println(hours);
			}
		}
	}
	public void editHours(double activityID, int date, double hours) {
		if (hours > 24) {
			hours = 24;
		}
		for (ArrayList<Double> aProjectWeek : week.get(currentWeek)) {
			if (aProjectWeek.get(0) == activityID) { //Check if the first element in the arraylist is equal to the actvity we want to register hours on.
				aProjectWeek.set(date, hours);
				System.out.println(date);
				System.out.println(hours);
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

	public double getHoursWorkedDayActivity(int gWeek, int gDate, Activity a) {
		int x = assignedActivites.lastIndexOf(a);
		return week.get(gWeek).get(x).get(gDate);
	}

	public void assignProject(Project project) {
		CurrentProject = project;
		week.add(currentWeek, projectWeek); //Fill up arraylist with activties
		for (int i = 0; i < CurrentProject.getNumberOfActivties(); i++) {
			week.get(currentWeek).add(new ArrayList<Double>(1)); //Inner ArrayList. Keeps track of hours on what days.
		}
		refreshActivties();
	}
	public void assignActivity(Activity a) {
	    if(a.getStartWeek()>currentWeek){
			System.out.println(a.getStartWeek());
			System.out.println(a.getEndWeek());
			System.out.println(currentWeek);
			futureAssignedActivties.add(a);
        }
        else{
            assignedActivites.add(a);
        }

	}

	public void refreshActivties() { //Needs to be called everytime an activity is created / changed with the employee.
		int x = week.get(currentWeek).size();
		if (x != assignedActivites.size()) {
			for (int i = x; i < assignedActivites.size(); i++) {
				week.get(currentWeek).add(x,new ArrayList<Double>());
				for (int y = 0; y <6; y++) {
					week.get(currentWeek).get(x).add(y,0.0);
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

	public Project getCurrentProject() {
		return CurrentProject;
	}

	public void setCurrentProject(Project P){this.CurrentProject=P;}

	public ArrayList<Activity> getAssignedActivites() {
		return assignedActivites;
	}

    public ArrayList<Activity> getFutureAssignedActivties() {
        return futureAssignedActivties;
    }

    public void assignPersonalActivity(Activity a) {
		unableToWork = true;
		assignedActivites.add(a);
	}

	public String[][] getActivityHours() {
		String[][] x = new String[5][assignedActivites.size()+1];
		x[0][0] = "Monday";
		x[1][0] = "Tuesday";
		x[2][0] = "Wednesday";
		x[3][0] = "Thursday";
		x[4][0] = "Friday";
		for (int i = 0; i < assignedActivites.size(); i++) {
			for (int j = 1; j < 6;j++)
			{
				if (week.get(currentWeek).get(i).get(j) != null)
				{
					x[j-1][i+1] = week.get(currentWeek).get(i).get(j).toString();
				}
			}
		}
		return x;
	}

	public String[] getActivitiesAssigned(){
	    String[] tempArray = new String[this.assignedActivites.size()+1];
	    tempArray[0]="";
	    for(int i=0; i<this.assignedActivites.size();i++){
	        tempArray[i+1]=this.assignedActivites.get(i).getName();
        }
        return tempArray;
    }

	public void updateWeek() {
		currentWeek = stm.getCurrentWeek();
		projectWeek.clear();
		week.add(currentWeek, projectWeek);
		for (int i = 0; i < assignedActivites.size(); i++) {
			week.get(currentWeek).add(new ArrayList<Double>(1)); //Inner ArrayList. Keeps track of hours on what days.
			for (int j = 0; j < 6; j++) {
				week.get(currentWeek).get(i).add(j,0.0);
			}
		}
		if (assignedActivites.size() != 0) {
			for (int i = 0; i < assignedActivites.size(); i++) {
				if (assignedActivites.get(i).getEndWeek() > currentWeek) {
					assignedActivites.remove(assignedActivites.get(i));
					if (assignedActivites.get(i) instanceof PersonalActivity) {
						unableToWork = false;
					}
				}
			}
			System.out.println(assignedActivites.size());
			refreshActivties();
		}
		if (futureAssignedActivties.size() != 0) {
			for (int i = 0; i < futureAssignedActivties.size(); i++) {
				if (futureAssignedActivties.get(i).getStartWeek() <= currentWeek) {
					assignedActivites.add(futureAssignedActivties.get(i));
				}
			}
			System.out.println(futureAssignedActivties.size());
			refreshActivties();
		}
	}
}