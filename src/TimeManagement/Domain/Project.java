package TimeManagement.Domain;

import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017.
 */
public class Project {
	private int ProjectNumber;
	private String projectName;
	public ArrayList<ProjectActivity> activities = new ArrayList<ProjectActivity>(0);
	private int startWeek;
	private ProjectLeader projectLeader;
	private ArrayList<PersonalActivity> personalActivities = new ArrayList<PersonalActivity>(0);
	public Project(String projectName, int startWeek) {
		this.projectName = projectName;
		this.startWeek = startWeek;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectLeader(ProjectLeader PL){
		this.projectLeader = PL;
	}

	public ProjectLeader getProjectLeader() {
		return projectLeader;
	}

	// To display the ID names in JComboBox java Swing
	public String toString(){
		return this.projectName;
	}

	public int getStartWeek() {
		return startWeek;
	}

	public int getNumberOfActivties(){
		return activities.size();
	}

	public ArrayList<ProjectActivity> getActivities(int week) {
		for(int i=0; i<this.activities.size(); i++){
			if(this.activities.get(i).getEndWeek()<week){
				this.activities.remove(this.activities.get(i));
			}
		}
		return activities;
	}

	public void addActivity(ProjectActivity activity) throws InvalidInputException, NameAlreadyExistException {
		if(activity.getStartWeek()<this.startWeek){
			throw new InvalidInputException("The input is invalid.");
		}
		else if(doesActivityNameExist(activity.getName())){
			throw new NameAlreadyExistException("This name already exist.");
		}
		else {
			activities.add(activity);
		}
	}

	public void addPersonalActivity(PersonalActivity activity) throws InvalidInputException, NameAlreadyExistException {
		if(activity.getStartWeek()<this.startWeek){
			throw new InvalidInputException("The input is invalid.");
		}
		else if(doesActivityNameExist(activity.getName())){
			throw new NameAlreadyExistException("This name already exist.");
		}
		else {
			personalActivities.add(activity);
		}
	}
	public boolean doesActivityNameExist(String name){
		for(int i=0; i<activities.size();i++){
			if(activities.get(i).getName().equals(name)){
				return true;
			}
		}
		return false;
	}
}
