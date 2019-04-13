/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricalstores.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author HP
 */
public class Postgres {
    public void connect() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ElectricalStores", "postgres", "tranvy")) {

			System.out.println("Java JDBC PostgreSQL Example");
			// When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within 
			// the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
			// Class.forName("org.postgresql.Driver"); 

			System.out.println("Connected to PostgreSQL database!");
			Statement statement = connection.createStatement();
			System.out.println("Reading car records...");
			System.out.printf("%-30.30s  %-30.30s%n", "Model", "Price");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Account\"");
			while (resultSet.next()) {
				System.out.printf("%-30.30s", resultSet.getString("user_name"));
			}

		} /*catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC driver not found.");
			e.printStackTrace();
		}*/ catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        Postgres p = new Postgres();
        p.connect();
    }
}
