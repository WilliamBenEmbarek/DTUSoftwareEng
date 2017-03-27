import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017.
 */
public class Project {
	int ProjectNumber;
	String ProjectName;
	ArrayList<Activity> activities = new ArrayList<Activity>(0);
	int StartWeek;

	public Project(String projectName, int startWeek) {
		ProjectName = projectName;
		StartWeek = startWeek;
	}
}
