package electricitybillsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener {

    JLabel image, heading, lblmeterno, lblmeterno2, lblname, lblname2, lblmonth, lblunits, lblunits2, lbltotalbill, lbltotalbill2, lblstatus, lblstatus2;
    Choice chsm;
    JButton pay, cancel;
    String meter;

    PayBill(String meter) {
        this.meter = meter;
        setLayout(null);
        setBounds(300, 100, 900, 600);
        heading = new JLabel("Electricity  Bill");
        getContentPane().setBackground(Color.cyan);

        heading.setFont(new Font("Times new roman", Font.BOLD, 24));
        heading.setBounds(350, 5, 200, 30);
        heading.setForeground(Color.RED);
        add(heading);

        lblmeterno = new JLabel("Meter Number:");
        lblmeterno.setBounds(70, 140, 100, 20);
        add(lblmeterno);

        lblmeterno2 = new JLabel(" ");
        lblmeterno2.setBounds(200, 140, 100, 20);
        add(lblmeterno2);

        lblname = new JLabel("Name:");
        lblname.setBounds(70, 200, 100, 20);
        add(lblname);

        lblname2 = new JLabel(" ");
        lblname2.setBounds(200, 200, 100, 20);
        add(lblname2);

        lblmonth = new JLabel("Month:");
        lblmonth.setBounds(70, 260, 100, 20);
        add(lblmonth);

        chsm = new Choice();
        chsm.setBounds(200, 260, 150, 20);
        chsm.add("January");
        chsm.add("February");
        chsm.add("March");
        chsm.add("April");
        chsm.add("May");
        chsm.add("June");
        chsm.add("July");
        chsm.add("August");
        chsm.add("September");
        chsm.add("October");
        chsm.add("November");
        chsm.add("December");

        add(chsm);

        lblunits = new JLabel("Units:");
        lblunits.setBounds(70, 320, 100, 20);
        add(lblunits);

        lblunits2 = new JLabel(" ");
        lblunits2.setBounds(200, 320, 100, 20);
        add(lblunits2);

        lbltotalbill = new JLabel("Total Bill:");
        lbltotalbill.setBounds(70, 380, 100, 20);
        add(lbltotalbill);

        lbltotalbill2 = new JLabel(" ");
        lbltotalbill2.setBounds(200, 380, 100, 20);
        add(lbltotalbill2);

        lblstatus = new JLabel("Status:");
        lblstatus.setBounds(70, 440, 100, 20);
        add(lblstatus);

        lblstatus2 = new JLabel(" ");
        lblstatus2.setBounds(200, 440, 100, 20);
        lblstatus2.setForeground(Color.RED);
        add(lblstatus2);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/paybill2.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        image = new JLabel(i3);
        image.setBounds(450, 100, 400, 400);
        add(image);
        try {
            Database c = new Database();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no='" + meter + "'");
            while (rs.next()) {
                lblmeterno2.setText(meter);
                lblname2.setText(rs.getString("name"));
            }
            rs = c.s.executeQuery("select * from bill where meter_no='" + meter + "' AND month='January'");
            while (rs.next()) {
                lblunits2.setText(rs.getString("units"));
                lbltotalbill2.setText(rs.getString("total_bill"));
                lblstatus2.setText(rs.getString("status"));

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        chsm.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                try {

                    Database c = new Database();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no='" + meter + "' AND month='" + chsm.getSelectedItem() + "'");
                    while (rs.next()) {
                        lblunits2.setText(rs.getString("units"));
                        lbltotalbill2.setText(rs.getString("total_bill"));
                        lblstatus2.setText(rs.getString("status"));

                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        });
        pay = new JButton("Pay");
        pay.setBounds(120, 500, 120, 20);
        pay.setFont(new Font("Times new roman", Font.PLAIN, 20));
        pay.setBackground(Color.green);
        pay.addActionListener(this);
        add(pay);

        cancel = new JButton("Close");
        cancel.setBounds(270, 500, 120, 20);
        cancel.setFont(new Font("Times new roman", Font.PLAIN, 20));
        cancel.setBackground(Color.red);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            try {
                Database c = new Database();
                c.s.executeUpdate("update bill set status='paid' where meter_no='" + meter + "' AND month='" + chsm.getSelectedItem() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Paytm(meter);
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PayBill("");
    }
}
