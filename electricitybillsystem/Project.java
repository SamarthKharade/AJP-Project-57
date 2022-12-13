package electricitybillsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {

    String slogin, meter;

    public Project(String slogin, String meter) {
        this.slogin = slogin;
        this.meter = meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/electric.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        JMenu master = new JMenu("Master");
        master.setForeground(Color.BLUE);

        JMenuItem newCustomer = new JMenuItem("New Customer");
        newCustomer.setFont(new Font("Times new roman", Font.PLAIN, 12));
        newCustomer.setBackground(Color.WHITE);
        newCustomer.addActionListener(this);
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icon/i1.png"));
        Image image1 = icon1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon icon2 = new ImageIcon(image1);
        newCustomer.setIcon(icon2);
        master.add(newCustomer);

        JMenuItem customerDetails = new JMenuItem("Customer Details");
        customerDetails.setFont(new Font("Times new roman", Font.PLAIN, 12));
        customerDetails.setBackground(Color.WHITE);
        customerDetails.addActionListener(this);
        ImageIcon icon3 = new ImageIcon(ClassLoader.getSystemResource("icon/i2.jpeg"));
        Image image2 = icon3.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon icon4 = new ImageIcon(image2);
        customerDetails.setIcon(icon4);
        master.add(customerDetails);

        JMenuItem depositDetails = new JMenuItem("Deposit Details");
        depositDetails.setFont(new Font("Times new roman", Font.PLAIN, 12));
        depositDetails.setBackground(Color.WHITE);
        depositDetails.addActionListener(this);
        ImageIcon icon5 = new ImageIcon(ClassLoader.getSystemResource("icon/deposit.png"));
        Image image3 = icon5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon icon6 = new ImageIcon(image3);
        depositDetails.setIcon(icon6);
        master.add(depositDetails);

        JMenuItem billCalculate = new JMenuItem("Calculate Bill");
        billCalculate.setFont(new Font("Times new roman", Font.PLAIN, 12));
        billCalculate.setBackground(Color.WHITE);
        billCalculate.addActionListener(this);
        ImageIcon icon7 = new ImageIcon(ClassLoader.getSystemResource("icon/i5.png"));
        Image image4 = icon7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon icon8 = new ImageIcon(image4);
        billCalculate.setIcon(icon8);
        master.add(billCalculate);

        JMenu info = new JMenu("Information");
        info.setForeground(Color.BLUE);

        JMenuItem updateInfo = new JMenuItem("Update Information");
        updateInfo.setFont(new Font("Times new roman", Font.PLAIN, 12));
        updateInfo.setBackground(Color.WHITE);
        ImageIcon icon9 = new ImageIcon(ClassLoader.getSystemResource("icon/i6.png"));
        Image image5 = icon9.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon icon10 = new ImageIcon(image5);
        updateInfo.setIcon(icon10);
        updateInfo.addActionListener(this);

        info.add(updateInfo);

        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setFont(new Font("Times new roman", Font.PLAIN, 12));
        viewInfo.setBackground(Color.WHITE);
        ImageIcon icon11 = new ImageIcon(ClassLoader.getSystemResource("icon/i7.png"));
        Image image6 = icon11.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon icon12 = new ImageIcon(image6);
        viewInfo.setIcon(icon12);
        viewInfo.addActionListener(this);
        info.add(viewInfo);

        JMenu user = new JMenu("User");
        user.setForeground(Color.BLUE);

        JMenuItem payBill = new JMenuItem("Pay Bill");
        payBill.setFont(new Font("Times new roman", Font.PLAIN, 12));
        payBill.setBackground(Color.WHITE);
        ImageIcon icon13 = new ImageIcon(ClassLoader.getSystemResource("icon/i8.png"));
        Image image7 = icon13.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon icon14 = new ImageIcon(image7);
        payBill.setIcon(icon14);
        payBill.addActionListener(this);
        user.add(payBill);

        JMenuItem billDetails = new JMenuItem("Bill Details");
        billDetails.setFont(new Font("Times new roman", Font.PLAIN, 12));
        billDetails.setBackground(Color.WHITE);
        ImageIcon icon15 = new ImageIcon(ClassLoader.getSystemResource("icon/i9.png"));
        Image image8 = icon15.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon icon16 = new ImageIcon(image8);
        billDetails.setIcon(icon16);
        billDetails.addActionListener(this);
        user.add(billDetails);

        JMenu report = new JMenu("Report");
        report.setForeground(Color.BLUE);

        JMenuItem generateBill = new JMenuItem("Generate Bill");
        generateBill.setFont(new Font("Times new roman", Font.PLAIN, 12));
        generateBill.setBackground(Color.WHITE);
        ImageIcon icon17 = new ImageIcon(ClassLoader.getSystemResource("icon/i10.png"));
        Image image9 = icon17.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon icon18 = new ImageIcon(image9);
        generateBill.setIcon(icon18);
        generateBill.addActionListener(this);
        report.add(generateBill);

        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLUE);

        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("Times new roman", Font.PLAIN, 12));
        notepad.setBackground(Color.WHITE);
        ImageIcon icon19 = new ImageIcon(ClassLoader.getSystemResource("icon/i11.jpeg"));
        Image image10 = icon19.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon icon20 = new ImageIcon(image10);
        notepad.setIcon(icon20);
        notepad.addActionListener(this);
        utility.add(notepad);

        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("Times new roman", Font.PLAIN, 12));
        calculator.setBackground(Color.WHITE);
        ImageIcon icon21 = new ImageIcon(ClassLoader.getSystemResource("icon/i12.png"));
        Image image11 = icon21.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon icon22 = new ImageIcon(image11);
        calculator.setIcon(icon22);
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenu close = new JMenu("Close");
        close.setForeground(Color.BLUE);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(new Font("Times new roman", Font.PLAIN, 12));
        exit.setBackground(Color.WHITE);
        ImageIcon icon23 = new ImageIcon(ClassLoader.getSystemResource("icon/i13.jpeg"));
        Image image12 = icon23.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon icon24 = new ImageIcon(image12);
        exit.setIcon(icon24);
        exit.addActionListener(this);
        close.add(exit);

        if (slogin.equals("Admin")) {
            mb.add(master);
        } else {
            mb.add(info);
            mb.add(user);
            mb.add(report);
        }
        mb.add(utility);
        mb.add(close);
        this.setLayout(new FlowLayout());
        setTitle("Welcome to MSCEB Poratl");

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        if (msg.equals("New Customer")) {
            new NewCustomer();
        } else if (msg.equals("Customer Details")) {
            new CustomerDetails();
        } else if (msg.equals("Deposit Details")) {
            new DepositDetails();
        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill();
        } else if (msg.equals("View Information")) {
            new ViewInformation(meter);
        } else if (msg.equals("Update Information")) {
            new UpdateInformation(meter);
        } else if (msg.equals("Bill Details")) {
            new BillDetails(meter);
        } else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Exit")) {
            setVisible(false);
            new LoginPage();
        } else if (msg.equals("Pay Bill")) {
            new PayBill(meter);
        } else if (msg.equals("Generate Bill")) {
            new GenerateBill(meter);
        }

    }

    public static void main(String[] args) {
        new Project("", "");
    }

}
