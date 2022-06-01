package gui;

import Database.DbHelper;
import beans.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateMenuPage {
    private static JFrame frame = new JFrame("UpdateMenuPage");
    private static int user;
    private static Menu menuStd;
    private static Menu menuVeg;
    private DbHelper dbHelper = new DbHelper();

    private JPanel addMenuPanel;
    private JLabel addMenuTitleLbl;
    private JLabel standartMenuLbl;
    private JLabel vegMenuLbl;
    private JLabel contentLbl;
    private JTextField contentTxt;
    private JLabel priceLbl;
    private JTextField priceTxt;
    private JLabel vegContentLbl;
    private JLabel vegPriceLbl;
    private JTextField vegContentTxt;
    private JTextField vegPriceTxt;
    private JButton confirmMenuBtn;
    private JButton addMenuCancelBtn;
    private JPanel MainPanel;

    UpdateMenuPage(int user, Menu menuStd, Menu menuVeg) {
        this.user = user;
        this.menuVeg = menuVeg;
        this.menuStd = menuStd;


        contentTxt.setText(menuStd.getContent());
        priceTxt.setText(String.valueOf(menuStd.getPrice()));
        vegContentTxt.setText(menuVeg.getContent());
        vegPriceTxt.setText(String.valueOf(menuVeg.getPrice()));


        confirmMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                menu.setContent(contentTxt.getText());
                menu.setPrice(Double.parseDouble(priceTxt.getText()));
                menu.setArtOfMenu("Standart");
                Menu menuVeg = new Menu();
                menuVeg.setContent(vegContentTxt.getText());
                menuVeg.setPrice(Double.parseDouble(vegPriceTxt.getText()));
                menuVeg.setArtOfMenu("Vegetarisch");
                try{
                    dbHelper.open();
                    dbHelper.updateMenu(menu, menuVeg);
                    dbHelper.close();

                    new HomePage(user).main(new String[]{});
                    frame.dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        addMenuCancelBtn.addActionListener(e -> {
            new MenuPage(user).main(new String[]{});
            frame.dispose();
        });
    }

    public static void main(String[] args) {
        frame.setContentPane(new UpdateMenuPage(user, menuStd, menuVeg).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
