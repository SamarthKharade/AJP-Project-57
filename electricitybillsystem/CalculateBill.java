package electricitybillsystem;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener {

    JLabel lblMeterNo, lblName, lblAddress, lblUC, lblMonth, lblName2, lblAddress2, image;
    JTextField tfUC, tfMonth;
    JButton next, cancel;
    Choice meterno, cMonth;

    public CalculateBill() {
        setSize(700, 500);
        setLocation(400, 100);
        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(Color.LIGHT_GRAY);
        //panel1.setBackground(new Color(173,216,230));
        add(panel1, "Center");

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(150, 10, 350, 30);
        heading.setForeground(Color.RED);
        heading.setFont(new Font("Times new roman", Font.PLAIN, 30));
        panel1.add(heading);

        lblMeterNo = new JLabel("Meter Number:");
        lblMeterNo.setBounds(150, 100, 150, 20);
        lblMeterNo.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblMeterNo);

        meterno = new Choice();

        try {
            Database c = new Database();
            ResultSet rs = c.s.executeQuery("Select * from customer");
            while (rs.next()) {
                meterno.add(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        meterno.setBounds(330, 100, 150, 20);
        meterno.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(meterno);

        lblName = new JLabel("Name:");
        lblName.setBounds(150, 150, 150, 20);
        lblName.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblName);

        lblName2 = new JLabel();
        lblName2.setBounds(330, 150, 200, 20);
        lblName2.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblName2);

        lblAddress = new JLabel("Address:");
        lblAddress.setBounds(150, 200, 200, 20);
        lblAddress.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblAddress);

        lblAddress2 = new JLabel();
        lblAddress2.setBounds(330, 200, 150, 20);
        lblAddress2.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblAddress2);

        try {
           Database c = new Database();
            ResultSet rs = c.s.executeQuery("Select * from customer where meter_no='" + meterno.getSelectedItem() + "'");
            while (rs.next()) {
                lblName2.setText(rs.getString("name"));
                lblAddress2.setText(rs.getString("address"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        meterno.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Database c = new Database();
                    ResultSet rs = c.s.executeQuery("Select * from customer where meter_no='" + meterno.getSelectedItem() + "'");
                    while (rs.next()) {
                        lblName2.setText(rs.getString("name"));
                        lblAddress2.setText(rs.getString("address"));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        lblUC = new JLabel("Units Consume:");
        lblUC.setBounds(150, 250, 200, 20);
        lblUC.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblUC);

        tfUC = new JTextField();
        tfUC.setBounds(330, 250, 150, 20);
        tfUC.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(tfUC);

        lblMonth = new JLabel("Month:");
        lblMonth.setBounds(150, 300, 150, 20);
        lblMonth.setFont(new Font("Times new roman", Font.PLAIN, 20));

        panel1.add(lblMonth);

        cMonth = new Choice();
        cMonth.setBounds(330, 300, 150, 20);
        //cMonth.setFont(new Font("Times new roman",Font.PLAIN,20));
        cMonth.add("January");
        cMonth.add("February");
        cMonth.add("March");
        cMonth.add("April");
        cMonth.add("May");
        cMonth.add("June");
        cMonth.add("July");
        cMonth.add("August");
        cMonth.add("September");
        cMonth.add("October");
        cMonth.add("November");
        cMonth.add("December");
        panel1.add(cMonth);

        next = new JButton("Submit");
        next.setBounds(150, 380, 120, 20);
        next.setFont(new Font("Times new roman", Font.PLAIN, 20));
        next.setBackground(Color.green);
        next.addActionListener(this);
        panel1.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(350, 380, 120, 20);
        cancel.setFont(new Font("Times new roman", Font.PLAIN, 20));
        cancel.setBackground(Color.red);
        cancel.addActionListener(this);

        panel1.add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/calculatebill.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        add(image, "East");

        getContentPane().setBackground(Color.white);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String smeter = meterno.getSelectedItem();
            String sunits = tfUC.getText();
            String smonth = cMonth.getSelectedItem();
            int totalBill = 0;
            int unitConsumed = Integer.parseInt(sunits);
            String query = "Select * from tax";

            // String query1="insert into customer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+semail+"','"+spphoneno+"')";
            //String qu="insert into login values('"+smeter+"','','"+sname+"','','')";
            try {
                 Database c = new Database();
                ResultSet rs = c.s.executeQuery(query);

                while (rs.next()) {
                    totalBill += unitConsumed * Integer.parseInt(rs.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(rs.getString("meter_rent"));
                    totalBill += Integer.parseInt(rs.getString("service_charge"));
                    totalBill += Integer.parseInt(rs.getString("service_tax"));
                    totalBill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    totalBill += Integer.parseInt(rs.getString("fixed_tax"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            String query2 = "insert into bill values('" + smeter + "','" + smonth + "','" + sunits + "','" + totalBill + "','Not Paid')";

            try {
                Database c = new Database();
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Customer Bill Calculated Succesfully!!!");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new CalculateBill();
    }
}
