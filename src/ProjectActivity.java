import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017.
 */
public class ProjectActivity extends Activity{

	ArrayList<Employee> assignedEmployees = new ArrayList<Employee>();

    public ProjectActivity(String activityName, int startWeek, int endWeek){
        super(activityName,startWeek,endWeek);
    }
	public void assignEmployee(Employee e) {
    	assignedEmployees.add(e);
	}

	public ArrayList<Employee> getAssignedEmployees() {
		return assignedEmployees;
	}
}
