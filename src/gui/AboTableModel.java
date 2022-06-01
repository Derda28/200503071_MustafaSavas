package gui;

import beans.Abonnement;
import beans.Customer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AboTableModel extends AbstractTableModel {
    private ArrayList<Abonnement> abos;
    private final String[] COLUMNS = {"Id", "ArtOfAbo", "Anzahl an Menüs", "Menüart", "Gesamtpreis", "Hat Bezahlt?", "KundenId", "Startdatum", "Dauer"};

    AboTableModel(ArrayList<Abonnement> abos) {
        this.abos = abos;
    }
    @Override
    public int getRowCount() {
        return abos.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch(columnIndex){
            case 0 -> abos.get(rowIndex).getId();
            case 1 -> abos.get(rowIndex).getArtOfAbo();
            case 2 -> abos.get(rowIndex).getCountOfMenus();
            case 3 -> abos.get(rowIndex).getArtOfMenu();
            case 4 -> abos.get(rowIndex).getTotalPrice();
            case 5 -> abos.get(rowIndex).isHasPaid();
            case 6 -> abos.get(rowIndex).getCustomerId();
            case 7 -> abos.get(rowIndex).getStartDate();
            case 8 -> abos.get(rowIndex).getDuration();
            default -> "-";
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }
}
