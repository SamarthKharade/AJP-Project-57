package electricitybillsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateInformation extends JFrame implements ActionListener {

    JLabel heading, lblname, lblname2, lblmeterno, lblmeterno2, lbladdress, lblcity, lblstate, lblemail, lblphone, image;
    JButton update, cancel;
    JTextField lbladdress2, lblcity2, lblstate2, lblemail2, lblphone2;
    String meter;

    UpdateInformation(String meter) {
        this.meter = meter;
        setLayout(null);
        setBounds(200, 150, 1000, 450);
        getContentPane().setBackground(Color.WHITE);

        heading = new JLabel("Update Customer Information");
        heading.setBounds(370, 0, 500, 40);
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

        lbladdress2 = new JTextField();
        lbladdress2.setBounds(200, 200, 150, 20);
        add(lbladdress2);

        lblcity = new JLabel("City:");
        lblcity.setBounds(70, 260, 100, 20);
        add(lblcity);

        lblcity2 = new JTextField();
        lblcity2.setBounds(200, 260, 150, 20);
        add(lblcity2);

        lblstate = new JLabel("State:");
        lblstate.setBounds(430, 80, 100, 20);
        add(lblstate);

        lblstate2 = new JTextField();
        lblstate2.setBounds(550, 80, 150, 20);
        add(lblstate2);

        lblemail = new JLabel("Email:");
        lblemail.setBounds(430, 140, 100, 20);
        add(lblemail);

        lblemail2 = new JTextField();
        lblemail2.setBounds(550, 140, 150, 20);
        add(lblemail2);

        lblphone = new JLabel("Phone:");
        lblphone.setBounds(430, 200, 100, 20);
        add(lblphone);

        lblphone2 = new JTextField();
        lblphone2.setBounds(550, 200, 150, 20);
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
        update = new JButton("Update");
        update.setBackground(Color.GREEN);
        update.setForeground(Color.WHITE);
        update.setBounds(250, 320, 100, 20);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(450, 320, 100, 20);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/updateinfo.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        image = new JLabel(i3);
        image.setBounds(750, 100, 300, 200);
        add(image);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            String address = lbladdress2.getText();
            String city = lblcity2.getText();
            String state = lblstate2.getText();
            String email = lblemail2.getText();
            String phone = lblphone2.getText();

            try {
                Database c = new Database();
                int i = c.s.executeUpdate("update customer set address='" + address + "',city='" + city + "',state='" + state + "',email='" + email + "',phone='" + phone + "' where meter_no='" + meter + "'");
                JOptionPane.showMessageDialog(null, "User Information Updated Successfully!!!");
            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateInformation("");
    }
}
