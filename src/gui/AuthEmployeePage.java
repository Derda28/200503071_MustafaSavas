package gui;

import Database.DbHelper;
import beans.AuthEmployee;
import beans.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AuthEmployeePage {
    static JFrame frame = new JFrame("AuthEmployeePage");
    private DbHelper dbHelper = new DbHelper();
    private static int user;

    private JPanel authEmployeePanel;
    private JButton uodateBtn;
    private JButton addAuthBtn;
    private JList authEmployeeList;
    private JButton authBackBtn;
    private JPanel MainPanel;
    private JTable authTable;
    private JScrollPane authTableScrollPanel;
    private JPanel BottomPanel;
    private JButton updateAuthBtn;
    private JButton deleteAuthBtn;

    AuthEmployeePage(int user) {
        this.user = user;

        reloadAuthList();

        uodateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadAuthList();
            }
        });

        addAuthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAuthEmployeePage(user).main(new String[]{});
                frame.dispose();
            }
        });


        authBackBtn.addActionListener(e -> {
            new HomePage(user).main(new String[]{});
            frame.dispose();
        });

        updateAuthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = authTable.getSelectedRow();
                AuthEmployee customer = new AuthEmployee();
                customer.setName((String)authTable.getValueAt(index,1));
                customer.setId((Integer) authTable.getValueAt(index,0));
                customer.setSurname((String)authTable.getValueAt(index,2));
                customer.setBranchCode((Integer) authTable.getValueAt(index,3));
                customer.setUsername((String) authTable.getValueAt(index,4));
                customer.setPassword("");

                new UpdateAuthPage(user, customer).main(new String[]{});
                frame.dispose();
            }
        });

        deleteAuthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbHelper.open();
                dbHelper.deleteAuthEmployee((Integer)authTable.getValueAt(authTable.getSelectedRow(), 0));
                dbHelper.close();

                reloadAuthList();
            }
        });
    }

    public void reloadAuthList() {
        dbHelper.open();
        ArrayList<AuthEmployee> auth = dbHelper.getAuthEmployees();

        AuthTableModel cTM = new AuthTableModel(auth);
        authTable.setModel(cTM);
        authTable.setAutoCreateRowSorter(true);

        dbHelper.close();
    }

    public static void main(String[] args) {
        frame.setContentPane(new AuthEmployeePage(user).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
