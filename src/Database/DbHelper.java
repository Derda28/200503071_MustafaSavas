package Database;

import beans.Abonnement;
import beans.AuthEmployee;
import beans.Customer;
import beans.Menu;

import javax.swing.*;
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
    private static final String ADMINEMPLOYEE_TABLE="adminemployee";

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

    public boolean isLoginSuccessfulAuth(String uname, String pass) throws SQLException {
        PreparedStatement st = (PreparedStatement) conn
                .prepareStatement("Select Username, Password from "+AUTHEMPLOYEE_TABLE+" where Username=? and Password=?");

        st.setString(1, uname);
        st.setString(2, pass);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isLoginSuccessfulAdmin(int id) throws SQLException {
        PreparedStatement st = (PreparedStatement) conn
                .prepareStatement("Select Id from "+ADMINEMPLOYEE_TABLE+" where Id=?");

        st.setString(1, String.valueOf(id));
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
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

            query = "delete from "+ABONNEMENT_TABLE+" where CustomerId = ?";
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1,customerId);
            preparedStmt.execute();

            preparedStmt.close();

        }catch (Exception e){
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void updateCustomer(Customer oldCustomer, Customer newCustomer) {
        try{
            String query = "update "+CUSTOMER_TABLE+" set Name = ?, Surname = ?, Telephone = ?, Address = ?, MailAdress = ?  where Id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString   (1, newCustomer.getName());
            preparedStmt.setString(2, newCustomer.getSurname());
            preparedStmt.setString(3, newCustomer.getTelephone());
            preparedStmt.setString   (4, newCustomer.getAddress());
            preparedStmt.setString(5, newCustomer.getMailAddress());
            preparedStmt.setInt(6, oldCustomer.getId());

            preparedStmt.executeUpdate();

            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Menu[] getMenus() {
        Menu[] menus = new Menu[2];
        try{
            String sql= "Select * from "+MENU_TABLE;
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                Menu menuVeg = new Menu();
                sql="Select * from "+MENU_TABLE+" where ArtOfMenu='Vegetarisch'";
                rs = stmt.executeQuery(sql);
                while(rs.next()){
                    menuVeg.setContent(rs.getString("Content"));
                    menuVeg.setPrice(rs.getDouble("Price"));
                }
                menus[1] = menuVeg;

                Menu menu = new Menu();
                sql="Select * from "+MENU_TABLE+" where ArtOfMenu='Standart'";
                rs = stmt.executeQuery(sql);
                while(rs.next()){
                    menu.setContent(rs.getString("Content"));
                    menu.setPrice(rs.getDouble("Price"));
                }
                menus[1] = menu;
                return menus;
            }else{
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addMenu(Menu menuStd, Menu menuVeg){
        try{
            String sql;
            sql = " insert into "+MENU_TABLE+" (Content, ArtOfMenu, Price)" + " values (?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString (1, menuStd.getContent());
            preparedStmt.setString (2, menuStd.getArtOfMenu());
            preparedStmt.setDouble  (3, menuStd.getPrice());

            preparedStmt.execute();

            sql = " insert into "+MENU_TABLE+" (Content, ArtOfMenu, Price)" + " values (?, ?, ?)";
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString (1, menuVeg.getContent());
            preparedStmt.setString (2, menuVeg.getArtOfMenu());
            preparedStmt.setDouble  (3, menuVeg.getPrice());

            preparedStmt.execute();

            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateMenu(Menu menu, Menu menuVeg) {
        try {
            String query = "update "+MENU_TABLE+" set Content = ?, Price = ? where ArtOfMenu = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString   (1, menu.getContent());
            preparedStmt.setDouble(2, menu.getPrice());
            preparedStmt.setString(3, menu.getArtOfMenu());

            preparedStmt.executeUpdate();

            preparedStmt.close();

            PreparedStatement preparedStmt2 = conn.prepareStatement(query);
            preparedStmt2.setString   (1, menuVeg.getContent());
            preparedStmt2.setDouble(2, menuVeg.getPrice());
            preparedStmt2.setString(3, menuVeg.getArtOfMenu());

            preparedStmt2.executeUpdate();

            preparedStmt2.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Abonnement> getAbos(int aboArt) {
        ArrayList<Abonnement> abos = new ArrayList<>();
        String aboArtStr="";
        switch(aboArt){
            case 1 -> aboArtStr="Einzelabonnement";
            case 2 -> aboArtStr="Unternehmensabonnement";
            case 3 -> aboArtStr="Abgelaufenes Abonnement";
        }

        try {
            String sql;
            sql = "select * from "+ABONNEMENT_TABLE+" where ArtOfAbo='"+aboArtStr+"'";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Abonnement abo = new Abonnement();

                abo.setId(rs.getInt("Id"));
                abo.setArtOfAbo(rs.getString("ArtOfAbo"));
                abo.setCountOfMenus(rs.getInt("CountOfMenus"));
                abo.setArtOfMenu(rs.getString("ArtOfMenu"));
                abo.setTotalPrice(rs.getDouble("TotalPrice"));
                abo.setHasPaid(rs.getBoolean("HasPaid"));
                abo.setCustomerId(rs.getInt("CustomerId"));
                abo.setStartDate(rs.getString("StartDate"));
                abo.setDuration(rs.getString("Duration"));

                abos.add(abo);
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return abos;
    }
    public void addAbo(Abonnement abo) {
        try{
            String query = "Insert into "+ABONNEMENT_TABLE+" (ArtOfAbo, CountOfMenus, ArtOfMenu, TotalPrice, HasPaid, CustomerId, StartDate, Duration)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, abo.getArtOfAbo());
            preparedStmt.setInt (2, abo.getCountOfMenus());
            preparedStmt.setString   (3, abo.getArtOfMenu());
            preparedStmt.setDouble(4, abo.getTotalPrice());
            preparedStmt.setBoolean(5, abo.isHasPaid());
            preparedStmt.setInt   (6, abo.getCustomerId());
            preparedStmt.setString(7, abo.getStartDate());
            preparedStmt.setString(8, abo.getDuration());

            preparedStmt.execute();

            query = "Update "+CUSTOMER_TABLE+" set AbonnementId=? where Id=?";
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1,abo.getId());
            preparedStmt.setInt(2,abo.getCustomerId());

            preparedStmt.execute();

            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAbo(Abonnement abo, int aboId) {
        try{
            String query = "update "+ABONNEMENT_TABLE+" set ArtOfAbo = ?, CountOfMenus = ?, ArtOfMenu = ?, TotalPrice=?, HasPaid=?, Duration=? where Id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString   (1, abo.getArtOfAbo());
            preparedStmt.setDouble(2, abo.getCountOfMenus());
            preparedStmt.setString(3, abo.getArtOfMenu());
            preparedStmt.setDouble   (4, abo.getTotalPrice());
            preparedStmt.setBoolean(5, abo.isHasPaid());
            preparedStmt.setString(6, abo.getDuration());
            preparedStmt.setInt(7, abo.getId());

            preparedStmt.executeUpdate();

            preparedStmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteAbo(int aboId) {
        try{
            String query = "update "+ABONNEMENT_TABLE+" set ArtOfAbo=? where Id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString   (1, "Abgelaufenes Abonnement");
            preparedStmt.setDouble(2, aboId);

            preparedStmt.executeUpdate();

            query = "update "+CUSTOMER_TABLE+" set AbonnementId = NULL where AbonnementId="+aboId;
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.executeUpdate();

            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<AuthEmployee> getAuthEmployees() {
        ArrayList<AuthEmployee> auth = new ArrayList<>();

        try {
            String sql;
            sql = "select * from "+AUTHEMPLOYEE_TABLE;
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                AuthEmployee abo = new AuthEmployee();

                abo.setId(rs.getInt("Id"));
                abo.setName(rs.getString("Name"));
                abo.setSurname(rs.getString("Surname"));
                abo.setBranchCode(rs.getInt("BranchCode"));
                abo.setUsername(rs.getString("Username"));

                auth.add(abo);
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return auth;
    }

    public void addAuthEmployee(AuthEmployee aE) {
        try{
            String sql;
            sql = " insert into "+AUTHEMPLOYEE_TABLE+" (Name, Surname, BranchCode, Username, Password)" + " values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString (1, aE.getName());
            preparedStmt.setString (2, aE.getSurname());
            preparedStmt.setInt  (3, aE.getBranchCode());
            preparedStmt.setString (4, aE.getUsername());
            preparedStmt.setString  (5, aE.getPassword());

            preparedStmt.execute();

            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateAuthEmployee(AuthEmployee uAE) {
            try{
                String query = "UPDATE "+AUTHEMPLOYEE_TABLE+" SET Name=?, Surname=?, BranchCode=?, Username=?, Password=? where Id = ?;";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString   (1, uAE.getName());
                preparedStmt.setString(2, uAE.getSurname());
                preparedStmt.setInt(3, uAE.getBranchCode());
                preparedStmt.setString   (4, uAE.getUsername());
                preparedStmt.setString(5, uAE.getPassword());
                preparedStmt.setInt(6, uAE.getId());

                preparedStmt.executeUpdate();

                preparedStmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void deleteAuthEmployee(int id) {
        try{
            String sql;
            String query = "DELETE FROM "+AUTHEMPLOYEE_TABLE+" WHERE id=?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.execute();

            preparedStmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getInformation(int customerId){
        ResultSet rs = query("SELECT Name, Surname FROM customer where id="+customerId);
        return rs;
    }

    public ResultSet query(String s) {
        try{
            ResultSet rs = stmt.executeQuery(s);
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}

