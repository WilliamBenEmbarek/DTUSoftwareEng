/**
 * Created by Emil on 28/03/2017.
 */

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Vector;

public class GUI extends JFrame implements ActionListener{

    private SystemTimeManager STM;
    private Employee currentLoggedOn;
    private ProjectLeader loggedOnProjectLeader;

    private String ID;
    private String pass;

    // Textfields to be added
    private JTextField userName;
    private JTextField password;
    private JTextField newProjectName;
    private JTextField activityStartWeek;
    private JTextField activityEndWeek;
    private JTextField newActivityName;

    // Number fields
    private JFormattedTextField projectStartWeek;
    private JFormattedTextField activityStartWeek;
    private JFormattedTextField activityEndWeek;

    // Drop-down menus
    private JComboBox boxOfEmployees;
    private JComboBox boxOfProjects;

    // Functional buttons
    private JButton logout;
    private JButton backEmployee;
    private JButton backProjectLeader;
    private JButton login;
    private JButton createProject;
    private JButton addProject;
    private JButton assignProjectLeader;
    private JButton addProjectLeader;
    private JButton createActivity;
    private JButton editActivity;
    private JButton assignEmployeeToActivity;

    // Allows only numbers in some fields
    private NumberFormat longFormat = NumberFormat.getIntegerInstance();
    private NumberFormatter numberFormatter = new NumberFormatter(longFormat);

    public static void main(String[] args) {
        GUI mainFrame = new GUI();

        windowProperties(mainFrame);
    }

    private GUI(){
        STM = new SystemTimeManager();
        setUpEmployees();
        loginPage();
    }

