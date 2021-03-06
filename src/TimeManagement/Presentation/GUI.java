package TimeManagement.Presentation;
/**
 * Created by Emil on 28/03/2017.
 */

import TimeManagement.Domain.*;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Vector;

public class GUI extends JFrame implements ActionListener{

    private SystemTimeManager stm;
    private Employee currentLoggedOn;
    private ProjectLeader loggedOnProjectLeader;
    private double idCounter = 0.0;

    // Textfields to be added
    private JTextField userName;
    private JTextField password;
    private JTextField newProjectName;
    private JTextField newActivityName;
    private JTextField personalActivity;

    // Number fields
    private JFormattedTextField projectStartWeek;
    private JFormattedTextField activityStartWeek;
    private JFormattedTextField activityEndWeek;
    private JFormattedTextField editActivityStartWeek;
    private JFormattedTextField editActivityEndWeek;
    private JFormattedTextField hours;
    private JFormattedTextField personalActivityStartWeek;
    private JFormattedTextField personalActivityEndWeek;

    // Drop-down menus
    private JComboBox boxOfEmployees;
    private JComboBox boxOfProjects;
    private JComboBox boxOfAvaliableEmployees;
    private JComboBox boxOfActivities;
    private JComboBox boxOfAssignedActivities;
    private JComboBox days;
    private JComboBox boxOfAssignedActivitiesToEdit;
    private JComboBox assistActivity;

