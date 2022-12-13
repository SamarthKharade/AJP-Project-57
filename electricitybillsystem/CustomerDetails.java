package electricitybillsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class CustomerDetails extends JFrame implements ActionListener {

    JLabel lblmno, lblsm;
    Choice chmno, chsm;
    JTable table;
    JButton search, print;

    CustomerDetails() {
        super("Customer Details");

        setSize(1000, 550);
        setLocation(200, 150);

        table = new JTable();

        try {
            Database c = new Database();
            ResultSet rs = c.s.executeQuery("Select * from customer");

            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        add(sp);

        print = new JButton("Print");
        print.addActionListener(this);
        add(print, "South");

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        try {
            table.print();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new CustomerDetails();
    }

}
