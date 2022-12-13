package electricitybillsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class GenerateBill extends JFrame implements ActionListener {

    Choice chsm;
    JLabel heading, meterno;
    JTextArea area;
    JButton bill, print1;
    String meter;

    GenerateBill(String meter) {
        this.meter = meter;
        setSize(500, 700);
        setLocation(400, 10);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        heading = new JLabel("Generate Bill");
        meterno = new JLabel(meter);
        chsm = new Choice();

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

        area = new JTextArea(50, 15);
        area.setText("\n\n\t---------Click on the-----------\n\t Generate Bill Button to get\n\t the bill of selected Month");
        area.setFont(new Font("Times new roman", Font.PLAIN, 20));
        JScrollPane pane = new JScrollPane(area);

        bill = new JButton("Generate Bill");
        bill.addActionListener(this);

        print1 = new JButton("Print");
        print1.addActionListener(this);

        panel.add(heading);
        panel.add(meterno);
        panel.add(chsm);
        panel.add(print1);
        add(panel, "North");

        add(pane, "Center");
        add(bill, "South");

        print1 = new JButton("Print");
        print1.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bill) {
            try {
                Database c = new Database();

                String month = chsm.getSelectedItem();
                area.setText("\t\nElectricity Bill Generated for the month\n\t of " + month + ", 2022\n\n\n");
                ResultSet rs = c.s.executeQuery("select * from customer where meter_no='" + meter + "'");
                if (rs.next()) {
                    area.append("\n     Customer Name :" + rs.getString("name"));
                    area.append("\n     Meter Number   :" + rs.getString("meter_no"));
                    area.append("\n     Address            :" + rs.getString("address"));
                    area.append("\n     City                  :" + rs.getString("city"));
                    area.append("\n     State                 :" + rs.getString("state"));
                    area.append("\n     Email                :" + rs.getString("email"));
                    area.append("\n     Phone Number   :" + rs.getString("phone"));

                }
                rs = c.s.executeQuery("select * from meterinfo where meternumber='" + meter + "'");
                if (rs.next()) {
                    area.append("\n--------------------------------------------------------------");

                    area.append("\n\n     Meter Location :" + rs.getString("meterlocation"));
                    area.append("\n      Meter Type     :" + rs.getString("metertype"));
                    area.append("\n      Phase Code     :" + rs.getString("phasecode"));
                    area.append("\n      Bill Type        :" + rs.getString("billtype"));
                    area.append("\n      Days               :" + rs.getString("days"));
                    area.append("\n--------------------------------------------------------------");

                }

                rs = c.s.executeQuery("select * from tax");
                if (rs.next()) {

                    area.append("\n\n     Cost Per Unit              :" + rs.getString("cost_per_unit"));
                    area.append("\n      Meter Rent                 :" + rs.getString("meter_rent"));
                    area.append("\n      Service Charge           :" + rs.getString("service_charge"));
                    area.append("\n      Service Tax                :" + rs.getString("service_tax"));
                    area.append("\n      Swacch Bharat Cess   :" + rs.getString("swacch_bharat_cess"));
                    area.append("\n      Fixed Tax                  :" + rs.getString("fixed_tax"));
                    area.append("\n--------------------------------------------------------------");

                }

                rs = c.s.executeQuery("select * from bill where meter_no='" + meter + "' and month='" + month + "'");
                if (rs.next()) {

                    area.append("\n\n     Current Month         :" + rs.getString("month"));
                    area.append("\n      Units Consumed      :" + rs.getString("units"));
                    area.append("\n      Total Charges         :" + rs.getString("total_bill"));
                    area.append("\n--------------------------------------------------------------");
                    area.append("\n      Total Amount         :" + rs.getString("total_bill"));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                area.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}
