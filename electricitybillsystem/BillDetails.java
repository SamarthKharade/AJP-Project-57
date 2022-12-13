package electricitybillsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame {

    String meter;

    BillDetails(String meter) {
        this.meter = meter;
        setSize(700, 600);
        setLocation(400, 100);

        getContentPane().setBackground(Color.WHITE);
        JTable table = new JTable();
        try {
            Database c = new Database();
            String query = "select * from bill where meter_no='" + meter + "'";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {

            e.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 700, 600);
        add(sp);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BillDetails("");
    }

}
