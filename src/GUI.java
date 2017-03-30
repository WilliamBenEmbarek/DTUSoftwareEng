/**
 * Created by Emil on 28/03/2017.
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener{
    private SystemTimeManager STM;
    private Employee currentLoggedOn;

    private String ID;
    private String pass;

    // Textfields to be added
    private JTextField userName;
    private JTextField password;
    private JTextField projectName;
    private JTextField newProjectName;
    private JTextField projectStartWeek;
    private JTextField projectLeader;
    private JTextField projectAssignProjectLeader;

    // Functional buttons
    private JButton logout;
    private JButton back;
    private JButton login;
    private JButton createProject;
    private JButton addProject;
    private JButton assignProjectLeader;
    private JButton addProjectLeader;

    // Panels
    private JPanel loginPanel;
    private JPanel employeePanel;
    private JPanel createProjectPanel;
    private JPanel assignProjectLeaderPanel;


    public static void main(String[] args) {
        GUI mainFrame = new GUI();

        windowProperties(mainFrame);
    }

    public GUI(){
        STM = new SystemTimeManager();
        setUpEmployees();
        loginPage();
    }

    public void loginPage(){
        loginPanel = new JPanel(new GridBagLayout());

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

    // Hardcoding of login
    public boolean checkLogin(){
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

    public void employeePage(){
        employeePanel = new JPanel(new GridBagLayout());

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

    public void createProjectPage(){
        createProjectPanel = new JPanel(new GridBagLayout());

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

        projectStartWeek = new JTextField(20);
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 3;
        createProjectPanel.add(projectStartWeek, cs);

        JLabel labelProjectLeader = new JLabel("Project Leader (optional): ");
        cs.gridx     = 0;
        cs.gridy     = 2;
        cs.gridwidth = 1;
        createProjectPanel.add(labelProjectLeader, cs);

        projectAssignProjectLeader = new JTextField(20);
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 3;
        createProjectPanel.add(projectAssignProjectLeader, cs);

        addProject = new JButton("Add Project");
        cs.gridx     = 1;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        addProject.addActionListener(this);
        createProjectPanel.add(addProject, cs);

        back = new JButton("Back");
        cs.gridx     = 0;
        cs.gridy     = 3;
        cs.gridwidth = 3;
        back.addActionListener(this);
        createProjectPanel.add(back, cs);


        getContentPane().add(createProjectPanel);
    }

    public void assignProjectLeaderPage(){
        assignProjectLeaderPanel = new JPanel(new GridBagLayout());

        // Sets contrains to organize components in the panel.
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelUsername = new JLabel("Project Leader: ");
        cs.gridx     = 0;
        cs.gridy     = 0;
        cs.gridwidth = 1;
        assignProjectLeaderPanel.add(labelUsername, cs);

        projectLeader     = new JTextField(20);
        cs.gridx     = 1;
        cs.gridy     = 0;
        cs.gridwidth = 2;
        assignProjectLeaderPanel.add(projectLeader, cs);

        JLabel labelPassword = new JLabel("Project: ");
        cs.gridx     = 0;
        cs.gridy     = 1;
        cs.gridwidth = 1;
        assignProjectLeaderPanel.add(labelPassword, cs);

        projectName     = new JTextField(20);
        cs.gridx     = 1;
        cs.gridy     = 1;
        cs.gridwidth = 2;
        assignProjectLeaderPanel.add(projectName, cs);


        addProjectLeader = new JButton("Assign this leader to the project");
        cs.gridx     = 1;
        cs.gridy     = 2;
        cs.gridwidth = 2;
        addProjectLeader.addActionListener(this);
        assignProjectLeaderPanel.add(addProjectLeader, cs);

        getContentPane().add(assignProjectLeaderPanel);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            if(checkLogin()) {
                currentLoggedOn = new Employee("ID");
                if(STM.ProjectLeaderIDs.contains(this.ID)){
                    getContentPane().removeAll();
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
        else if (e.getSource() == createProject){
            getContentPane().removeAll();
            createProjectPage();
            revalidate();
            repaint();

        }
        else if (e.getSource() == addProject){
            String name = newProjectName.getText().trim();
            String startWeek = projectStartWeek.getText().trim();
            String assignProjectLeader = projectAssignProjectLeader.getText().trim();
            if(assignProjectLeader.equals("")){
                currentLoggedOn.AddProject(name,Integer.parseInt(startWeek));
                addProject.setText("Project has been added");
                revalidate();
                repaint();
            }
            else{
                if(STM.canTheIDBeAssignToProjectLeader(assignProjectLeader)){
                    currentLoggedOn.AddProject(name,Integer.parseInt(startWeek),assignProjectLeader);
                    addProject.setText("Project has been added");
                    revalidate();
                    repaint();
                }
                else{
                    addProject.setText("Invalid Project Leader ID");
                    revalidate();
                    repaint();
                }
            }
        }
        else if(e.getSource() == assignProjectLeader){
            getContentPane().removeAll();
            assignProjectLeaderPage();
            revalidate();
            repaint();
        }
        else if(e.getSource() == addProjectLeader){
            String leader = projectLeader.getText().trim();
            String project = projectName.getText().trim();

            if(STM.canTheIDBeAssignToProjectLeader(leader) && STM.doesTheProjectExist(project)){
                STM.AssignProjectLeader(leader ,project);
            }
        }
        else if (e.getSource() == back){
            getContentPane().removeAll();
            employeePage();
            revalidate();
            repaint();
        }
        else if (e.getSource() == logout){
            getContentPane().removeAll();
            loginPage();
            revalidate();
            repaint();
        }
    }

    public static void windowProperties(JFrame mainFrame){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 400);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setVisible(true);
    }

    // Hardcode employees
    public void setUpEmployees(){
        Employee e1 = new Employee("Emil");
        STM.Employees.add(e1);
        Employee e2 = new Employee("William");
        STM.Employees.add(e2);
    }
}
