import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017.
 */
public class Project {
	int ProjectNumber;
	String ProjectName;
	Employee projectLeader;
	ArrayList<Activity> activities = new ArrayList<Activity>(0);
	int StartWeek;

	public Project(String projectName, int startWeek) {
		this.ProjectName	 = projectName;
		this.StartWeek	 = startWeek;
	}

	public Project(String projectName, int startWeek, Employee projectLeader) {
		this.ProjectName 	= projectName;
		this.StartWeek       = startWeek;
		this.projectLeader = projectLeader;
	}

	public String getProjectName() {
		return ProjectName;
	}

	// To display the ID names in JComboBox java Swing
	public String toString(){
		return this.ProjectName;
	}

	public Employee getProjectLeader() {
		return projectLeader;
	}
}
