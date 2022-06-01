package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePage {
    static JFrame frame = new JFrame("HomePage");
    private static int user;
    private JPanel homePanel;
    private JButton customerBtn;
    private JButton menuBtn;
    private JButton aboBtn;
    private JButton authBtn;
    private JLabel titleLbl;
    private JPanel BtnPanel;
    private JPanel TitlePanel;

    public HomePage(int user) {
        this.user = user;

        if(user==0){
            authBtn.setVisible(false);
        }else{
            authBtn.setVisible(true);
        }
        customerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerPage(user).main(new String[]{});
                frame.dispose();
            }
        });

        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPage(user).main(new String[]{});
                frame.dispose();
            }
        });

        aboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboPage(user).main(new String[]{});
                frame.dispose();
            }
        });

        authBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AuthEmployeePage(user).main(new String[]{});
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        frame.setContentPane(new HomePage(user).homePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }


}