    private void loginPage(){
        JPanel loginPanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelUsername = new JLabel("Username: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        loginPanel.add(labelUsername, cs);

        userName     = new JTextField(20);
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 2;
        loginPanel.add(userName, cs);

        JLabel labelPassword = new JLabel("Password: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        loginPanel.add(labelPassword, cs);

        password     = new JPasswordField(20);
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 2;
        loginPanel.add(password, cs);


        login        = new JButton("Login");
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 2;
        login.addActionListener(this);
        loginPanel.add(login, cs);

        getContentPane().add(loginPanel);
    }

    private void employeePage(){
        JPanel employeePanel = new JPanel(new GridBagLayout());

        createProject = new JButton("Create Project");
        createProject.addActionListener(this);
        employeePanel.add(createProject);

        assignProjectLeader = new JButton("Assign Project Leader");
        assignProjectLeader.addActionListener(this);
        employeePanel.add(assignProjectLeader);

        logout = new JButton("Log Out");
        logout.addActionListener(this);
        employeePanel.add(logout);

        getContentPane().add(employeePanel);
    }

    private void createProjectPage(){
        JPanel createProjectPanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelProjectName = new JLabel("Project Name: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        createProjectPanel.add(labelProjectName, cs);

        newProjectName    = new JTextField(20);
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 3;
        createProjectPanel.add(newProjectName, cs);

        JLabel labelProjectStartWeek = new JLabel("Project Start Week: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        createProjectPanel.add(labelProjectStartWeek, cs);

        numberFormatter.setAllowsInvalid(false);
        projectStartWeek = new JFormattedTextField(numberFormatter);
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        createProjectPanel.add(projectStartWeek, cs);

        JLabel labelProjectLeader = new JLabel("Project Leader (optional): ");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        createProjectPanel.add(labelProjectLeader, cs);

        boxOfEmployees = new JComboBox(new Vector(STM.Employees));
        // Remove the index because this function is optional
        boxOfEmployees.setSelectedIndex(-1);
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 3;
        createProjectPanel.add(boxOfEmployees, cs);

        addProject = new JButton("Add Project");
        cs.gridx     = 1;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        addProject.addActionListener(this);
        createProjectPanel.add(addProject, cs);

        backEmployee = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        backEmployee.addActionListener(this);
        createProjectPanel.add(backEmployee, cs);


        getContentPane().add(createProjectPanel);
    }

    private void assignProjectLeaderPage(){
        JPanel assignProjectLeaderPanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelUsername = new JLabel("Project Leader: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        assignProjectLeaderPanel.add(labelUsername, cs);

        boxOfEmployees = new JComboBox(new Vector(STM.Employees));
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 2;
        assignProjectLeaderPanel.add(boxOfEmployees, cs);

        JLabel labelPassword = new JLabel("Project: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        assignProjectLeaderPanel.add(labelPassword, cs);

        boxOfProjects     = new JComboBox(new Vector(STM.projectsWithoutAProjectLeader()));
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 2;
        assignProjectLeaderPanel.add(boxOfProjects, cs);


        addProjectLeader = new JButton("Assign this leader to the project");
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 2;
        addProjectLeader.addActionListener(this);
        assignProjectLeaderPanel.add(addProjectLeader, cs);

        backEmployee = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        backEmployee.addActionListener(this);
        assignProjectLeaderPanel.add(backEmployee,cs);

        getContentPane().add(assignProjectLeaderPanel);

    }

    private void projectLeaderPage(){
        JPanel projectLeaderPanel = new JPanel(new GridBagLayout());

        JLabel labelProject = new JLabel();

        createActivity = new JButton("Create Activity");
        createActivity.addActionListener(this);
        projectLeaderPanel.add(createActivity);

        editActivity = new JButton("Edit Activity");
        editActivity.addActionListener(this);
        projectLeaderPanel.add(editActivity);

        assignEmployeeToActivity = new JButton("Assign Employee");
        assignEmployeeToActivity.addActionListener(this);
        projectLeaderPanel.add(assignEmployeeToActivity);

        logout = new JButton("Log Out");
        logout.addActionListener(this);
        projectLeaderPanel.add(logout);

        getContentPane().add(projectLeaderPanel);
    }

    private void createActivityPage(){
        JPanel createActivityPanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelActivityName = new JLabel("Activty Name: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        createActivityPanel.add(labelActivityName, cs);

        newActivityName    = new JTextField(20);
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 3;
        createActivityPanel.add(newActivityName, cs);

        JLabel labelActivityStartWeek = new JLabel("Activity Start Week: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        createActivityPanel.add(labelActivityStartWeek, cs);

        activityStartWeek = new JFormattedTextField(numberFormatter);
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        createActivityPanel.add(activityStartWeek, cs);

        JLabel labelActivityEndWeek = new JLabel("Activity End Week: ");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        createActivityPanel.add(labelActivityEndWeek, cs);

        activityEndWeek = new JFormattedTextField(numberFormatter);
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 3;
        createActivityPanel.add(activityEndWeek, cs);

        addProject = new JButton("Add Activity");
        cs.gridx     = 1;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        addProject.addActionListener(this);
        createActivityPanel.add(addProject, cs);

        backProjectLeader = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        backProjectLeader.addActionListener(this);
        createActivityPanel.add(backProjectLeader, cs);


        getContentPane().add(createActivityPanel);
    }

    public void actionPerformed(ActionEvent e) {
        // To keep track of projects and employees in the console
        System.out.println(Arrays.toString(STM.getEmployees().toArray()));
        System.out.println(Arrays.toString(STM.getProjects().toArray()));
        System.out.println(Arrays.toString(STM.getProjectLeaders().toArray()));

        actionCommandsEmployeePage(e);
        actionCommandsProjectLeaderPage(e);
        if (e.getSource() == login) {
            if(checkLogin()) {
                currentLoggedOn = STM.getEmployeeByID(userName.getText().trim());
                // If the currentLoggedOn is null, then it is a project leader
                if(currentLoggedOn == null ){
                    loggedOnProjectLeader = STM.getProjectLeaderByID(userName.getText().trim());
                    getContentPane().removeAll();
                    projectLeaderPage();
                    revalidate();
                    repaint();
                }
                else{
                    getContentPane().removeAll();
                    employeePage();
                    revalidate();
                    repaint();
                }
            }
        }
        if (e.getSource() == logout){
            getContentPane().removeAll();
            loginPage();
            revalidate();
            repaint();
        }
    }

    private void actionCommandsEmployeePage(ActionEvent e){
        if (e.getSource() == createProject){
            getContentPane().removeAll();
            createProjectPage();
            revalidate();
            repaint();
        }
        if (e.getSource() == addProject){
            String name = newProjectName.getText().trim();
            String startWeek = projectStartWeek.getText().trim();
            if(boxOfEmployees.getSelectedIndex()==-1){
                currentLoggedOn.AddProject(name,Integer.parseInt(startWeek));
                addProject.setText("Project has been added");
                revalidate();
                repaint();
            }
            else{
                currentLoggedOn.AddProject(name,Integer.parseInt(startWeek),(Employee)boxOfEmployees.getSelectedItem());
                addProject.setText("Project has been added");
                revalidate();
                repaint();
            }
        }
        if(e.getSource() == assignProjectLeader){
            getContentPane().removeAll();
            assignProjectLeaderPage();
            revalidate();
            repaint();
        }
        if(e.getSource() == addProjectLeader){
            STM.AssignProjectLeader((Employee)boxOfEmployees.getSelectedItem(),(Project)boxOfProjects.getSelectedItem());

        }
        if (e.getSource() == backEmployee){
            getContentPane().removeAll();
            employeePage();
            revalidate();
            repaint();
        }
    }

    private void actionCommandsProjectLeaderPage(ActionEvent e){
        if(e.getSource()==createActivity){
            getContentPane().removeAll();
            createActivityPage();
            revalidate();
            repaint();
        }
        if (e.getSource() == backProjectLeader){
            getContentPane().removeAll();
            projectLeaderPage();
            revalidate();
            repaint();
        }
    }

    private static void windowProperties(JFrame mainFrame){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 400);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setVisible(true);
    }

    // Hardcode employees
    private void setUpEmployees(){
        Employee e1 = new Employee("Emil");
        STM.Employees.add(e1);
        Employee e2 = new Employee("William");
        STM.Employees.add(e2);
        Employee e3 = new Employee("Test person");
        STM.Employees.add(e3);
    }

    // Hardcoding of login of each employee
    private boolean checkLogin(){
        ID = userName.getText().trim();
        pass = password.getText().trim();

        if(ID.equals("Emil") && pass.equals("123")){
            return true;
        }
        if(ID.equals("William") && pass.equals("321")){
            return true;
        }
        return false;
    }
}
