package gui;

import Database.DbHelper;
import beans.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CustomerPage {
    static JFrame frame = new JFrame("CustomerPage");
    private DbHelper dbHelper = new DbHelper();
    private static int user;

    private JPanel customerPanel;
    private JButton addCustomerBtn;
    private JButton reloadBtn;
    private JButton customerBackBtn;
    private JPanel MainPanel;
    private JTable customerTable;
    private JScrollPane tableScrollPanel;
    private JPanel actionPanel;
    private JButton updateCustomerBtn;
    private JButton deleteCustomerBtn;
    private JButton addAboBtn;

    CustomerPage(int user){
        this.user = user;
        reloadCustomerList();
        addCustomerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCustomerPage(user).main(new String[]{});
                frame.dispose();
            }
        });

        reloadBtn.addActionListener(e -> reloadCustomerList());


        customerBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage(user).main(new String[]{});
                frame.dispose();
            }
        });
        updateCustomerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = customerTable.getSelectedRow();
                Customer customer = new Customer();
                customer.setName((String)customerTable.getValueAt(index,1));
                customer.setId((Integer) customerTable.getValueAt(index,0));
                customer.setSurname((String)customerTable.getValueAt(index,2));
                customer.setTelephone((String)customerTable.getValueAt(index,4));
                customer.setAbonnementId((Integer) customerTable.getValueAt(index,3));
                customer.setAddress((String)customerTable.getValueAt(index,5));
                customer.setMailAddress((String)customerTable.getValueAt(index,6));

                new UpdateCustomerPage(user, customer).main(new String[]{});
                frame.dispose();
            }
        });
        addAboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((Integer)customerTable.getValueAt(customerTable.getSelectedRow(),3)==0){
                    int index = (Integer) customerTable.getValueAt(customerTable.getSelectedRow(),0);
                    new AddAboPage(user, index).main(new String[]{});
                    frame.dispose();
                }

            }
        });
        deleteCustomerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbHelper.open();
                dbHelper.deleteCustomer((Integer) customerTable.getValueAt(customerTable.getSelectedRow(),0));
                dbHelper.close();
                reloadCustomerList();
            }
        });

    }
    public static void main(String[] args) {
        frame.setContentPane(new CustomerPage(user).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public void reloadCustomerList() {
        dbHelper.open();
        ArrayList<Customer> customer = dbHelper.getCustomer();
        /*String[][] model = new String[customer.size()][7];
        int id, abonnementId;
        String name, surname, telephone, address, mailAddress;


        for (int i = 0; i < customer.size(); i++) {
            id = customer.get(i).getId();
            name = customer.get(i).getName();
            surname = customer.get(i).getSurname();
            abonnementId = customer.get(i).getAbonnementId();
            telephone = customer.get(i).getTelephone();
            address = customer.get(i).getAddress();
            mailAddress = customer.get(i).getMailAddress();

            model[i][0] = String.valueOf(id);
            model[i][1] = name;
            model[i][2] = surname;
            model[i][3] = String.valueOf(abonnementId);
            model[i][4] = telephone;
            model[i][5] = address;
            model[i][6] = mailAddress;
        }*/

        CustomerTableModel cTM = new CustomerTableModel(customer);
        customerTable.setModel(cTM);
        customerTable.setAutoCreateRowSorter(true);

        dbHelper.close();
    }

}
