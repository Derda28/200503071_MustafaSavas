package gui;

import beans.Customer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CustomerTableModel extends AbstractTableModel {

    private ArrayList<Customer> customers;
    private final String[] COLUMNS = {"Id", "Name", "Nachname", "AboId", "Telefonnr", "Adresse", "E-Mail"};

    CustomerTableModel(ArrayList<Customer> customers) {
        this.customers = customers;
    }
    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch(columnIndex){
            case 0 -> customers.get(rowIndex).getId();
            case 1 -> customers.get(rowIndex).getName();
            case 2 -> customers.get(rowIndex).getSurname();
            case 3 -> customers.get(rowIndex).getAbonnementId();
            case 4 -> customers.get(rowIndex).getTelephone();
            case 5 -> customers.get(rowIndex).getAddress();
            case 6 -> customers.get(rowIndex).getMailAddress();
            default -> "-";
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }
}
