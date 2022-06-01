package gui;

import Database.DbHelper;
import beans.Abonnement;
import beans.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AboListPage {
    static JFrame frame = new JFrame("AboListPage");
    private DbHelper dbHelper = new DbHelper();
    private static int user;
    private static int aboArt;

    private JPanel aboListPanel;
    private JButton updateListBtn;
    private JButton addAboBtn;
    private JList aboList;
    private JButton aboListBackBtn;
    private JPanel MainPanel;
    private JTable aboTable;
    private JScrollPane aboTableSrollPanel;
    private JPanel BottomPanel;
    private JButton updateBtn;
    private JButton cancelBtn;

    AboListPage(int user, int aboArt) {
        reloadAboList();
        this.user = user;
        this.aboArt = aboArt;

        if(aboArt == 3) {
            cancelBtn.setVisible(false);
        }


        updateListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Update the List
            }
        });

        addAboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Customer> customers = new ArrayList<>();
                dbHelper.open();
                customers = dbHelper.getCustomer();
                dbHelper.close();
                String[] possibilities = new String[customers.size()];

                for(int i=0; i< customers.size(); i++) {
                    possibilities[i] = customers.get(i).getId()+" "+customers.get(i).getName()+" "+customers.get(i).getSurname();
                }

                String s = (String)JOptionPane.showInputDialog(
                        frame,
                        "Bitte wählen Sie den gewünschten Kunden aus...",
                        "Kunden auswählen",
                        JOptionPane.PLAIN_MESSAGE, null,
                        possibilities,
                        possibilities[0]);

                if ((s != null) && (s.length() > 0)) {
                    int index = Integer.parseInt(s.substring(0,s.indexOf(' ')));
                    new AddAboPage(user, index).main(new String[]{});
                    frame.dispose();
                }


            }
        });


        aboListBackBtn.addActionListener(e -> {
            new AboPage(user).main(new String[]{});
            frame.dispose();
        });
        updateListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadAboList();
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = aboTable.getSelectedRow();
                Abonnement customer = new Abonnement();
                customer.setArtOfAbo((String)aboTable.getValueAt(index,1));
                customer.setId((Integer) aboTable.getValueAt(index,0));
                customer.setCountOfMenus((Integer) aboTable.getValueAt(index,2));
                customer.setArtOfMenu((String)aboTable.getValueAt(index, 3));
                customer.setTotalPrice((Double) aboTable.getValueAt(index,4));
                if(aboTable.getValueAt(index,5).equals(false)){
                    customer.setHasPaid(false);
                }else{
                    customer.setHasPaid(true);
                }
                customer.setCustomerId((Integer)aboTable.getValueAt(index,6));
                customer.setStartDate((String)aboTable.getValueAt(index,7));
                customer.setDuration((String)aboTable.getValueAt(index,8));

                new UpdateAboPage(user, customer).main(new String[]{});
                frame.dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    dbHelper.open();
                    dbHelper.deleteAbo((Integer) aboTable.getValueAt(aboTable.getSelectedRow(), 0));
                    dbHelper.close();

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                reloadAboList();
            }
        });
    }

    public void reloadAboList() {
        dbHelper.open();
        ArrayList<Abonnement> abos = dbHelper.getAbos(aboArt);


        AboTableModel cTM = new AboTableModel(abos);
        aboTable.setModel(cTM);
        aboTable.setAutoCreateRowSorter(true);

        dbHelper.close();
    }

    public static void main(String[] args) {
        frame.setContentPane(new AboListPage(user, aboArt).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
