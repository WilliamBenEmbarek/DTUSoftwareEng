import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017.
 */
public class Project {
	private int ProjectNumber;
	private String ProjectName;
	private ArrayList<Activity> activities = new ArrayList<Activity>(0);
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

}