    // Week days
    private String[] weekDays = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};

    // JPanel
    private JPanel assignEmployeePanel;
    private int selectAIndex=0;
    private int daySelect=0;
    private int editSelect=0;

    // Functional buttons
    private JButton nextWeek;
    private JButton logout;
    private JButton backEmployee;
    private JButton backProjectLeader;
    private JButton backRegisterTimeMenue;
    private JButton login;
    private JButton registerHoursMenue;
    private JButton createProject;
    private JButton addProject;
    private JButton assignProjectLeader;
    private JButton addProjectLeader;
    private JButton createActivityPage;
    private JButton editActivityPage;
    private JButton editActivity;
    private JButton assignEmployeeToActivityPage;
    private JButton assignEmployeeToActivity;
    private JButton addActivity;
    private JButton registerTimePage;
    private JButton assistancePage;
    private JButton editHoursPage;
    private JButton registerTime;
    private JButton editTime;
    private JButton assignEmployeeToPersonalActivityPage;
    private JButton assignEmployeeToPersonalActivity;
    private JButton requestAssist;

    // Allows only numbers in some fields
    private NumberFormat intFormat = NumberFormat.getIntegerInstance();
    private NumberFormatter intFormatter = new NumberFormatter(intFormat);

    private NumberFormat doubleFormat = new DecimalFormat("#0.00",new DecimalFormatSymbols(Locale.ENGLISH));
    private NumberFormatter doubleFormatter = new NumberFormatter(doubleFormat);

    public static void main(String[] args) throws NameAlreadyExistException {
        GUI mainFrame = new GUI();

        mainFrame.windowProperties(mainFrame);
    }

    private GUI() throws NameAlreadyExistException {
        stm = new SystemTimeManager();
        stm.setUpEmployees();
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

        nextWeek     = new JButton("Next Week");
        cs.gridx     = 1;
        cs.gridy     = 3;
        cs.gridwidth = 2;
        nextWeek.addActionListener(this);
        loginPanel.add(nextWeek, cs);

        JLabel labelWeek = new JLabel("Week: "+ stm.getCurrentWeek());
        cs.gridx     = 0;
        cs.gridy     = 3;
        cs.gridwidth = 1;
        loginPanel.add(labelWeek, cs);

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

        registerHoursMenue = new JButton("Register Hours");
        registerHoursMenue.addActionListener(this);
        employeePanel.add(registerHoursMenue);

        logout = new JButton("Log Out");
        logout.addActionListener(this);
        employeePanel.add(logout);

        getContentPane().add(employeePanel);
    }

    private void registerHoursPage(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        JLabel week = new JLabel("Week: "+ stm.getCurrentWeek());
        panel.add(week,BorderLayout.NORTH);
        JPanel registerHoursPanel = new JPanel(new GridBagLayout());

        Object rowData[][] = currentLoggedOn.getActivityHours();
        Object columnNames[] = currentLoggedOn.getActivitiesAssigned();

        JTable table = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(100,100));

        panel.add(scrollPane,BorderLayout.CENTER);

        registerTimePage = new JButton("Register Time");
        registerTimePage.addActionListener(this);
        registerHoursPanel.add(registerTimePage);

        assistancePage = new JButton("Assistance on Activity");
        assistancePage.addActionListener(this);
        registerHoursPanel.add(assistancePage);

        editHoursPage = new JButton("Edit hours worked");
        editHoursPage.addActionListener(this);
        registerHoursPanel.add(editHoursPage);

        backEmployee = new JButton("Back");
        backEmployee.addActionListener(this);
        registerHoursPanel.add(backEmployee);

        panel.add(registerHoursPanel,BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private void registerHoursTable(){
        JPanel registerHourPanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelActivity = new JLabel("Activity: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        registerHourPanel.add(labelActivity, cs);

        boxOfAssignedActivities = new JComboBox(new Vector(currentLoggedOn.getAssignedActivites()));
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 3;
        registerHourPanel.add(boxOfAssignedActivities, cs);


        JLabel labelHours = new JLabel("Hours worked: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        registerHourPanel.add(labelHours, cs);

        doubleFormatter.setAllowsInvalid(false);
        hours = new JFormattedTextField(doubleFormatter);
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        registerHourPanel.add(hours, cs);

        JLabel labelDate = new JLabel("Day: ");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        registerHourPanel.add(labelDate, cs);

        days = new JComboBox(new Vector(new ArrayList<String>(Arrays.asList(weekDays))));
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 3;
        registerHourPanel.add(days, cs);

        registerTime = new JButton("Register Time");
        cs.gridx     = 1;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        registerTime.addActionListener(this);
        registerHourPanel.add(registerTime, cs);

        backRegisterTimeMenue = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        backRegisterTimeMenue.addActionListener(this);
        registerHourPanel.add( backRegisterTimeMenue, cs);



        getContentPane().add(registerHourPanel);
    }

    private void assistancePage(){
        JPanel assistancePanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        // Possible activities to assist
        ArrayList<Activity> possibleAssistActivities = new ArrayList<>();
        for(int i = 0; i<currentLoggedOn.getCurrentProject().getActivities(stm.getCurrentWeek()).size(); i++){
            if(currentLoggedOn.getAssignedActivites().contains(currentLoggedOn.getCurrentProject().getActivities(stm.getCurrentWeek()).get(i))){
                // Don't add to list
            }
            else{
                possibleAssistActivities.add(currentLoggedOn.getCurrentProject().getActivities(stm.getCurrentWeek()).get(i));
            }
        }

        JLabel labelActivity = new JLabel("Activity: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        assistancePanel.add(labelActivity, cs);

        boxOfAssignedActivities = new JComboBox(new Vector(possibleAssistActivities));
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 3;
        assistancePanel.add(boxOfAssignedActivities, cs);


        JLabel labelHours = new JLabel("Hours worked: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        assistancePanel.add(labelHours, cs);

        doubleFormatter.setAllowsInvalid(false);
        hours = new JFormattedTextField(doubleFormatter);
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        assistancePanel.add(hours, cs);

        JLabel labelDate = new JLabel("Day: ");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        assistancePanel.add(labelDate, cs);

        days = new JComboBox(new Vector(new ArrayList<String>(Arrays.asList(weekDays))));
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 3;
        assistancePanel.add(days, cs);

        registerTime = new JButton("Register Time");
        cs.gridx     = 1;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        registerTime.addActionListener(this);
        assistancePanel.add(registerTime, cs);

        backRegisterTimeMenue = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        backRegisterTimeMenue.addActionListener(this);
        assistancePanel.add( backRegisterTimeMenue, cs);



        getContentPane().add(assistancePanel);
    }

    private void assistancePanel(){
        JPanel assistPanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelEmployee = new JLabel("Employee to ask: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        assistPanel.add(labelEmployee, cs);

        ArrayList<Employee> Empl = new ArrayList<>();
        Empl.addAll(stm.getEmployees());
        Empl.remove(currentLoggedOn);
        boxOfEmployees = new JComboBox(new Vector(Empl));
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 3;
        assistPanel.add(boxOfEmployees, cs);

        JLabel labelActivities = new JLabel("Activity to assist: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        assistPanel.add(labelActivities, cs);

        assistActivity = new JComboBox(new Vector(currentLoggedOn.getAssignedActivites()));
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        assistPanel.add(assistActivity, cs);

        requestAssist = new JButton("Request assist");
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 3;
        requestAssist.addActionListener(this);
        assistPanel.add(requestAssist,cs);

        backRegisterTimeMenue = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        backRegisterTimeMenue.addActionListener(this);
        assistPanel.add( backRegisterTimeMenue, cs);

        getContentPane().add(assistPanel);
    }

    private void editHoursPage(){
        JPanel editHourPanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelActivity = new JLabel("Activity to Edit: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        editHourPanel.add(labelActivity, cs);

        boxOfAssignedActivitiesToEdit = new JComboBox(new Vector(currentLoggedOn.getAssignedActivites()));
        boxOfAssignedActivitiesToEdit.setSelectedIndex(editSelect);
        boxOfAssignedActivitiesToEdit.addActionListener(this);
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 3;
        editHourPanel.add(boxOfAssignedActivitiesToEdit, cs);

        JLabel labelDate = new JLabel("Day: ");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        editHourPanel.add(labelDate, cs);

        days = new JComboBox(new Vector(new ArrayList<String>(Arrays.asList(weekDays))));
        days.setSelectedIndex(daySelect);
        days.addActionListener(this);
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 3;
        editHourPanel.add(days, cs);

        String S = (String)days.getSelectedItem();
        int day = 0;
        if(S.equals("Monday"))day=1;
        if(S.equals("Tuesday"))day=2;
        if(S.equals("Wednesday"))day=3;
        if(S.equals("Thursday"))day=4;
        if(S.equals("Friday"))day=5;

        Activity A = (Activity) boxOfAssignedActivitiesToEdit.getSelectedItem();
        JLabel labelHours = new JLabel("Hours registered: "+currentLoggedOn.getHoursWorkedDayActivity(stm.getCurrentWeek(),day,A));
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        editHourPanel.add(labelHours, cs);

        doubleFormatter.setAllowsInvalid(false);
        hours = new JFormattedTextField(doubleFormatter);
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        editHourPanel.add(hours, cs);

        editTime = new JButton("Edit Time");
        cs.gridx     = 1;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        editTime.addActionListener(this);
        editHourPanel.add(editTime, cs);

        backRegisterTimeMenue = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        backRegisterTimeMenue.addActionListener(this);
        editHourPanel.add( backRegisterTimeMenue, cs);



        getContentPane().add(editHourPanel);
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

        intFormatter.setAllowsInvalid(false);
        intFormatter.setMaximum(100);
        projectStartWeek = new JFormattedTextField(intFormatter);
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        createProjectPanel.add(projectStartWeek, cs);

        JLabel labelProjectLeader = new JLabel("Project Leader (optional): ");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        createProjectPanel.add(labelProjectLeader, cs);

        boxOfEmployees = new JComboBox(new Vector(stm.getEmployees()));
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

        boxOfEmployees = new JComboBox(new Vector(stm.getEmployees()));
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 2;
        assignProjectLeaderPanel.add(boxOfEmployees, cs);

        JLabel labelPassword = new JLabel("Project: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        assignProjectLeaderPanel.add(labelPassword, cs);

        boxOfProjects     = new JComboBox(new Vector(stm.projectsWithoutAProjectLeader()));
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
        Panel p = new Panel();
        p.setLayout(new BorderLayout());

        JPanel projectLeaderPanel = new JPanel(new GridBagLayout());

        JLabel labelProject = new JLabel("Project: "+loggedOnProjectLeader.getCurrentProject());
        p.add(labelProject, BorderLayout.CENTER);

        createActivityPage = new JButton("Create Activity");
        createActivityPage.addActionListener(this);
        projectLeaderPanel.add(createActivityPage);

        editActivityPage = new JButton("Edit Activity");
        editActivityPage.addActionListener(this);
        projectLeaderPanel.add(editActivityPage);

        assignEmployeeToActivityPage = new JButton("Assign Employee");
        assignEmployeeToActivityPage.addActionListener(this);
        projectLeaderPanel.add(assignEmployeeToActivityPage);

        assignEmployeeToPersonalActivityPage = new JButton("Assign Employee Personal Activity");
        assignEmployeeToPersonalActivityPage.addActionListener(this);
        projectLeaderPanel.add(assignEmployeeToPersonalActivityPage);

        logout = new JButton("Log Out");
        logout.addActionListener(this);
        projectLeaderPanel.add(logout);

        p.add(projectLeaderPanel, BorderLayout.SOUTH);
        getContentPane().add(p);
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

        activityStartWeek = new JFormattedTextField(intFormatter);
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        createActivityPanel.add(activityStartWeek, cs);

        JLabel labelActivityEndWeek = new JLabel("Activity End Week: ");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        createActivityPanel.add(labelActivityEndWeek, cs);

        activityEndWeek = new JFormattedTextField(intFormatter);
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
        assignEmployeePanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelActivities = new JLabel("Activity to be assigned: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        assignEmployeePanel.add(labelActivities, cs);

        boxOfActivities = new JComboBox(new Vector(loggedOnProjectLeader.getAssignedProject().getActivities(stm.getCurrentWeek())));
        boxOfActivities.setSelectedIndex(selectAIndex);
        boxOfActivities.addActionListener(this);
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 3;
        assignEmployeePanel.add(boxOfActivities, cs);

        JLabel labelAvaliableEmployees = new JLabel("Employee to be assigned: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        assignEmployeePanel.add(labelAvaliableEmployees, cs);

        Activity A = (Activity)boxOfActivities.getSelectedItem();
        boxOfAvaliableEmployees = new JComboBox(new Vector(stm.AvailableEmployeesForAGivenActivity(A)));
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        assignEmployeePanel.add(boxOfAvaliableEmployees, cs);

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

    private void editActivityPage(){
        JPanel editActivityPanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelActivity = new JLabel("Activity ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        editActivityPanel.add(labelActivity, cs);

        boxOfActivities = new JComboBox(new Vector(loggedOnProjectLeader.getAssignedProject().getActivities(stm.getCurrentWeek())));
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 3;
        editActivityPanel.add(boxOfActivities, cs);


        JLabel labelStartWeek = new JLabel("New start week (optional): ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        editActivityPanel.add(labelStartWeek, cs);

        intFormatter.setAllowsInvalid(false);
        editActivityStartWeek = new JFormattedTextField(intFormatter);
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        editActivityPanel.add(editActivityStartWeek, cs);

        JLabel labelEndWeek = new JLabel("New end week (optional): ");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        editActivityPanel.add(labelEndWeek, cs);

        intFormatter.setAllowsInvalid(false);
        editActivityEndWeek = new JFormattedTextField(intFormatter);
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 3;
        editActivityPanel.add(editActivityEndWeek, cs);

        editActivity = new JButton("Edit Activity");
        cs.gridx     = 1;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        editActivity.addActionListener(this);
        editActivityPanel.add(editActivity, cs);

        backProjectLeader = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        backProjectLeader.addActionListener(this);
        editActivityPanel.add(backProjectLeader, cs);



        getContentPane().add(editActivityPanel);
    }

    private void assignEmployeeToPersonalActivityPage(){
        JPanel assignEmployeeToPersonalActivityPanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelPersonalA = new JLabel("Personal Activity: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        assignEmployeeToPersonalActivityPanel.add(labelPersonalA, cs);

        personalActivity     = new JTextField(20);
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 2;
        assignEmployeeToPersonalActivityPanel.add(personalActivity, cs);


        JLabel labelStartWeek = new JLabel("Employee: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        assignEmployeeToPersonalActivityPanel.add(labelStartWeek, cs);


        boxOfEmployees = new JComboBox(new Vector(stm.getEmployees()));
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        assignEmployeeToPersonalActivityPanel.add(boxOfEmployees, cs);

        JLabel StartWeek = new JLabel("Start Week: ");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        assignEmployeeToPersonalActivityPanel.add(StartWeek, cs);

        intFormatter.setAllowsInvalid(false);
        personalActivityStartWeek = new JFormattedTextField(intFormatter);
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 3;
        assignEmployeeToPersonalActivityPanel.add(personalActivityStartWeek, cs);

        JLabel EndWeek = new JLabel("End Week: ");
        cs.gridx     = 0;
        cs.gridy     = 3;
        cs.gridwidth = 1;
        assignEmployeeToPersonalActivityPanel.add(EndWeek, cs);

        intFormatter.setAllowsInvalid(false);
        personalActivityEndWeek = new JFormattedTextField(intFormatter);
        cs.gridx     = 1;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        assignEmployeeToPersonalActivityPanel.add(personalActivityEndWeek, cs);

        assignEmployeeToPersonalActivity = new JButton("Add Personal Activity to Employee");
        cs.gridx     = 1;
        cs.gridy     = 4;
        cs.gridwidth = 3;
        assignEmployeeToPersonalActivity.addActionListener(this);
        assignEmployeeToPersonalActivityPanel.add(assignEmployeeToPersonalActivity, cs);

        backProjectLeader = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 4;
        cs.gridwidth = 1;
        backProjectLeader.addActionListener(this);
        assignEmployeeToPersonalActivityPanel.add(backProjectLeader, cs);



        getContentPane().add(assignEmployeeToPersonalActivityPanel);
    }

    public void actionPerformed(ActionEvent e) {

        try {
            actionCommandsEmployeePage(e);
        } catch (NameAlreadyExistException e1) {
            e1.printStackTrace();
        }

        try {
            actionCommandsRegisterHours(e);
        } catch (UnableToAssignException e1) {
            e1.printStackTrace();
        }

        try {
            actionCommandsProjectLeaderPage(e);
        } catch (InvalidInputException | NameAlreadyExistException | UnableToAssignException e1) {
            e1.printStackTrace();
        }

        if (e.getSource() == login) {
            if(stm.checkLogin(userName.getText().trim(), password.getText().trim())) {
                currentLoggedOn = stm.getEmployeeByID(userName.getText().trim());
                // If the currentLoggedOn is null, then it is a project leader
                if(currentLoggedOn == null ){
                    loggedOnProjectLeader = stm.getProjectLeaderByID(userName.getText().trim());
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
        if(e.getSource()== nextWeek){
            stm.nextWeek();
            getContentPane().removeAll();
            loginPage();
            revalidate();
            repaint();
        }
    }

    private void actionCommandsRegisterHours(ActionEvent e) throws UnableToAssignException {
        if(e.getSource()==registerHoursMenue || e.getSource()==backRegisterTimeMenue){
            if(currentLoggedOn.getCurrentProject()==null){
                registerHoursMenue.setText("You are not on a project");
                registerHoursMenue.setBackground(Color.RED);
                registerHoursMenue.setOpaque(true);
                registerHoursMenue.setBorderPainted(false);
                revalidate();
                repaint();
            }
            else {
                getContentPane().removeAll();
                registerHoursPage();
                revalidate();
                repaint();
            }
        }
        if(e.getSource()==registerTimePage){
                getContentPane().removeAll();
                registerHoursTable();
                revalidate();
                repaint();
        }
        if(e.getSource() == registerTime){
            if(boxOfAssignedActivities.getSelectedItem() == null || hours.getText().trim().equals("")){
                registerTime.setText("Something went wrong, try again");
                revalidate();
                repaint();
            }
            else{
                Activity A = (Activity) boxOfAssignedActivities.getSelectedItem();
                double activityID = A.getID();
                Double time = Double.valueOf(hours.getText().trim());
                // Convert from string monday etc. to number 1-5
                int dayNumber = 0;
                for(int i = 0; i<weekDays.length ; i++){
                    if(weekDays[i]==days.getSelectedItem().toString()){
                        dayNumber = i;
                    }
                }

				try {
					currentLoggedOn.registerHours(activityID,dayNumber+1,time);
				} catch (InvalidInputException e1) {
					e1.printStackTrace();
				}

				registerTime.setText("Time is registered");
                revalidate();
                repaint();
            }
        }
        if(e.getSource() == assistancePage){
            getContentPane().removeAll();
            assistancePage();
            revalidate();
            repaint();
        }
        if(e.getSource() == editHoursPage){
            getContentPane().removeAll();
            editHoursPage();
            revalidate();
            repaint();
        }
        if(e.getSource() == days || e.getSource()==boxOfAssignedActivitiesToEdit){
            daySelect = days.getSelectedIndex();
            editSelect = boxOfAssignedActivitiesToEdit.getSelectedIndex();
            getContentPane().removeAll();
            editHoursPage();
            revalidate();
            repaint();
        }
        if(e.getSource() == editTime){
            if(boxOfAssignedActivitiesToEdit.getSelectedItem() == null || hours.getText().trim().equals("")){
                editTime.setText("Something went wrong, try again");
                revalidate();
                repaint();
            }
            else{
                Activity A = (Activity) boxOfAssignedActivitiesToEdit.getSelectedItem();
                double activityID = A.getID();
                Double time = Double.valueOf(hours.getText().trim());
                // Convert from string monday etc. to number 1-5
                int dayNumber = 0;
                for(int i = 0; i<weekDays.length ; i++){
                    if(weekDays[i]==days.getSelectedItem().toString()){
                        dayNumber = i;
                    }
                }

				try {
					currentLoggedOn.editHours(activityID,dayNumber+1,time);
				} catch (InvalidInputException e1) {
					e1.printStackTrace();
				}

				editTime.setText("Time is edited");
                revalidate();
                repaint();
            }
        }
        if(e.getSource() == assistancePage){
            getContentPane().removeAll();
            assistancePanel();
            revalidate();
            repaint();
        }
        if(e.getSource() == requestAssist){
            Employee E = (Employee) boxOfEmployees.getSelectedItem();
            Activity A = (Activity) assistActivity.getSelectedItem();
            if(E==null || A==null){
                requestAssist.setText("Something went wrong, try again");
                revalidate();
                repaint();
            }
            else{
                ProjectLeader pL = currentLoggedOn.getCurrentProject().getProjectLeader();
                try {
                    pL.addActivity("Assist"+idCounter,idCounter,stm.getCurrentWeek(),stm.getCurrentWeek()+1);
                } catch (InvalidInputException | NameAlreadyExistException e1) {
                    e1.printStackTrace();
                }

                pL.assignEmployee(E, pL.getAssignedProject().activities.get((int)idCounter));
                idCounter++;
                requestAssist.setText("You have requested assist");
                revalidate();
                repaint();
            }
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
            else if(stm.doesProjectIDExist(name)){
                addProject.setText("The name already exist, try again");
                revalidate();
                repaint();
            }
            else if(Integer.parseInt(startWeek)<stm.getCurrentWeek()){
                addProject.setText("The start week is invalid, try again");
                revalidate();
                repaint();
            }
            else if(boxOfEmployees.getSelectedIndex()==-1){
                currentLoggedOn.AddProject(name,Integer.parseInt(startWeek));
                addProject.setText("Project "+name+" has been added");
                revalidate();
                repaint();
            }
            else{
                currentLoggedOn.AddProject(name,Integer.parseInt(startWeek),(Employee)boxOfEmployees.getSelectedItem());
                addProject.setText("Project "+name+" has been added");
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
            stm.AssignProjectLeader((Employee)boxOfEmployees.getSelectedItem(),(Project)boxOfProjects.getSelectedItem());
            addProjectLeader.setText("Project Leader "+(Employee)boxOfEmployees.getSelectedItem()+" is assigned!");
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

    private void actionCommandsProjectLeaderPage(ActionEvent e) throws InvalidInputException, NameAlreadyExistException, UnableToAssignException {
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
            else if(loggedOnProjectLeader.getAssignedProject().doesActivityNameExist(name)){
                addActivity.setText("The activity name already exist, try again");
                revalidate();
                repaint();
            }
            else{
                loggedOnProjectLeader.addActivity(name, idCounter, Integer.parseInt(startWeek), Integer.parseInt(endWeek));
                idCounter++;
                addActivity.setText("Activity "+name+" is added!");
                revalidate();
                repaint();
            }
        }
        if(e.getSource() == assignEmployeeToActivityPage){
            if(loggedOnProjectLeader.getAssignedProject().getActivities(stm.getCurrentWeek()).size()==0){
                assignEmployeeToActivityPage.setText("Create some activities first");
                revalidate();
                repaint();
            }
            else{
                getContentPane().removeAll();
                assignEmployeePage();
                revalidate();
                repaint();
            }
        }
        if(e.getSource() == assignEmployeeToActivity){
            loggedOnProjectLeader.assignEmployee((Employee)boxOfAvaliableEmployees.getSelectedItem(),(ProjectActivity)boxOfActivities.getSelectedItem());
            assignEmployeeToActivity.setText("Employee has been assigned");
            getContentPane().removeAll();
            assignEmployeePage();
            revalidate();
            repaint();

        }
        if(e.getSource() == editActivityPage){
            getContentPane().removeAll();
            editActivityPage();
            revalidate();
            repaint();
        }
        if(e.getSource() == editActivity){
            Activity A = (Activity)boxOfActivities.getSelectedItem();
            int currentEndWeek = A.getEndWeek();
            int currentStartWeek = A.getStartWeek();
            if((editActivityStartWeek.getText().trim().equals("") && editActivityEndWeek.getText().trim().equals("")) || boxOfActivities.getSelectedItem()==null){
                editActivity.setText("Nothing has changed");
                revalidate();
                repaint();
            }
            else if(editActivityEndWeek.getText().trim().equals("")) {
                if(loggedOnProjectLeader.getAssignedProject().getStartWeek()>Integer.parseInt(editActivityStartWeek.getText().trim())){
                    editActivity.setText("The start week is before project start week, try again");
                    revalidate();
                    repaint();
                }
                else if(Integer.parseInt(editActivityStartWeek.getText().trim())>currentEndWeek){
                    editActivity.setText("The start week is after end week, try again");
                    revalidate();
                    repaint();
                }
                else{
                    Activity a = (Activity) boxOfActivities.getSelectedItem();
                    a.setStartWeek(Integer.parseInt(editActivityStartWeek.getText().trim()));
                    editActivity.setText("The start week has been changed");
                    revalidate();
                    repaint();
                }
            }
            else if(editActivityStartWeek.getText().trim().equals("")){
                if(currentStartWeek>Integer.parseInt(editActivityEndWeek.getText().trim())){
                    editActivity.setText("The end week is before start week, try again");
                    revalidate();
                    repaint();
                }
                else{
                    Activity a = (Activity) boxOfActivities.getSelectedItem();
                    a.setEndWeek(Integer.parseInt(editActivityEndWeek.getText().trim()));
                    editActivity.setText("The end week has been changed");
                    revalidate();
                    repaint();
                }
            }
            else {
                if(loggedOnProjectLeader.getAssignedProject().getStartWeek()>Integer.parseInt(editActivityStartWeek.getText().trim())){
                    editActivity.setText("The start week is before project start week, try again");
                    revalidate();
                    repaint();
                }
                else if(Integer.parseInt(editActivityStartWeek.getText().trim())>Integer.parseInt(editActivityEndWeek.getText().trim())){
                    editActivity.setText("Please set start week before an end week");
                    revalidate();
                    repaint();
                }
                else{
                    Activity A1 = (Activity) boxOfActivities.getSelectedItem();
                    A1.setStartWeek(Integer.parseInt(editActivityStartWeek.getText().trim()));
                    A1.setEndWeek(Integer.parseInt(editActivityEndWeek.getText().trim()));
                    editActivity.setText("The start week and end week has been changed");
                    revalidate();
                    repaint();
                }
            }
        }
        if(e.getSource()==boxOfActivities){
            selectAIndex = boxOfActivities.getSelectedIndex();
            getContentPane().removeAll();
            assignEmployeePage();
            revalidate();
            repaint();
        }
        if(e.getSource() == assignEmployeeToPersonalActivityPage){
            getContentPane().removeAll();
            assignEmployeeToPersonalActivityPage();
            revalidate();
            repaint();
        }
        if(e.getSource() == assignEmployeeToPersonalActivity){
            String name = assignEmployeeToPersonalActivity.getText().trim();
            if(name.equals("") || personalActivityStartWeek.getText().trim().equals("")
                    || personalActivityEndWeek.getText().trim().equals("")){
                assignEmployeeToPersonalActivity.setText("Please fill out fields, try again");
                revalidate();
                repaint();
            }
            else{
                int startWeek = Integer.parseInt(personalActivityStartWeek.getText().trim());
                int endWeek = Integer.parseInt(personalActivityEndWeek.getText().trim());
                if(startWeek<stm.getCurrentWeek()){
                    assignEmployeeToPersonalActivity.setText("The start week is in the past, try again");
                    revalidate();
                    repaint();
                }
                else if(endWeek<startWeek){
                    assignEmployeeToPersonalActivity.setText("The end week is before start week, try again");
                    revalidate();
                    repaint();
                }
                else{
                    Employee E = (Employee) boxOfEmployees.getSelectedItem();
                    loggedOnProjectLeader.addPersonalActivity(name, idCounter,startWeek,endWeek,E);
                    idCounter++;
                    assignEmployeeToPersonalActivity.setText("Employee is assigned to personal activity.");
                    revalidate();
                    repaint();
                }
            }
        }
    }

    private void windowProperties(JFrame mainFrame){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 400);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setVisible(true);
    }


}
