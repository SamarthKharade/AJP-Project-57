package electricitybillsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Paytm extends JFrame implements ActionListener {

    String meter;
    JButton close;

    Paytm(String meter) {
        this.meter = meter;
        setLayout(null);
        JEditorPane j = new JEditorPane();
        j.setEditable(false);
        try {
            j.setPage("https://paytm.com/online-payments");

        } catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not found</html>");

        }
        JScrollPane pane = new JScrollPane();
        add(pane);

        close = new JButton("Close");
        close.setBounds(500, 20, 80, 20);
        close.addActionListener(this);
        j.add(close);

        setSize(700, 600);
        setLocation(400, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new PayBill(meter);
    }

    public static void main(String[] args) {
        new Paytm("");
    }
}
