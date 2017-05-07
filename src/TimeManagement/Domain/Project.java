package TimeManagement.Domain;

import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017.
 */
public class Project {
	private int ProjectNumber;
	private String ProjectName;
	public ArrayList<ProjectActivity> activities = new ArrayList<ProjectActivity>(0);
	private int StartWeek;
	private ProjectLeader projectLeader;

	public Project(String projectName, int startWeek) {
		this.ProjectName	 = projectName;
		this.StartWeek	 = startWeek;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectLeader(ProjectLeader PL){
		this.projectLeader = PL;
	}

	public ProjectLeader getProjectLeader() {
		return projectLeader;
	}

	// To display the ID names in JComboBox java Swing
	public String toString(){
		return this.ProjectName;
	}

	public int getStartWeek() {
		return StartWeek;
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


	public void addActivity(ProjectActivity activity) {
		activities.add(activity);
	}
}
