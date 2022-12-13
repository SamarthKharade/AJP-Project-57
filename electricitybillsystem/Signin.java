package electricitybillsystem;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signin extends JFrame implements ActionListener {

    JButton register, cancel2, backtologin;
    JLabel createas, meter, uname, name, pass;
    Choice choice2;
    JTextField mnumber, uname2, name2, pass2;

    public Signin() {

        getContentPane().setBackground(Color.cyan);
        /* JPanel p1=new JPanel();
      p1.setBounds(40,40,600,300);
      p1.setBackground(Color.pink);*/

        //p1.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230,2),"Create-Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(172,216,230))));
        //add(p1);
        createas = new JLabel("Create Account As:");
        createas.setBounds(100, 50, 140, 20);
        createas.setFont(new Font("Times new roman", Font.BOLD, 14));
        add(createas);

        choice2 = new Choice();
        choice2.add("Admin");
        choice2.add("Customer");
        choice2.setBounds(260, 50, 150, 20);
        add(choice2);

        meter = new JLabel("Meter Number :");
        meter.setBounds(100, 90, 140, 20);
        meter.setFont(new Font("Times new roman", Font.BOLD, 14));
        meter.setVisible(false);
        add(meter);

        mnumber = new JTextField();
        mnumber.setBounds(260, 90, 150, 20);
        mnumber.setVisible(false);
        add(mnumber);

        uname = new JLabel("Username :");
        uname.setBounds(100, 130, 140, 20);
        uname.setFont(new Font("Times new roman", Font.BOLD, 14));
        add(uname);

        uname2 = new JTextField();
        uname2.setBounds(260, 130, 150, 20);
        add(uname2);

        name = new JLabel("Name :");
        name.setBounds(100, 170, 140, 20);
        name.setFont(new Font("Times new roman", Font.BOLD, 14));
        add(name);

        name2 = new JTextField();
        name2.setBounds(260, 170, 150, 20);
        add(name2);

        mnumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent fe) {

            }

            public void focusLost(FocusEvent fe) {
                try {
                     Database c = new Database();
                    ResultSet rs = c.s.executeQuery("Select * from login where meter_no='" + mnumber.getText() + "'");
                    while (rs.next()) {
                        name2.setText(rs.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pass = new JLabel("Password :");
        pass.setBounds(100, 210, 140, 20);
        pass.setFont(new Font("Times new roman", Font.BOLD, 14));
        add(pass);

        pass2 = new JTextField();
        pass2.setBounds(260, 210, 150, 20);
        add(pass2);

        choice2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                String user = choice2.getSelectedItem();
                if (user.equals("Customer")) {
                    meter.setVisible(true);
                    mnumber.setVisible(true);
                    name2.setEditable(false);
                } else {
                    meter.setVisible(false);
                    mnumber.setVisible(false);
                    name2.setEditable(true);
                }
            }

        });

        register = new JButton("Register");
        register.setBackground(Color.green);
        register.setBounds(120, 280, 100, 20);
        register.addActionListener(this);
        add(register);

        cancel2 = new JButton("Cancel");
        cancel2.setBackground(Color.red);
        cancel2.setBounds(280, 280, 100, 20);
        cancel2.addActionListener(this);
        add(cancel2);

        backtologin = new JButton("Back To Login");
        backtologin.setBackground(Color.yellow);
        backtologin.setBounds(180, 320, 150, 20);
        backtologin.addActionListener(this);
        add(backtologin);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/sign.png"));
        Image i8 = i7.getImage().getScaledInstance(257, 196, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image3 = new JLabel(i9);
        image3.setBounds(420, 50, 257, 196);
        add(image3);

        setLayout(null);
        //setSize(640,300);
        setBounds(150, 150, 700, 400);
        setVisible(true);
        setTitle("Regesteration Page");
        setLocation(400, 200);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == register) {
            String atype = choice2.getSelectedItem();
            String susername = uname2.getText();
            String sname = name2.getText();
            String spassword = pass2.getText();
            String smeter = mnumber.getText();

            try {
                Database c = new Database();
                String query = null;
                if (atype.equals("Admin")) {
                    query = "insert into login values('" + smeter + "','" + susername + "','" + sname + "','" + spassword + "','" + atype + "')";
                } else {
                    query = "update login set username='" + susername + "',password='" + spassword + "',user='" + atype + "' where meter_no='" + smeter + "'";
                }
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account Created Successfully!!!");
                setVisible(false);
                new LoginPage();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == cancel2) {
            setVisible(false);
        } else if (ae.getSource() == backtologin) {

            //new LoginPage();
            setVisible(false);
            new LoginPage();
        } else {

        }
    }

    public static void main(String[] args) {
        new Signin();
    }
}
