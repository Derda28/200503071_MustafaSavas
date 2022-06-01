package gui;

import beans.AuthEmployee;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AuthTableModel extends AbstractTableModel {
    private ArrayList<AuthEmployee> auth;
    private final String[] COLUMNS = {"Id", "Name", "Nachname", "Filialcode", "Benutzername"};

    AuthTableModel(ArrayList<AuthEmployee> auth) {
        this.auth = auth;
    }
    @Override
    public int getRowCount() {
        return auth.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch(columnIndex){
            case 0 -> auth.get(rowIndex).getId();
            case 1 -> auth.get(rowIndex).getName();
            case 2 -> auth.get(rowIndex).getSurname();
            case 3 -> auth.get(rowIndex).getBranchCode();
            case 4 -> auth.get(rowIndex).getUsername();
            default -> "-";
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }
}
