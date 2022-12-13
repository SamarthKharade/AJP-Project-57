package electricitybillsystem;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class MeterInformation extends JFrame implements ActionListener {

    JLabel lblName, lblMeter, image, lblMeterNumber, lblMeterLocation, lblMeterType, lblPhaseCode, lblBillType, lblDays, lblDays1, lblNote, lblNote1;
    JTextField tfName;
    Choice meterlocation, tfmeterType, tfphasecode, tfBillType;
    JButton next;
    String meternumber;

    public MeterInformation(String smeter) {
        this.meternumber = smeter;
        setSize(800, 600);
        setLocation(400, 50);
        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(Color.pink);
        add(panel1, "Center");

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(230, 10, 300, 30);
        heading.setForeground(Color.red);
        heading.setFont(new Font("Times new roman", Font.PLAIN, 30));
        panel1.add(heading);

        lblName = new JLabel("Meter Number:");
        lblName.setBounds(150, 100, 150, 20);
        lblName.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblName);

        lblMeterNumber = new JLabel(meternumber);
        lblMeterNumber.setBounds(330, 100, 150, 20);
        lblMeterNumber.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblMeterNumber);

        lblMeterLocation = new JLabel("Meter Location:");
        lblMeterLocation.setBounds(150, 150, 150, 20);
        lblMeterLocation.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblMeterLocation);

        meterlocation = new Choice();
        meterlocation.addItem("Inside");
        meterlocation.addItem("Outside");
        meterlocation.setBounds(330, 150, 200, 20);
        panel1.add(meterlocation);

        lblMeterType = new JLabel("Meter Type:");
        lblMeterType.setBounds(150, 200, 150, 20);
        lblMeterType.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblMeterType);

        tfmeterType = new Choice();
        tfmeterType.addItem("Electric Meter");
        tfmeterType.addItem("Solar Meter");
        tfmeterType.addItem("Smart Meter");
        tfmeterType.setBounds(330, 200, 200, 20);
        panel1.add(tfmeterType);

        lblPhaseCode = new JLabel("Phase Code:");
        lblPhaseCode.setBounds(150, 250, 150, 20);
        lblPhaseCode.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblPhaseCode);

        tfphasecode = new Choice();
        tfphasecode.addItem("011");
        tfphasecode.addItem("022");
        tfphasecode.addItem("033");
        tfphasecode.addItem("044");
        tfphasecode.addItem("055");
        tfphasecode.addItem("066");
        tfphasecode.addItem("077");
        tfphasecode.addItem("088");
        tfphasecode.addItem("099");

        tfphasecode.setBounds(330, 250, 200, 20);
        panel1.add(tfphasecode);

        lblBillType = new JLabel("Bill Type:");
        lblBillType.setBounds(150, 300, 150, 20);
        lblBillType.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblBillType);

        tfBillType = new Choice();
        tfBillType.addItem("Normal");
        tfBillType.addItem("Industial");
        tfBillType.setBounds(330, 300, 200, 20);
        panel1.add(tfBillType);

        lblDays = new JLabel("Days:");
        lblDays.setBounds(150, 350, 200, 20);
        lblDays.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblDays);

        lblDays1 = new JLabel("30 Days");
        lblDays1.setBounds(330, 350, 200, 20);
        lblDays1.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblDays1);

        lblNote = new JLabel("Note:");
        lblNote.setBounds(150, 400, 50, 20);
        lblNote.setFont(new Font("Times new roman", Font.BOLD, 20));
        panel1.add(lblNote);

        lblNote1 = new JLabel("Bill will be calculated after 30 days!!!");
        lblNote1.setBounds(210, 400, 350, 20);
        lblNote1.setFont(new Font("Times new roman", Font.PLAIN, 20));
        panel1.add(lblNote1);

        next = new JButton("Submit");
        next.setBounds(250, 450, 120, 20);
        next.setFont(new Font("Times new roman", Font.PLAIN, 20));
        next.setBackground(Color.green);
        next.addActionListener(this);
        panel1.add(next);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/meter.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        add(image, "East");

        getContentPane().setBackground(Color.white);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String meterno1 = meternumber;
            //String meterno = lblMeterNumber;
            String slocation = meterlocation.getSelectedItem();
            String smetertype = tfmeterType.getSelectedItem();
            String sphase = tfphasecode.getSelectedItem();
            String sbilltype = tfBillType.getSelectedItem();
            String days = "30";

            String query3 = "insert into meterinfo values('" + meterno1 + "','" + slocation + "','" + smetertype + "','" + sphase + "','" + sbilltype + "','" + days + "')";

            try {
                //  String query3="insert into meterinformation values('"+meterno+"','"+slocation+"','"+smetertype+"','"+sphase+"','"+sbilltype+"','"+days+"')";     
                 Database c = new Database();
                c.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "Meter Information Added Succesfully!!!");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new MeterInformation("");
    }
}
