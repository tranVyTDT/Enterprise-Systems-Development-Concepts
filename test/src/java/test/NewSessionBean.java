/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import electricalstores.data.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ejb.Stateful;

/**
 *
 * @author HP
 */
@Stateful
public class NewSessionBean implements NewSessionBeanLocal {
    private final String server = "jdbc:postgresql://localhost:5432/ElectricalStores";
    private final String usernameServer = "postgres";
    private final String passwordServer = "tranvy";

    @Override
    public Account login(String userName, String password) {
        try (Connection connection = DriverManager.getConnection(server, usernameServer, passwordServer)) {
            String SQL = "SELECT * "
                    + "FROM public.\"Account\" a"
                    + "WHERE a.user_name = ? AND a.password = ?";
            try (PreparedStatement statement = connection.prepareStatement(SQL)) {
                statement.setString(1, userName);
                statement.setString(2, password);
                ResultSet rs = statement.executeQuery();
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
        }
        return new Account();
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
}
