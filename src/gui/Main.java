package gui;

import Database.DbHelper;
import beans.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JButton LOGINButton;
    private JButton HOMEButton;
    private JButton authEMployeeBtn;
    private JButton adminEmployeeBtn;
    private JLabel usernameLbl;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JTextField usernameTxt;
    private JButton loginBtn;
    private JPasswordField idTxt;
    private JLabel idLbl;
    private JPanel BtnPanel;
    private JButton testBtn;
    static JFrame frame = new JFrame("Main");

    private int user = 0;

    public static void main(String[] args) {

        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public Main(){
        loginPage();
    }


/*    private void setVisibility(int which){
        loginPanel.setVisible(false);
        homePanel.setVisible(false);
        customerPanel.setVisible(false);
        addCustomerPanel.setVisible(false);
        aboPanel.setVisible(false);
        aboListPanel.setVisible(false);
        addAboPanel.setVisible(false);
        menuPanel.setVisible(false);
        addMenuPanel.setVisible(false);
        authEmployeePanel.setVisible(false);
        addAuthEmployeePanel.setVisible(false);
        switch(which){
            case 1:
                loginPanel.setVisible(true);
                break;
            case 2:
                homePanel.setVisible(true);
                break;
            case 3:
                customerPanel.setVisible(true);
                break;
            case 4:
                addCustomerPanel.setVisible(true);
                break;
            case 5:
                aboPanel.setVisible(true);
                break;
            case 6:
                aboListPanel.setVisible(true);
                break;
            case 7:
                addAboPanel.setVisible(true);
                break;
            case 8:
                menuPanel.setVisible(true);
                break;
            case 9:
                addMenuPanel.setVisible(true);
                break;
            case 10:
                authEmployeePanel.setVisible(true);
                break;
            case 11:
                addAuthEmployeePanel.setVisible(true);
                break;
        }
    }

    private void addAuthEmployeePage() {
        setVisibility(11);
        confirmAuthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SAVE DATA
                authEmployeePage();
            }
        });

        addAuthCancelBtn.addActionListener(e -> {
            authEmployeePage();
        });
    }

    private void authEmployeePage() {
        DbHelper dbHelper = new DbHelper();
        setVisibility(10);
        uodateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAuthEmployeePage();
            }
        });

        addAuthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAuthEmployeePage();
            }
        });

        authEmployeeList.addMouseListener(new MouseAdapter()
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
                        addCustomerPage();
                    });
                    copy.addActionListener(e12 -> {
                        //ADD ABO
                        addAboPage();
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

        authBackBtn.addActionListener(e -> {
            homePage();
        });
    }

    private void addMenuPage() {
        setVisibility(9);
        confirmMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SAVE DATA
                menuPage();
            }
        });

        addMenuCancelBtn.addActionListener(e -> {
            menuPage();
        });
    }

    private void menuPage() {
        setVisibility(8);
        addMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMenuPage();
            }
        });

        updateMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMenuPage();
            }
        });

        menuBackBtn.addActionListener(e -> {
            homePage();
        });
    }

    private void addAboPage() {
        setVisibility(7);
        confirmAboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SAVE DATA
                aboListPage();
            }
        });

        addAboCancelBtn.addActionListener(e -> {
            aboListPage();
        });
    }

    private void aboListPage() {
        DbHelper dbHelper = new DbHelper();
        setVisibility(6);
        updateListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        addAboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        aboList.addMouseListener(new MouseAdapter()
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
                        addCustomerPage();
                    });
                    copy.addActionListener(e12 -> {
                        //ADD ABO
                        addAboPage();
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

        aboListBackBtn.addActionListener(e -> {
            aboPage();
        });
    }

    private void aboPage() {
        setVisibility(5);
        singleAboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboListPage();
            }
        });

        multipleAboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboListPage();
            }
        });

        expiredAboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboListPage();
            }
        });

        aboBackBtn.addActionListener(e -> {
            homePage();
        });
    }

    private void addCustomerPage() {
        DbHelper dbHelper = new DbHelper();
        setVisibility(4);
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

            customerPage();
        });


        addCustomerCancelBtn.addActionListener(e -> {
            customerPage();
        });
    }

    private void customerPage() {
        DbHelper dbHelper = new DbHelper();
        setVisibility(3);
        reloadBtn.addActionListener(e -> reloadCustomerList());

        addCustomerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomerPage();
            }
        });

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
                        addCustomerPage();
                    });
                    copy.addActionListener(e12 -> {
                        //ADD ABO
                        addAboPage();
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

        customerBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePage();
            }
        });
    }

    private void homePage() {
        setVisibility(2);
        customerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerPage();
            }
        });

        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPage();
            }
        });

        aboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aboPage();
            }
        });

        authBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authEmployeePage();
            }
        });
    }*/

    private void loginPage() {
        DbHelper dbHelper = new DbHelper();
        authEmployee();

        authEMployeeBtn.addActionListener(e -> {
            authEmployee();
        });

        adminEmployeeBtn.addActionListener(e -> {
            adminEmployee();
        });

        loginBtn.addActionListener(e -> {
            //check IF login is succesfull???
            //if TRUE

            try {
                if(user==1) {
                    dbHelper.open();
                    if(dbHelper.isLoginSuccessfulAdmin(Integer.parseInt(idTxt.getText()))){
                        new HomePage(user).main(new String[]{});
                        frame.dispose();
                    }else{
                        System.out.println("falsche ID");
                    }
                }else{
                    dbHelper.open();
                    if(dbHelper.isLoginSuccessfulAuth(usernameTxt.getText(), passwordField.getText())){
                        new HomePage(user).main(new String[]{});
                        frame.dispose();
                    }else{
                        System.out.println("wrong pass or username");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            dbHelper.close();
        });
    }

    public void authEmployee() {
        user=0;
        idLbl.setVisible(false);
        idTxt.setVisible(false);
        usernameLbl.setVisible(true);
        passwordField.setVisible(true);
        passwordLabel.setVisible(true);
        usernameTxt.setVisible(true);
        authEMployeeBtn.setBackground(Color.getHSBColor(6,82,92));
        adminEmployeeBtn.setBackground(Color.getHSBColor(6,100,92));
    }

    public void adminEmployee() {
        user=1;
        usernameLbl.setVisible(false);
        passwordField.setVisible(false);
        passwordLabel.setVisible(false);
        usernameTxt.setVisible(false);
        idLbl.setVisible(true);
        idTxt.setVisible(true);
        adminEmployeeBtn.setBackground(Color.getHSBColor(6,82,92));
        authEMployeeBtn.setBackground(Color.getHSBColor(6,100,92));
    }

    /*public void reloadCustomerList() {
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
    }*/
}
