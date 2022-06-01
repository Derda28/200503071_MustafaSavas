package gui;

import Database.DbHelper;
import beans.Customer;

import javax.swing.*;

public class AddCustomerPage {
    static JFrame frame = new JFrame("AddCustomerPage");
    private DbHelper dbHelper = new DbHelper();
    private static int user;
    private Customer selectedCustomer;

    private JLabel nameLabel, surnameLabel, telephoneLabel, addressLabel, mailAddressLabel;
    private JButton confirmBtn;
    private JTextField nameTxt;
    private JTextField surnameTxt;
    private JTextField telephoneTxt;
    private JTextField addressTxt;
    private JTextField mailAdressTxt;
    private JButton addCustomerCancelBtn;
    private JPanel MainPanel, addCustomerPanel;

    public AddCustomerPage(int user) {
        this.user = user;
        confirmBtn.addActionListener(e -> {
            Customer customer = new Customer();
            customer.setName(nameTxt.getText());
            customer.setSurname(surnameTxt.getText());
            customer.setTelephone(telephoneTxt.getText());
            customer.setAddress(addressTxt.getText());
            customer.setMailAddress(mailAdressTxt.getText());
            dbHelper.open();
            dbHelper.addCustomer(customer);
            dbHelper.close();

            new CustomerPage(user).main(new String[]{});
            frame.dispose();
        });


        addCustomerCancelBtn.addActionListener(e -> {
            new CustomerPage(user).main(new String[]{});
            frame.dispose();
        });



    }

    /*public AddCustomerPage(int user, Customer customer) {
        this.user = user;
        this.selectedCustomer = customer;

        nameTxt.setText(selectedCustomer.getName());
        surnameTxt.setText(selectedCustomer.getSurname());
        telephoneTxt.setText(selectedCustomer.getTelephone());
        addressTxt.setText(selectedCustomer.getAddress());
        mailAdressTxt.setText(selectedCustomer.getMailAddress());

        confirmBtn.addActionListener(e -> {
            Customer updatedCustomer = new Customer();
            updatedCustomer.setName(nameTxt.getText());
            updatedCustomer.setSurname(surnameTxt.getText());
            updatedCustomer.setTelephone(telephoneTxt.getText());
            updatedCustomer.setAddress(addressTxt.getText());
            updatedCustomer.setMailAddress(mailAdressTxt.getText());
            dbHelper.open();
            dbHelper.updateCustomer(selectedCustomer, updatedCustomer);
            dbHelper.close();

            new CustomerPage(user).main(new String[]{});
            frame.dispose();
        });


        addCustomerCancelBtn.addActionListener(e -> {
            new CustomerPage(user).main(new String[]{});
            frame.dispose();
        });
    }*/
    public static void main(String[] args) {
        frame.setContentPane(new AddCustomerPage(user).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }
}
