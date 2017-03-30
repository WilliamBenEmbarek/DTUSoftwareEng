import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017.
 */
public class Project {
	int ProjectNumber;
	String ProjectName;
	String projectLeaderID;
	ArrayList<Activity> activities = new ArrayList<Activity>(0);
	int StartWeek;

	public Project(String projectName, int startWeek) {
		ProjectName	 = projectName;
		StartWeek	 = startWeek;
	}

	public Project(String projectName, int startWeek, String projectLeader) {
		ProjectName 	= projectName;
		StartWeek       = startWeek;
		projectLeaderID = projectLeader;
	}

	public String getProjectName() {
		return ProjectName;
	}

	// To display the ID names in JComboBox java Swing
	public String toString(){
		return this.ProjectName;
	}
}
