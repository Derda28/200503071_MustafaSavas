package Database;

import beans.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbHelper {
    // JDBC driver name and database URL
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost/200503071";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    private static final String CUSTOMER_TABLE="customer";
    private static final String AUTHEMPLOYEE_TABLE="authemployee";
    private static final String ABONNEMENT_TABLE="abonnement";
    private static final String MENU_TABLE="menu";


    private Connection conn = null;
    private Statement stmt = null;

    public DbHelper() {
        //STEP 2: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        try {
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
            System.out.println("Succesful");
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            stmt.close();
            conn.close();
            System.out.println("Closed Succesfully");
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Customer> getCustomer() {
        ArrayList<Customer> customers = new ArrayList<>();


        try {
            String sql;
            sql = "select * from "+CUSTOMER_TABLE;
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Customer customer = new Customer();

                customer.setName(rs.getString("Name"));
                customer.setSurname(rs.getString("Surname"));
                customer.setTelephone(rs.getString("Telephone"));
                customer.setAddress(rs.getString("Address"));
                customer.setMailAddress(rs.getString("MailAdress"));
                customer.setId(rs.getInt("Id"));
                customer.setAbonnementId(rs.getInt("AbonnementId"));

                customers.add(customer);
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    public void addCustomer(Customer customer) {
        try {
            String sql;
            sql = " insert into "+CUSTOMER_TABLE+" (Name, Surname, Telephone, Address, MailAdress)" + " values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString (1, customer.getName());
            preparedStmt.setString (2, customer.getSurname());
            preparedStmt.setString   (3, customer.getTelephone());
            preparedStmt.setString(4, customer.getAddress());
            preparedStmt.setString    (5, customer.getMailAddress());

            preparedStmt.execute();
            // stmt.executeUpdate(sql); // DDL

            //STEP 5: Extract data from result set
            /*while(rs.next()){
                //Display values
                System.out.print(rs.getString(1));
                System.out.print(rs.getString(2));
            }*/
            //STEP 6: Clean-up environment
            preparedStmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCustomer(int customerId) {
        try{
            String sql;
            String query = "delete from "+CUSTOMER_TABLE+" where id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, customerId);
            preparedStmt.execute();

            preparedStmt.close();

        }catch (Exception e){
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

