package gui;

import Database.DbHelper;
import beans.Customer;

import javax.swing.*;

public class UpdateCustomerPage {
    static JFrame frame = new JFrame("UpdateCustomerPage");
    private DbHelper dbHelper = new DbHelper();
    private static int user;
    private static Customer selectedCustomer;

    private JPanel addCustomerPanel;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel telephoneLabel;
    private JLabel addressLabel;
    private JLabel mailAddressLabel;
    private JButton confirmBtn;
    private JTextField nameTxt;
    private JTextField surnameTxt;
    private JTextField telephoneTxt;
    private JTextField addressTxt;
    private JTextField mailAdressTxt;
    private JButton addCustomerCancelBtn;
    private JPanel MainPanel;

    UpdateCustomerPage(int user, Customer selectedCustomer) {
        this.user = user;
        this.selectedCustomer = selectedCustomer;

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
    }

    public static void main(String[] args) {
        frame.setContentPane(new UpdateCustomerPage(user, selectedCustomer).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
