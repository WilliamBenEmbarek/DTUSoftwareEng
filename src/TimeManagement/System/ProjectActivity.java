package TimeManagement.System;

import java.util.ArrayList;

/**
 * Created by Emil on 27/03/2017.
 */
public class ProjectActivity extends Activity{

	ArrayList<Employee> assignedEmployees = new ArrayList<Employee>();

    public ProjectActivity(String activityName,int id, int startWeek){
        super(activityName,id,startWeek);
    }
	public ProjectActivity(String activityName,int id, int startWeek, int endWeek){
		super(activityName,id,startWeek,endWeek);
	}
	public void assignEmployee(Employee e) {
    	assignedEmployees.add(e);
	}

	public ArrayList<Employee> getAssignedEmployees() {
		return assignedEmployees;
	}
}
