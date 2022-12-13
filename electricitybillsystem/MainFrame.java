package electricitybillsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements Runnable {

    Thread t;

    public MainFrame() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/images.png"));
        Image i2 = i1.getImage().getScaledInstance(550, 593, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i1);
        add(image);
        setVisible(true);
        for (int i = 1; i <= 593; i++) {
            setSize(i, i);
            setLocation(400, 0);

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        t = new Thread(this);
        t.start();
        setVisible(true);
    }

    public void run() {
        try {
            Thread.sleep(5000);
            setVisible(false);
            new LoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
