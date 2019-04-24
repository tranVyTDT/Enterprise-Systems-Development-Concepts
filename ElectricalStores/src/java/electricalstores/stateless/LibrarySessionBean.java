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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author HP
 */
@Stateless
public class LibrarySessionBean implements LibrarySessionBeanRemote {
    private final String url = "java:/ElectricalStoreDS";
    private final String usernameServer = "postgres";
    private final String passwordServer = "tranvy";
    private DataSource ds;
    private Context ctx;
    @Override
    public Account login(String userName, String password) {
        Connection c = null;
        Account account = new Account();
        try {
    //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            Statement statement = c.createStatement();
            String SQL = "SELECT * "
                    + "FROM public.\"Account\" a "
                    + "WHERE a.user_name = "
                    + "'" + userName + "'" + " AND a.password = "
                    + "'" + password + "'";
            ResultSet rs = statement.executeQuery(SQL);
            rs.next();
            return new Account(rs.getString("user_name"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getInt("person_id"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return account;
    }
    @Override
    public String createAccount(String userName, String password, String position, int person_id) {
        String SQLInsert = "insert into public.\"Account\"(user_name, password, role, person_id) VALUES(?,?,?,?)";
        Connection c = null;
        try{
    //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            PreparedStatement pstmt = c.prepareStatement(SQLInsert);
            Statement statement = c.createStatement();
            String SQL = "SELECT EXISTS(SELECT *\n" +
                                    "    FROM public.\"Account\" " +
                                    "    WHERE  user_name = "
                                    + "'" + userName  +"'" 
                                    + "OR "
                                    + "person_id = "
                                    + "'" + person_id  +"'" 
                                    + ")";
            ResultSet rs = statement.executeQuery(SQL);
                rs.next();
                if (rs.getBoolean(1)) {
                    return "this username has been used" ;
                } else {
                    SQL = "SELECT EXISTS(SELECT *\n" +
                                    "    FROM public.\"Person\" " +
                                    "    WHERE  id = "
                                    + "'" + person_id  +"')";
                    rs = statement.executeQuery(SQL);
                    rs.next();
                    if(rs.getBoolean(1)){
                        pstmt.setString(1, userName);
                        pstmt.setString(2, password);
                        pstmt.setString(3, position);
                        pstmt.setInt(4, person_id);
                        pstmt.executeUpdate();
                        pstmt.close();
                    }else{
                        return "insert failed";
                    }
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return "successful";
    }

    @Override
    public String deleteAccount(String userName) {
        String sql = "DELETE FROM public.\"Account\" WHERE user_name = ?";
        Connection c = null;
        try {
    //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            PreparedStatement pstmt = c.prepareStatement(sql);
            Statement statement = c.createStatement();
            String SQL = "SELECT EXISTS(SELECT *\n" +
                                    "    FROM public.\"Account\" " +
                                    "    WHERE  user_name = "
                                    + "'" + userName  +"')";
            ResultSet rs = statement.executeQuery(SQL);
            rs.next();
            System.out.println(rs.getBoolean(1));
            if (rs.getBoolean(1)) {
                pstmt.setString(1, userName);
                pstmt.executeUpdate();
                pstmt.close();
            } else {
                return  userName + "not exist" ;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return "successful";
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
    public String createReceipt(List<String> productId , String sales_consultant_name) {
        String SQLInser = "INSERT INTO public.\"Receipt\"(\n" +
                            "total_cost, cashier_name, sales_consultant_name, is_finished, day_and_time)\n" +
                            "VALUES (?, ?, ?, ?, ?)";
        Connection c = null;
        try {
    //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            PreparedStatement pstmt = c.prepareStatement(SQLInser);
            Statement statement = c.createStatement();
            String SQLgetCost = "SELECT  SUM(price)\n" +
                                "FROM public.\"Product\"\n" +
                                "WHERE";
            for(String s : productId){
                String SQL = "SELECT EXISTS(SELECT *\n" +
                                    "    FROM public.\"Product\" " +
                                    "    WHERE  id = "
                                    + "'" + s +"')";
                ResultSet rs = statement.executeQuery(SQL);
                rs.next();
                if(!rs.getBoolean(1)){
                    return s + " out of stock";
                }
                SQLgetCost += " id = '"+ s + "' AND " ;
            }
            SQLgetCost += " id = '" + productId.get(0) +"'";
            ResultSet rs = statement.executeQuery(SQLgetCost);
            rs.next();
            float cost = rs.getFloat(1);
            Calendar calendar = Calendar.getInstance();
            java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
            pstmt.setFloat(1, cost);
            pstmt.setString(2,null);
            pstmt.setString(3,sales_consultant_name);
            pstmt.setBoolean(4, false);
            pstmt.setTimestamp(5, ourJavaTimestampObject);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return "successful";
    }

    @Override
    public String importProduct(String name, int price, String description, String type, int store_id) {
        String SQLInsert = "INSERT INTO public.\"Product\"(\n" +
                            "name, type, description, is_sold, price)\n" +
                            "VALUES (?, ?, ?, ?, ?)";
        String SQLgetMaxId = "SELECT MAX(id)\n" +
                            "FROM public.\"Product\"";
        String SQLinsert2 = "INSERT INTO public.product_store(\n" +
                            "product_id, store_id)\n" +
                            "VALUES (?, ?)";
        Connection c = null;
        try {
        //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(SQLgetMaxId);
            rs.next();
            PreparedStatement pstmt = c.prepareStatement(SQLInsert);
            PreparedStatement pstmt2 = c.prepareStatement(SQLinsert2);
            pstmt.setString(1, name);
            pstmt.setString(2, type);
            pstmt.setString(3, description);
            pstmt.setBoolean(4, false);
            pstmt.setInt(5, price);
            pstmt2.setInt(1, rs.getInt(1) + 1);
            pstmt2.setInt(2, store_id);
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            pstmt2.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return "successful";
    }

    @Override
    public String modifyProduct(String Id, String attribute, String newValue) {
        Connection c = null;
        try {
        //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            String SQL = "UPDATE public.\"Product\"\n" +
                        "SET " +attribute +"=?\n" +
                        "WHERE  id=?" ;
            PreparedStatement pstmt = c.prepareStatement(SQL);
            if(attribute.equalsIgnoreCase("price")){
                pstmt.setInt(1, Integer.parseInt(newValue));
            }else{
                pstmt.setString(1, newValue);
            }
            pstmt.setInt(2, Integer.parseInt(Id));
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException ex) {
            return ex.toString();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return "successful";
    }

    @Override
    public String deleteProduct(String Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Receipt> showHistoricalSelling(String storeName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Product> showProductInStore(String storeName) {
        ArrayList<Product> resultList = new ArrayList();
        Connection c = null;
        try {
        //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            Statement statement = c.createStatement();
            String SQL = "SELECT p.*\n" +
                        "FROM public.\"Product\" p , public.\"Store\" s, public.product_store ps \n" +
                        "WHERE p.id = ps.product_id AND s.name = '"
                    + storeName + "'";
            ResultSet rs = statement.executeQuery(SQL);
            if(rs.next()){
                resultList.add(new Product(rs.getString("name")
                                        , rs.getString("type")
                                        , rs.getString("description")
                                        , rs.getBoolean("is_sold")
                                        , rs.getInt("price")
                                        , storeName));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        
        return resultList;
    }

    @Override
    public List<Product> searchProductByName(String name, String storeName) {
        ArrayList<Product> resultList = new ArrayList();
        Connection c = null;
        try {
        //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            Statement statement = c.createStatement();
            String SQL = "SELECT p.*\n" +
                        "FROM public.\"Product\" p , public.\"Store\" s, public.product_store ps \n" +
                        "WHERE p.id = ps.product_id AND s.name = '"
                    + storeName + "' AND p.name Like '%"
                    + name + "%'";
            ResultSet rs = statement.executeQuery(SQL);
            if(rs.next()){
                resultList.add(new Product(rs.getString("name")
                                        , rs.getString("type")
                                        , rs.getString("description")
                                        , rs.getBoolean("is_sold")
                                        , rs.getInt("price")
                                        , storeName));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        
        return resultList;
    }

    @Override
    public Product getProductDetail(String Id , String store_name) {
        String SQL = "SELECT name, type, description, is_sold, price\n" +
                    "FROM public.\"Product\""
                + "WHERE id = "
                + "'" + Id +"'";
        Connection c = null;
        try {
        //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(SQL);
            rs.next();
            return new Product(rs.getString("name")
                    , rs.getString("type")
                    , rs.getString("description")
                    , rs.getBoolean("is_sold")
                    , rs.getInt("price")
                    , store_name);
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return new Product();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public String createPerson(String name, int age, int store_id) {
        String SQLInsert = "INSERT INTO public.\"Person\"(\n" +
                            "name, age, store_id)\n" +
                            "VALUES (?, ?, ?);";
        Connection c = null;
        try {
        //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            PreparedStatement pstmt = c.prepareStatement(SQLInsert);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setInt(3, store_id);
            pstmt.executeUpdate();
            pstmt.close();
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return "successful";
    }

    @Override
    public String createStore(String storeName) {
        String SQLInsert = "INSERT INTO public.\"Store\"(\n" +
                            "name)\n" +
                            "VALUES (?);";
        Connection c = null;
        try {
        //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            PreparedStatement pstmt = c.prepareStatement(SQLInsert);
            Statement statement = c.createStatement();
            String SQL = "SELECT EXISTS(SELECT *\n" +
                                    "    FROM public.\"Store\" " +
                                    "    WHERE name = "
                                    + "'" + storeName  +"')";
            ResultSet rs = statement.executeQuery(SQL);
            rs.next();
            if (rs.getBoolean(1)) {
                return "this store name has been used" ;
            } else {
                pstmt.setString(1, storeName);
                pstmt.executeUpdate();
                pstmt.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return "successful";
    }

    @Override
    public String getStoreName(String user_name) {
        Connection c = null;
        Account account = new Account();
        try {
    //-------------------------------------------------
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(url);
            c = ds.getConnection(usernameServer, passwordServer);
    //-------------------------------------------------
    //get connection
            Statement statement = c.createStatement();
            String SQL = "SELECT s.name\n" +
                        "FROM public.\"Store\"  s, public.\"Person\" p, public.\"Account\" a\n" +
                        "WHERE a.user_name = '" +user_name+"' AND a.person_id = p.id AND p.store_id = s.id";
            ResultSet rs = statement.executeQuery(SQL);
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return null;
    }
}
