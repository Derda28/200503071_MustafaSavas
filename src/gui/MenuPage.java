package gui;

import Database.DbHelper;
import beans.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuPage {
    static JFrame frame = new JFrame("MenuPage");
    private static int user;
    private DbHelper dbHelper = new DbHelper();

    private JPanel menuPanel;
    private JLabel menuTitleLbl;
    private JButton addMenuBtn;
    private JButton updateMenuBtn;
    private JButton menuBackBtn;
    private JPanel MainPanel;

    MenuPage(int user) {
        this.user = user;

        Menu[] menus = getMenus();

        if(menus!=null){
            addMenuBtn.setVisible(false);
            updateMenuBtn.setVisible(true);
        }else{
            addMenuBtn.setVisible(true);
            updateMenuBtn.setVisible(false);
        }
        addMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMenuPage(user).main(new String[]{});
                frame.dispose();
            }
        });

        updateMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new UpdateMenuPage(user, menus[0], menus[1]).main(new String[]{});
                frame.dispose();
            }
        });

        menuBackBtn.addActionListener(e -> {
            new HomePage(user).main(new String[]{});
            frame.dispose();
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
        frame.setContentPane(new MenuPage(user).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
