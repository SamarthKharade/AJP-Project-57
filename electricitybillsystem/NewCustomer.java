package electricitybillsystem;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class NewCustomer extends JFrame implements ActionListener {

    JLabel lblName, lblMeterNo, lblMeter, lblAddress, lblCity, lblState, lblEmail, lblPhoneNo, image;
    JTextField tfName, tfAddress, tfCity, tfState, tfEmail, tfPhoneNo;
    JButton next, cancel;

    public NewCustomer() {
        setSize(700, 700);
        setLocation(400, 20);
        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(173, 216, 230));
        add(panel1, "Center");

        JLabel heading = new JLabel("New Customer");
        heading.setBounds(230, 10, 200, 30);
        heading.setForeground(Color.red);
        heading.setFont(new Font("Times new roman", Font.PLAIN, 30));
        panel1.add(heading);

        lblName = new JLabel("Customer Name:");
        lblName.setBounds(150, 100, 150, 20);
        lblName.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(330, 100, 150, 20);
        tfName.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(tfName);

        lblMeterNo = new JLabel("Meter Number:");
        lblMeterNo.setBounds(150, 150, 150, 20);
        lblMeterNo.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblMeterNo);

        lblMeter = new JLabel("");
        lblMeter.setBounds(330, 150, 150, 20);
        lblMeter.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblMeter);

        Random ran = new Random();
        long number = ran.nextLong() % 1000000;
        lblMeter.setText("" + Math.abs(number));

        lblAddress = new JLabel("Customer Address:");
        lblAddress.setBounds(150, 200, 200, 20);
        lblAddress.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setBounds(330, 200, 150, 20);
        tfAddress.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(tfAddress);

        lblCity = new JLabel("City:");
        lblCity.setBounds(150, 250, 200, 20);
        lblCity.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblCity);

        tfCity = new JTextField();
        tfCity.setBounds(330, 250, 150, 20);
        tfCity.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(tfCity);

        lblState = new JLabel("State:");
        lblState.setBounds(150, 300, 200, 20);
        lblState.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblState);

        tfState = new JTextField();
        tfState.setBounds(330, 300, 150, 20);
        tfState.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(tfState);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(150, 350, 200, 20);
        lblEmail.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(330, 350, 150, 20);
        tfEmail.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(tfEmail);

        lblPhoneNo = new JLabel("Phone Number:");
        lblPhoneNo.setBounds(150, 400, 200, 20);
        lblPhoneNo.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblPhoneNo);

        tfPhoneNo = new JTextField();
        tfPhoneNo.setBounds(330, 400, 150, 20);
        tfPhoneNo.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(tfPhoneNo);

        next = new JButton("Next");
        next.setBounds(150, 480, 120, 20);
        next.setFont(new Font("Times new roman", Font.PLAIN, 20));
        next.setBackground(Color.green);
        next.addActionListener(this);
        panel1.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(350, 480, 120, 20);
        cancel.setFont(new Font("Times new roman", Font.PLAIN, 20));
        cancel.setBackground(Color.red);
        cancel.addActionListener(this);

        panel1.add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        add(image, "East");

        getContentPane().setBackground(Color.white);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String sname = tfName.getText();
            String smeter = lblMeter.getText();
            String saddress = tfAddress.getText();
            String scity = tfCity.getText();
            String sstate = tfState.getText();
            String semail = tfEmail.getText();
            String spphoneno = tfPhoneNo.getText();

            String query1 = "insert into customer values('" + sname + "','" + smeter + "','" + saddress + "','" + scity + "','" + sstate + "','" + semail + "','" + spphoneno + "')";
            String qu = "insert into login values('" + smeter + "','','" + sname + "','','')";

            try {
               Database c = new Database();
               c.s.executeUpdate(query1);
                c.s.executeUpdate(qu);

                JOptionPane.showMessageDialog(null, "User Account Created Succesfully!!!");
                setVisible(false);
                new MeterInformation(smeter);
                //new LoginPage();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
