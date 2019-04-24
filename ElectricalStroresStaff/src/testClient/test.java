/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testClient;

import electricalstores.data.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class test {

    public static void main(String[] args) {
        EJBTester ejb = new EJBTester();
        ejb.testStatelessEJB();
        
    }
}
