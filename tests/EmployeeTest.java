import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Emil on 27/03/2017.
 */
public class EmployeeTest {
    /*
        Test add employee with a giving ID, and then make th
     */
    @Test
    public void addEmployee(){
        SystemTimeManager STM = new SystemTimeManager();
        assertEquals(STM.employees.size(),0);
        Employee employee = new Employee("SteveJobs");


    }
}
