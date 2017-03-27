import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Emil on 27/03/2017.
 */
public class EmployeeTest {
    /*
        Add an employees to the "firm", and check if they can add a project
    */
    @Test
    public void addEmployee(){
        SystemTimeManager STM = new SystemTimeManager();
        assertEquals(STM.employees.size(),0);

        Employee employee = new Employee("SteveJobs");
        STM.employees.add(employee);
        assertEquals(STM.employees.size(),1);

    }

    @Test
    public void addProject(){
        assertEquals(STM.projects.size(),0);
        employee.AddProject("Design New Sodamachine", 1);
        assertEquals(STM.projects.size(),1);

        Project project = STM.projects.get(0);
        assertEquals(project.getProjectName(),"Design New SodaMachine");
        assertEquals(project.getStartWeek, 1);
    }
}
