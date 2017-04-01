/**
 * Created by Emil on 27/03/2017.
 */
public class ProjectLeader {
    private String ID;
    private String currentProject;

    public ProjectLeader(String ID, String project){
        this.ID = ID;
        this.currentProject = project;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
