package electricitybillsystem;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewInformation extends JFrame implements ActionListener {

    JLabel heading, lblname, lblname2, lblmeterno, lblmeterno2, lbladdress, lbladdress2, lblcity, lblcity2, lblstate, lblstate2, lblemail, lblemail2, lblphone, lblphone2, image;
    JButton cancel;

    ViewInformation(String meter) {
        setLayout(null);
        setBounds(350, 100, 850, 550);
        getContentPane().setBackground(Color.WHITE);

        heading = new JLabel("View Customer Information");
        heading.setBounds(300, 0, 500, 40);
        heading.setFont(new Font("Times new roman", Font.PLAIN, 20));
        heading.setForeground(Color.blue);
        add(heading);

        lblname = new JLabel("Name:");
        lblname.setBounds(70, 80, 100, 20);
        add(lblname);

        lblname2 = new JLabel(" ");
        lblname2.setBounds(200, 80, 100, 20);
        add(lblname2);

        lblmeterno = new JLabel("Meter Number:");
        lblmeterno.setBounds(70, 140, 100, 20);
        add(lblmeterno);

        lblmeterno2 = new JLabel(" ");
        lblmeterno2.setBounds(200, 140, 100, 20);
        add(lblmeterno2);

        lbladdress = new JLabel("Address:");
        lbladdress.setBounds(70, 200, 100, 20);
        add(lbladdress);

        lbladdress2 = new JLabel(" ");
        lbladdress2.setBounds(200, 200, 100, 20);
        add(lbladdress2);

        lblcity = new JLabel("City:");
        lblcity.setBounds(70, 260, 100, 20);
        add(lblcity);

        lblcity2 = new JLabel(" ");
        lblcity2.setBounds(200, 260, 100, 20);
        add(lblcity2);

        lblstate = new JLabel("State:");
        lblstate.setBounds(430, 80, 100, 20);
        add(lblstate);

        lblstate2 = new JLabel(" ");
        lblstate2.setBounds(550, 80, 100, 20);
        add(lblstate2);

        lblemail = new JLabel("Email:");
        lblemail.setBounds(430, 140, 100, 20);
        add(lblemail);

        lblemail2 = new JLabel(" ");
        lblemail2.setBounds(550, 140, 200, 20);
        add(lblemail2);

        lblphone = new JLabel("Phone:");
        lblphone.setBounds(430, 200, 100, 20);
        add(lblphone);

        lblphone2 = new JLabel(" ");
        lblphone2.setBounds(550, 200, 100, 20);
        add(lblphone2);

        try {
            Database c = new Database();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no='" + meter + "'");
            while (rs.next()) {
                lblname2.setText(rs.getString("name"));
                lblmeterno2.setText(rs.getString("meter_no"));
                lbladdress2.setText(rs.getString("address"));
                lblcity2.setText(rs.getString("city"));
                lblstate2.setText(rs.getString("state"));
                lblemail2.setText(rs.getString("email"));
                lblphone2.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(340, 320, 100, 20);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 150, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        image = new JLabel(i3);
        image.setBounds(20, 350, 450, 150);
        add(image);

    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new ViewInformation("");
    }
}
