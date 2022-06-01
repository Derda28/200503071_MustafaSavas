package gui;

import Database.DbHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboPage {
    static JFrame frame = new JFrame("AboPage");
    private DbHelper dbHelper = new DbHelper();
    private static int user;

    private JPanel aboPanel;
    private JButton singleAboBtn;
    private JButton multipleAboBtn;
    private JButton expiredAboBtn;
    private JButton aboBackBtn;
    private JPanel MainPanel;

    AboPage(int user) {
        this.user = user;
        singleAboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboListPage(user,1).main(new String[]{});
                frame.dispose();
            }
        });

        multipleAboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboListPage(user,2).main(new String[]{});
                frame.dispose();
            }
        });

        expiredAboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboListPage(user,3).main(new String[]{});
                frame.dispose();
            }
        });

        aboBackBtn.addActionListener(e -> {
            new HomePage(user).main(new String[]{});
            frame.dispose();
        });
    }

    public static void main(String[] args) {
        frame.setContentPane(new AboPage(user).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
