package gui;

import Database.DbHelper;
import beans.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMenuPage {
    static JFrame frame = new JFrame("AddMenuPage");
    private static int user;
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

    AddMenuPage(int user) {
        this.user = user;
        confirmMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menuStd = new Menu();
                menuStd.setArtOfMenu("Standart");
                menuStd.setContent(contentTxt.getText());
                menuStd.setPrice(Double.parseDouble(priceTxt.getText()));
                Menu menuVeg = new Menu();
                menuVeg.setArtOfMenu("Vegetarisch");
                menuVeg.setContent(vegContentTxt.getText());
                menuVeg.setPrice(Double.parseDouble(vegPriceTxt.getText()));
                try{
                    dbHelper.open();
                    dbHelper.addMenu(menuStd, menuVeg);
                    dbHelper.close();
                    new MenuPage(user).main(new String[]{});
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
        frame.setContentPane(new AddMenuPage(user).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
