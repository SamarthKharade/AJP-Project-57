package electricitybillsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {

    JButton login, signin, cancel;
    JTextField username1, password1;
    Choice choice;
    JButton pay, close;

    public LoginPage() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel username = new JLabel("Username:");
        username.setBounds(300, 20, 100, 20);
        add(username);
        username1 = new JTextField();
        username1.setBounds(400, 20, 150, 20);
        add(username1);
        JLabel password = new JLabel("Password:");
        password.setBounds(300, 60, 100, 20);
        add(password);
        password1 = new JTextField();
        password1.setBounds(400, 60, 150, 20);
        add(password1);
        JLabel loginas = new JLabel("Login As:");
        loginas.setBounds(300, 100, 100, 20);
        add(loginas);
        choice = new Choice();
        choice.add("Admin");
        choice.add("Customer");
        choice.setBounds(400, 100, 150, 20);
        add(choice);
        login = new JButton("Login");
        login.setBackground(Color.green);
        login.setBounds(330, 160, 100, 20);
        login.addActionListener(this);
        add(login);
        signin = new JButton("Sign in");
        signin.setBackground(Color.cyan);
        signin.setBounds(450, 160, 100, 20);
        signin.addActionListener(this);
        add(signin);
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.red);
        cancel.setBounds(380, 200, 100, 20);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icon/login.jpg"));
        Image i5 = i4.getImage().getScaledInstance(216, 233, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image2 = new JLabel(i6);
        image2.setBounds(5, 5, 216, 233);
        add(image2);
        setSize(640, 300);
        setVisible(true);
        setTitle("Login Page");
        setLocation(400, 200);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String susername = username1.getText();
            String spassword = password1.getText();
            String slogin = choice.getSelectedItem();
            JLabel message = new JLabel();

            try {
               Database c = new Database();
                String query = "Select * from login where username='" + susername + "' and password='" + spassword + "' and user='" + slogin + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(slogin, meter);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login!!!");
                    username1.setText(" ");
                    password1.setText(" ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == signin) {
            setVisible(false);
            new Signin();
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        } else {
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
