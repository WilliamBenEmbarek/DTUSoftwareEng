package TimeManagement.Presentation; /**
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

    private SystemTimeManager STM;
    private Employee currentLoggedOn;
    private ProjectLeader loggedOnProjectLeader;
    private double idCounter = 0.0;

    // Textfields to be added
    private JTextField userName;
    private JTextField password;
    private JTextField newProjectName;
    private JTextField newActivityName;

    // Number fields
    private JFormattedTextField projectStartWeek;
    private JFormattedTextField activityStartWeek;
    private JFormattedTextField activityEndWeek;
    private JFormattedTextField editActivityStartWeek;
    private JFormattedTextField editActivityEndWeek;
    private JFormattedTextField hours;

    // Drop-down menus
    private JComboBox boxOfEmployees;
    private JComboBox boxOfProjects;
    private JComboBox boxOfAvaliableEmployees;
    private JComboBox boxOfActivities;
    private JComboBox boxOfAssignedActivities;
    private JComboBox days;

    // Week days
    private String[] weekDays = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday"};

    // JPanel
    private JPanel assignEmployeePanel;
    private int selectAIndex=0;

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

        nextWeek     = new JButton("Next Week");
        cs.gridx     = 1;
        cs.gridy     = 3;
        cs.gridwidth = 2;
        nextWeek.addActionListener(this);
        loginPanel.add(nextWeek, cs);

        JLabel labelWeek = new JLabel("Week: "+STM.getCurrentWeek());
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

        JPanel registerHoursPanel = new JPanel(new GridBagLayout());

        Object rowData[][] = currentLoggedOn.getActivityHours();
        Object columnNames[] = { "Column One", "Column Two" };
        System.out.println(Arrays.deepToString(rowData));
        JTable table = new JTable(rowData, columnNames);
        panel.add(table,BorderLayout.CENTER);

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
        for(int i = 0; i<currentLoggedOn.getCurrentProject().getActivities().size();i++){
            if(currentLoggedOn.getAssignedActivites().contains(currentLoggedOn.getCurrentProject().getActivities().get(i))){
                // Don't add to list
            }
            else{
                possibleAssistActivities.add(currentLoggedOn.getCurrentProject().getActivities().get(i));
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

        boxOfActivities = new JComboBox(new Vector(loggedOnProjectLeader.getAssignedProject().getActivities()));
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
        boxOfAvaliableEmployees = new JComboBox(new Vector(STM.AvailableEmployeesForAGivenActivity(A)));
        System.out.println(Arrays.toString(STM.AvailableEmployeesForAGivenActivity(A).toArray()));
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

        boxOfActivities = new JComboBox(new Vector(loggedOnProjectLeader.getAssignedProject().getActivities()));
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

    public void actionPerformed(ActionEvent e) {

        try {
            actionCommandsEmployeePage(e);
        } catch (NameAlreadyExistException e1) {
            e1.printStackTrace();
        }
        actionCommandsRegisterHours(e);
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
        if(e.getSource()== nextWeek){
            STM.nextWeek();
            getContentPane().removeAll();
            loginPage();
            revalidate();
            repaint();
            System.out.println(""+STM.getCurrentWeek());
        }
    }

    private void actionCommandsRegisterHours(ActionEvent e){
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

                currentLoggedOn.registerHours(activityID,dayNumber+1,time);

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
            STM.AssignProjectLeader((Employee)boxOfEmployees.getSelectedItem(),(Project)boxOfProjects.getSelectedItem());
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

    private void actionCommandsProjectLeaderPage(ActionEvent e)  {
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
                loggedOnProjectLeader.addActivity(name, idCounter, Integer.parseInt(startWeek), Integer.parseInt(endWeek));
                idCounter++;
                addActivity.setText("Activity "+name+" is added!");
                revalidate();
                repaint();
            }
        }
        if(e.getSource() == assignEmployeeToActivityPage){
            if(loggedOnProjectLeader.getAssignedProject().getActivities().size()==0){
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
                else{
                    Activity a = (Activity) boxOfActivities.getSelectedItem();
                    a.setStartWeek(Integer.parseInt(editActivityStartWeek.getText().trim()));
                    editActivity.setText("The start week has been changed");
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
                    Activity A = (Activity) boxOfActivities.getSelectedItem();
                    A.setStartWeek(Integer.parseInt(editActivityStartWeek.getText().trim()));
                    A.setEndWeek(Integer.parseInt(editActivityEndWeek.getText().trim()));
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
    }

    private void windowProperties(JFrame mainFrame){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 400);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setVisible(true);
    }

}
