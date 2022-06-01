package gui;

import Database.DbHelper;
import beans.AuthEmployee;
import beans.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateAuthPage {
    private static JFrame frame = new JFrame("UpdateAuthPage");
    private static int user;
    private static AuthEmployee aE;
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

    UpdateAuthPage(int user, AuthEmployee aE) {
        this.user = user;
        this.aE = aE;


        authNameTxt.setText(aE.getName());
        authSurnameTxt.setText(aE.getSurname());
        branchcodeTxt.setText(String.valueOf(aE.getBranchCode()));
        authUsernameTxt.setText(aE.getUsername());
        authPasswordTxt.setText(aE.getPassword());

        confirmAuthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthEmployee uAE = new AuthEmployee();
                uAE.setId(aE.getId());
                uAE.setName(authNameTxt.getText());
                uAE.setSurname(authSurnameTxt.getText());
                uAE.setBranchCode(Integer.parseInt(branchcodeTxt.getText()));
                uAE.setUsername(authUsernameTxt.getText());
                uAE.setPassword(authPasswordTxt.getText());
                try{
                    dbHelper.open();
                    dbHelper.updateAuthEmployee(uAE);
                    dbHelper.close();

                    new AuthEmployeePage(user).main(new String[]{});
                    frame.dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }



            }
        });

        addAuthCancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AuthEmployeePage(user).main(new String[]{});
                frame.dispose();
            }
        });

    }

    public static void main(String[] args) {
        frame.setContentPane(new UpdateAuthPage(user, aE).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
