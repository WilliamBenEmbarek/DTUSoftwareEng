/**
 * Created by Emil on 28/03/2017.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener{

    public static void main(String[] args) {
        GUI simplegui = new GUI();

        simplegui.setTitle("SimpleGui"); // Set title on window
        simplegui.setSize(600, 400);     // Set size
        simplegui.setResizable(false);    // Allow the window to be resized
        simplegui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        simplegui.setVisible(true);      // Make the window visible
    }

    public void actionPerformed(ActionEvent e) {
    }

    public GUI(){

    }
}
