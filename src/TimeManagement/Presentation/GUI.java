package TimeManagement.Presentation; /**
 * Created by Emil on 28/03/2017.
 */

import TimeManagement.Domain.*;
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

    // Textfields to be added
    private JTextField userName;
    private JTextField password;
    private JTextField newProjectName;
    private JTextField newActivityName;

    // Number fields
    private JFormattedTextField projectStartWeek;
    private JFormattedTextField activityStartWeek;
    private JFormattedTextField activityEndWeek;

    // Drop-down menus
    private JComboBox boxOfEmployees;
    private JComboBox boxOfProjects;
    private JComboBox boxOfAvaliableEmployees;
    private JComboBox boxOfActivities;

    // Functional buttons
    private JButton logout;
    private JButton backEmployee;
    private JButton backProjectLeader;
    private JButton login;
    private JButton createProject;
    private JButton addProject;
    private JButton assignProjectLeader;
    private JButton addProjectLeader;
    private JButton createActivityPage;
    private JButton editActivityPage;
    private JButton assignEmployeeToActivityPage;
    private JButton assignEmployeeToActivity;
    private JButton addActivity;

    // Allows only numbers in some fields
    private NumberFormat longFormat = NumberFormat.getIntegerInstance();
    private NumberFormatter numberFormatter = new NumberFormatter(longFormat);

    public static void main(String[] args) {
        GUI mainFrame = new GUI();

        mainFrame.windowProperties(mainFrame);
    }

    private GUI(){
        STM = new SystemTimeManager();
        STM.setUpEmployees();
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

        boxOfEmployees = new JComboBox(new Vector(STM.getEmployees()));
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

        boxOfEmployees = new JComboBox(new Vector(STM.getEmployees()));
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

        JLabel labelProject = new JLabel("Project: "+loggedOnProjectLeader.getCurrentProject());
        projectLeaderPanel.add(labelProject);

        createActivityPage = new JButton("Create Activity");
        createActivityPage.addActionListener(this);
        projectLeaderPanel.add(createActivityPage);

        editActivityPage = new JButton("Edit Activity");
        editActivityPage.addActionListener(this);
        projectLeaderPanel.add(editActivityPage);

        assignEmployeeToActivityPage = new JButton("Assign Employee");
        assignEmployeeToActivityPage.addActionListener(this);
        projectLeaderPanel.add(assignEmployeeToActivityPage);

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

        addActivity = new JButton("Add Activity");
        cs.gridx     = 1;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        addActivity.addActionListener(this);
        createActivityPanel.add(addActivity, cs);

        backProjectLeader = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        backProjectLeader.addActionListener(this);
        createActivityPanel.add(backProjectLeader, cs);


        getContentPane().add(createActivityPanel);
    }

    private void assignEmployeePage(){
        JPanel assignEmployeePanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelActivities = new JLabel("Activity to be assigned: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        assignEmployeePanel.add(labelActivities, cs);

        boxOfActivities = new JComboBox(new Vector(loggedOnProjectLeader.assignedProject.activities));
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 3;
        assignEmployeePanel.add(boxOfActivities, cs);

        JLabel labelAvaliableEmployees = new JLabel("Employee to be assigned: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        assignEmployeePanel.add(labelAvaliableEmployees, cs);

        boxOfAvaliableEmployees = new JComboBox(new Vector(STM.AvailableEmployeesForAGivenWeek(1)));
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        assignEmployeePanel.add(boxOfActivities, cs);

        assignEmployeeToActivity = new JButton("Assign to activity");
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 3;
        assignEmployeeToActivity.addActionListener(this);
        assignEmployeePanel.add(assignEmployeeToActivity, cs);

        backProjectLeader = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 3;
        backProjectLeader.addActionListener(this);
        assignEmployeePanel.add(backProjectLeader, cs);



        getContentPane().add(assignEmployeePanel);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(Arrays.toString(STM.getProjectLeaders().toArray()));
        System.out.println(Arrays.toString(STM.getEmployees().toArray()));
        System.out.println(Arrays.toString(STM.getProjects().toArray()));

        try {
            actionCommandsEmployeePage(e);
        } catch (NameAlreadyExistException e1) {
            e1.printStackTrace();
        }

        actionCommandsProjectLeaderPage(e);

        if (e.getSource() == login) {
            if(STM.checkLogin(userName.getText().trim(), password.getText().trim())) {
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

    private void actionCommandsEmployeePage(ActionEvent e) throws NameAlreadyExistException {
        if (e.getSource() == createProject){
            getContentPane().removeAll();
            createProjectPage();
            revalidate();
            repaint();
        }
        if (e.getSource() == addProject){
            String name = newProjectName.getText().trim();
            String startWeek = projectStartWeek.getText().trim();
            if(name.equals("") || startWeek.equals("")){
                addProject.setText("Please fill out the fields, try again");
                revalidate();
                repaint();
            }
            else if(STM.doesProjectIDExist(name)){
                addProject.setText("The name already exist, try again");
                revalidate();
                repaint();
            }
            else if(boxOfEmployees.getSelectedIndex()==-1){
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
            addProjectLeader.setText("Project Leader is assigned!");
            revalidate();
            repaint();
        }
        if (e.getSource() == backEmployee){
            getContentPane().removeAll();
            employeePage();
            revalidate();
            repaint();
        }
    }

    private void actionCommandsProjectLeaderPage(ActionEvent e){
        if(e.getSource()==createActivityPage){
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
        if(e.getSource() == addActivity){
            String name      = newActivityName.getText().trim();
            String startWeek = activityStartWeek.getText().trim();
            String endWeek   = activityEndWeek.getText().trim();
            if(name.equals("") || startWeek.equals("") || endWeek.equals("")){
                addActivity.setText("Please fill out the fields, try again");
                revalidate();
                repaint();
            }
            else if(Integer.parseInt(startWeek)>Integer.parseInt(endWeek)){
                addActivity.setText("Invalid start and end week, try again");
                revalidate();
                repaint();
            }
            else if(Integer.parseInt(startWeek)<loggedOnProjectLeader.assignedProject.getStartWeek()){
                addActivity.setText("Start week is before project start week, try again");
                revalidate();
                repaint();
            }
            else{
                loggedOnProjectLeader.addActivity(name, Integer.parseInt(startWeek), Integer.parseInt(endWeek));
                addActivity.setText("Activity is added!");
                revalidate();
                repaint();
            }
            System.out.println(Arrays.toString(loggedOnProjectLeader.assignedProject.activities.toArray()));
        }
        if(e.getSource() == assignEmployeeToActivityPage){
            getContentPane().removeAll();
            assignEmployeePage();
            revalidate();
            repaint();
        }
        if(e.getSource() == assignEmployeeToActivity){
            loggedOnProjectLeader.assignEmployee((Employee)boxOfAvaliableEmployees.getSelectedItem(),(ProjectActivity)boxOfActivities.getSelectedItem());

        }
    }

    private void windowProperties(JFrame mainFrame){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 400);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setVisible(true);
    }

}
