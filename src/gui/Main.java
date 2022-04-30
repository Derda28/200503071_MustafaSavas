package gui;

import Database.DbHelper;
import beans.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main {
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JPanel homePanel;
    private JButton LOGINButton;
    private JButton HOMEButton;
    private JPanel customerPanel;
    private JPanel addCustomerPanel;
    private JPanel aboPanel;
    private JButton addCustomerBtn;
    private JList customerList;
    private JButton confirmBtn;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel telephoneLabel;
    private JLabel addressLabel;
    private JLabel mailAddressLabel;
    private JTextField nameTxt;
    private JTextField surnameTxt;
    private JTextField telephoneTxt;
    private JTextField addressTxt;
    private JTextField mailAdressTxt;
    private JButton reloadBtn;
    static JFrame frame = new JFrame("Main");

    public static void main(String[] args) {

        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Main(){
        DbHelper dbHelper = new DbHelper();
        loginPanel.setVisible(false);
        homePanel.setVisible(false);
        customerPanel.setVisible(false);
        addCustomerPanel.setVisible(true);
        aboPanel.setVisible(false);
        LOGINButton.addActionListener(e -> {
            loginPanel.setVisible(false);
            homePanel.setVisible(true);
        });
        HOMEButton.addActionListener(e -> {
            loginPanel.setVisible(true);
            homePanel.setVisible(false);
        });
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

            addCustomerPanel.setVisible(false);
            customerPanel.setVisible(true);
        });
        reloadBtn.addActionListener(e -> reloadCustomerList());
        customerList.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if (SwingUtilities.isRightMouseButton(e))
                {
                    JList list = (JList) e.getSource();

                    int preferredHeight = list.getPreferredSize().height;
                    int mouseHeight = e.getPoint().y;

                    if (mouseHeight > preferredHeight)
                        return;

                    int row = list.locationToIndex(e.getPoint());
                    list.setSelectedIndex(row);
                    // show JPopupMenu
                    final JPopupMenu popupmenu = new JPopupMenu("Edit");
                    JMenuItem cut = new JMenuItem("aktualisieren");
                    JMenuItem copy = new JMenuItem("abonnement hinzufügen");
                    JMenuItem paste = new JMenuItem("löschen");
                    popupmenu.add(cut); popupmenu.add(copy); popupmenu.add(paste);
                    popupmenu.show(frame , e.getX(), e.getY());
                    cut.addActionListener(e1 -> {
                        //UPDATE
                    });
                    copy.addActionListener(e12 -> {
                        //ADD ABO
                    });
                    paste.addActionListener(e13 -> {
                        dbHelper.open();
                        String selected = (String) list.getSelectedValue();
                        int index = Integer.parseInt(selected.substring(0,selected.indexOf(' ')));
                        dbHelper.deleteCustomer(index);
                        dbHelper.close();
                        reloadCustomerList();
                    });
                }
            }
        });
    }

    public void reloadCustomerList() {
        DbHelper dbHelper = new DbHelper();
        dbHelper.open();
        ArrayList<Customer> customer= dbHelper.getCustomer();
        String[] model = new String[customer.size()];
        int id;
        String name, surname;

        for(int i=0; i<customer.size(); i++) {
            id = customer.get(i).getId();
            name = customer.get(i).getName();
            surname = customer.get(i).getSurname();
            model[i]=id+" "+name+" "+surname;
        }



        customerList.setModel(new DefaultComboBoxModel<>(model));
        dbHelper.close();
    }
}
