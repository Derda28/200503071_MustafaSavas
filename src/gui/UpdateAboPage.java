package gui;

import Database.DbHelper;
import beans.Abonnement;
import beans.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateAboPage {
    static JFrame frame = new JFrame("UpdateAboPage");
    private DbHelper dbHelper = new DbHelper();
    private static int user;
    private static Abonnement abo;

    private JTextField nameTxt;
    private JPanel MainPanel;
    private JPanel addAboPanel;
    private JComboBox aboTypComboBox;
    private JLabel aboTypLbl;
    private JLabel menuLbl;
    private JComboBox menuComboBox;
    private JLabel customerLbl;
    private JLabel menuCountLbl;
    private JTextField countTxt;
    private JLabel paidLbl;
    private JLabel durationLbl;
    private JComboBox paidComboBox;
    private JComboBox durationComboBox;
    private JLabel addAboTitleLbl;
    private JButton confirmAboBtn;
    private JButton addAboCancelBtn;
    private JLabel customerNameLabel;

    UpdateAboPage(int user, Abonnement abo) {
        this.user = user;
        this.abo = abo;

        String customerName="", customerSurname="";
        dbHelper.open();
        ResultSet rs = dbHelper.query("SELECT Name, Surname FROM customer where id="+abo.getCustomerId());
        try{
            while(rs.next()){
                customerName = rs.getString("Name");
                customerSurname = rs.getString("Surname");
            }
            customerNameLabel.setText(abo.getCustomerId()+" "+customerName+" "+customerSurname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbHelper.close();

        String[] typ = {"Einzelabonnement","Unternehmensabonnement"};
        DefaultComboBoxModel<String> dcbModel= new DefaultComboBoxModel<>(typ);
        aboTypComboBox.setModel(dcbModel);


        Menu[] menus = getMenus();
        Menu standartMenu = new Menu();
        Menu vegMenu = new Menu();
        standartMenu.setArtOfMenu(menus[0].getArtOfMenu());
        standartMenu.setContent(menus[0].getContent());
        standartMenu.setPrice(menus[0].getPrice());
        vegMenu.setPrice(menus[1].getPrice());
        vegMenu.setContent(menus[1].getContent());
        vegMenu.setArtOfMenu(menus[1].getArtOfMenu());
        String[] typ1 = {standartMenu.getArtOfMenu(),vegMenu.getArtOfMenu()};
        DefaultComboBoxModel<String> dcbModel1= new DefaultComboBoxModel<>(typ1);
        menuComboBox.setModel(dcbModel1);

        String[] typ2 = {"Ja","Nein"};
        DefaultComboBoxModel<String> dcbModel2= new DefaultComboBoxModel<>(typ2);
        paidComboBox.setModel(dcbModel2);

        String[] typ3 = {"1","2","3","4","5","6","7","8","9","10"};
        DefaultComboBoxModel<String> dcbModel3 = new DefaultComboBoxModel<>(typ3);
        durationComboBox.setModel(dcbModel3);

        countTxt.setText(String.valueOf(abo.getCountOfMenus()));
        addAboCancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomePage(user).main(new String[]{});
                frame.dispose();
            }
        });

        confirmAboBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Abonnement newAbo = new Abonnement();
                newAbo.setId(abo.getId());
                newAbo.setArtOfAbo(aboTypComboBox.getSelectedItem().toString());
                newAbo.setCountOfMenus(Integer.parseInt(countTxt.getText()));
                newAbo.setCustomerId(abo.getCustomerId());
                newAbo.setStartDate(LocalDate.now().toString());
                newAbo.setArtOfMenu(menuComboBox.getSelectedItem().toString());
                if(paidComboBox.getSelectedItem().toString().equals("Ja")){
                    newAbo.setHasPaid(true);
                }else{
                    newAbo.setHasPaid(false);
                }
                newAbo.setDuration(durationComboBox.getSelectedItem().toString()+" Jahr(e)");
                if(menuComboBox.getSelectedItem().equals("Vegetarisch")){
                    newAbo.setTotalPrice(Integer.parseInt(countTxt.getText())*menus[1].getPrice());
                }else{
                    newAbo.setTotalPrice(Integer.parseInt(countTxt.getText())*menus[0].getPrice());
                }
                try{
                    dbHelper.open();
                    dbHelper.updateAbo(newAbo,abo.getId());
                    dbHelper.close();

                    new HomePage(user).main(new String[]{});
                    frame.dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    private Menu[] getMenus() {
        Menu[] menus = new Menu[2];
        Menu menu = new Menu();
        Menu menu2 = new Menu();
        try{
            dbHelper.open();
            ResultSet rs = dbHelper.query("Select * from menu where ArtOfMenu='Standart'");
            while(rs.next()){
                menu.setArtOfMenu(rs.getString("ArtOfMenu"));
                menu.setContent(rs.getString("Content"));
                menu.setPrice(rs.getDouble("Price"));

            }
            menus[0] = menu;

            rs = dbHelper.query("Select * from menu where ArtOfMenu='Vegetarisch'");
            while(rs.next()){
                menu2.setArtOfMenu(rs.getString("ArtOfMenu"));
                menu2.setContent(rs.getString("Content"));
                menu2.setPrice(rs.getDouble("Price"));

            }
            menus[1] = menu2;
            dbHelper.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return menus;
    }

    public static void main(String[] args) {
        frame.setContentPane(new UpdateAboPage(user, abo).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
