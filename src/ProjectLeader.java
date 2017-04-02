/**
 * Created by Emil on 27/03/2017.
 */
public class ProjectLeader {
    private String ID;
    private String currentProject;
    private Project assignedProject;

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

    public void addActivity(String activity, int startWeek, int endWeek){

    }

    public String getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(String currentProject) {
        this.currentProject = currentProject;
    }

    public String toString(){
        return this.ID;
    }
}
