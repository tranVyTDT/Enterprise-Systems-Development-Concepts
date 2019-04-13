/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricalstores.stateless;

import electricalstores.data.Account;
import electricalstores.data.Product;
import electricalstores.data.Receipt;
import electricalstores.stateless.LibrarySessionBeanRemote;
import java.util.List;
import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.postgresql.Driver;
/**
 *
 * @author HP
 */
@Stateless
public class LibrarySessionBean implements LibrarySessionBeanRemote {
    private final String server = "jdbc:postgresql://localhost:5432/ElectricalStores";
    private final String usernameServer = "postgres";
    private final String passwordServer = "tranvy";

    @Override
    public Account login(String userName, String password) {
            Account acc = new Account("a","a","a",1);
            //Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ElectricalStores", "postgres", "tranvy")) {
          acc.setUser_name("b");
            Statement statement = connection.createStatement();
            String SQL = "SELECT * "
                    + "FROM public.\"Account\" a "
                    + "WHERE a.user_name = "
                    + "'" +userName+ "'" +" AND a.password = "
                    + "'" +password+ "'" ;
            try (ResultSet rs = statement.executeQuery(SQL);) {
                rs.next();
                acc.setUser_name("b");
                return new Account(rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getInt("person_id"));
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        } catch (Exception ex) {
            System.out.println("Connection failure.");
            ex.printStackTrace();
            System.err.println("blabla");
        }
        return acc;
    }

    @Override
    public String createAccount(String userName, String password, String position, int person_id) {
        String SQLInsert = "insert into public.\"Account\"(user_name, password, role, person_id) VALUES(?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(server, usernameServer, passwordServer) ;
                PreparedStatement pstmt = connection.prepareStatement(SQLInsert)) {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * "
                    + "FROM public.\"Account\" a"
                    + "WHERE a.user_name = 'userName' AND a.password = 'password'";
            try(ResultSet rs = statement.executeQuery(SQL)) {
                if(rs.next()){
                    return "this username has "; 
                }else{
                    pstmt.setString(1,userName);
                    pstmt.setString(2, password);
                    pstmt.setString(3, position);
                    pstmt.setInt(4, person_id);
                    pstmt.executeUpdate();
                }
                
                
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
        } catch (Exception ex) {
            System.out.println("Connection failure.");
            ex.printStackTrace();
        }
        return "";
        
    }

    @Override
    public void deleteAccount(String userName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Receipt finishReceipt(String receiptId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> listReceiptId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createReceipt(List<String> productId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importProduct(String name, int price, String description, String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifyProduct(String Id, String attribute, String newValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProduct(String Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Receipt> showHistoricalSelling() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Receipt> showHistoricalSelling(String storeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> showProductInStore() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> showProductInStore(String storeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> searchProductByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> searchProductByName(String name, String storeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProductDetail(String Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
