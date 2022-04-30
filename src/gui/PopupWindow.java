package gui;
import javax.swing.*;
import java.awt.event.*;

public class PopupWindow {
    PopupWindow(){
        final JFrame f= new JFrame("PopupMenu");
        final JPopupMenu popupmenu = new JPopupMenu("Edit");
        JMenuItem cut = new JMenuItem("aktualisieren");
        JMenuItem copy = new JMenuItem("abonnement hinzufügen");
        JMenuItem paste = new JMenuItem("löschen");
        popupmenu.add(cut); popupmenu.add(copy); popupmenu.add(paste);
        f.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                popupmenu.show(f , e.getX(), e.getY());
            }
        });
        f.add(popupmenu);
        f.setSize(300,300);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main(String args[])
    {
        new PopupWindow();
    }
}
