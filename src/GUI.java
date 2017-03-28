/**
 * Created by Emil on 28/03/2017.
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener{
    private String user;
    private String pass;
    private JTextField userName;
    private JTextField password;
    private JButton login;

    // Panels
    private JPanel loginPanel;

    public static void main(String[] args) {

        GUI mainFrame = new GUI();

        windowProperties(mainFrame);
    }

    public GUI(){
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
        user = userName.getText().trim();
        pass = password.getText().trim();

        if(user.equals("Emil") && pass.equals("123")){
            return true;
        }
        if(user.equals("William") && pass.equals("321")){
            return true;
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            if(checkLogin()) {
                JLabel myLabel = new JLabel("test");
                getContentPane().removeAll();
                revalidate();
                repaint();
                getContentPane().add(myLabel);
                revalidate();
                repaint();
            }
        }
    }

    public static void windowProperties(JFrame mainFrame){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 200);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setVisible(true);
    }
}
