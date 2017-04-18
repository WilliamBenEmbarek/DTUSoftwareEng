/**
 * Created by Emil on 27/03/2017.
 */
public class ProjectLeader {
    private String ID;
    private String currentProject;
    public Project assignedProject;

    public ProjectLeader(String ID, String project, Project p){
        this.ID = ID;
        this.currentProject = project;
        this.assignedProject = p;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void addActivity(String activity, int ID, int startWeek){
        ProjectActivity A = new ProjectActivity(activity,ID,startWeek);
        assignedProject.addActivity(A);
    }

    public void addActivity(String activity, int ID, int startWeek, int endWeek){
        ProjectActivity A = new ProjectActivity(activity,ID,startWeek,endWeek);
        assignedProject.addActivity(A);
    }

    public void assignEmployee(Employee e, ProjectActivity a) {
    	a.assignEmployee(e);
    	e.assignActivity(a);
	}
	public void assignProject(Employee e, Project p) {
        e.assignProject(p);
    }

    public String getCurrentProject() {
        return currentProject;
    }
    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setCurrentProject(String currentProject) {
        this.currentProject = currentProject;
    }

    public String toString(){
        return this.ID;
    }

}
