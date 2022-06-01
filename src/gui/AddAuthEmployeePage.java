package gui;

import Database.DbHelper;
import beans.AuthEmployee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAuthEmployeePage {
    static JFrame frame = new JFrame("AddAuthEmployeePage");
    private static int user;
    private DbHelper dbHelper = new DbHelper();

    private JPanel addAuthEmployeePanel;
    private JLabel nameLbl;
    private JTextField authNameTxt;
    private JLabel authSurnameLbl;
    private JTextField authSurnameTxt;
    private JLabel branchcodeLbl;
    private JTextField branchcodeTxt;
    private JLabel authUsernameLbl;
    private JTextField authUsernameTxt;
    private JLabel authPasswordLbl;
    private JTextField authPasswordTxt;
    private JButton confirmAuthBtn;
    private JButton addAuthCancelBtn;
    private JPanel MainPanel;

    AddAuthEmployeePage(int user) {
        this.user = user;
        confirmAuthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthEmployee aE = new AuthEmployee();
                aE.setName(authNameTxt.getText());
                aE.setSurname(authSurnameTxt.getText());
                aE.setBranchCode(Integer.parseInt(branchcodeTxt.getText()));
                aE.setUsername(authUsernameTxt.getText());
                aE.setPassword(authPasswordTxt.getText());
                try{
                    dbHelper.open();
                    dbHelper.addAuthEmployee(aE);
                    dbHelper.close();

                    new AuthEmployeePage(user).main(new String[]{});
                    frame.dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        addAuthCancelBtn.addActionListener(e -> {
            new AuthEmployeePage(user).main(new String[]{});
            frame.dispose();
        });
    }

    public static void main(String[] args) {
        frame.setContentPane(new AddAuthEmployeePage(user).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
