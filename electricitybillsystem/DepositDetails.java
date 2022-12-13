package electricitybillsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class DepositDetails extends JFrame implements ActionListener {

    JLabel lblmno, lblsm;
    Choice chmno, chsm;
    JTable table;
    JButton search, print;

    DepositDetails() {
        
        super("Deposit Details");
       

        setSize(700, 600);
        setLocation(400, 50);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        lblmno = new JLabel("Search by meter number");
        lblmno.setBounds(20, 20, 150, 20);
        add(lblmno);

        chmno = new Choice();
        chmno.setBounds(190, 20, 150, 20);
        add(chmno);

        lblsm = new JLabel("Search by a month");
        lblsm.setBounds(400, 20, 100, 20);
        add(lblsm);

        chsm  = new Choice();
        chsm.setBounds(520, 20, 100, 20);
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

        table = new JTable();
   
        try {
           Database c = new Database();
           ResultSet rs = c.s.executeQuery("Select * from bill");
            //ResultSet rs1 = c.s.executeQuery("Select * from bill");
               // table.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {
                //chmno.add(rs.getString("meter_no"));
                
               // chmno .addItem(rs.getString("meter_no"));
               String mycat=rs.getString("meter_no");
               chmno.addItem(mycat); 
               //ResultSet rs1 = c.s.executeQuery("Select * from bill");
               //table.setModel(DbUtils.resultSetToTableModel(rs));

               select();
               
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
            
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
     

       
    

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 600);
        add(sp);

        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        setVisible(true);
    }

    
    public void select(){
            try{
                Database c=new Database();
                ResultSet rs=c.s.executeQuery("select * from bill");
                table.setModel(DbUtils.resultSetToTableModel(rs));

                
            }catch(SQLException ex)
            {
                ex.printStackTrace();
            }
                }
    
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String query = "Select * from bill where meter_no='" + chmno.getSelectedItem() + "' and month='" + chsm.getSelectedItem() + "'";
            try {
                Database c = new Database();
                ResultSet rs2 = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        DepositDetails ds=new DepositDetails();
        //ds.select();
    }

}
