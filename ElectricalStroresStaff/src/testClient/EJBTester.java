/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testClient;

import java.util.List;
import electricalstores.stateless.LibrarySessionBean;
import electricalstores.stateless.LibrarySessionBeanRemote;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 *
 * @author HP
 */
public class EJBTester {

    private Properties props;
    private InitialContext ctx;
    

    public EJBTester() {
        readJNDI();
    }

    /**
     * Read the JNDI properties file
     */
    private void readJNDI() {
        props = new Properties();

        try {
            props.load(new FileInputStream("jndi.properties"));
        } catch (IOException e) {
            System.err.println(e.toString());
        }

        try {
            ctx = new InitialContext(props);
            //ctx.close();
        } catch (NamingException ex) {
            System.err.println(ex.toString());
        }
    }

    private String getJNDI() {
        String appName = "";
        String moduleName = "ElectricalStores";
        String distinctName = "";
        String sessionBeanName = LibrarySessionBean.class.getSimpleName();
        String viewClassName = LibrarySessionBeanRemote.class.getName();

        return "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + sessionBeanName + "!" + viewClassName;
    }

    /**
     * Show the GUI in console window
     */
    private void showGUI() {
        System.out.println("\n===============================");
        System.out.println("Welcome to TDTU Bookstore");
        System.out.println("===============================");
        System.out.print("Enter 1 to test");
    }


    /**
     * Test the Stateless EJB
     */
    public void testStatelessEJB() {
        try {
            // Scanner definition
            Scanner sc = new Scanner(System.in);

            // Lookup the LibrarySessionBeanRemote
            LibrarySessionBeanRemote libBean = (LibrarySessionBeanRemote) ctx.lookup(getJNDI());

            int choice = 0;
            while (choice != 3) {
                this.showGUI();

                // Use this approach because nextInt will cause error to nextLine()
                choice = Integer.parseInt(sc.nextLine());

                if (choice == 1) {
                    // Add a book
                    ArrayList<String> l = new ArrayList<String>();
                    l.add("1");
                    System.out.println(libBean.modifyProduct("2", "type" , "Electrical Device"));
                } else {
                    // Exit
                    break;
                }
            }

            sc.close();

        } catch (NamingException ex) {
            System.err.println(ex.toString());
        }
    }

}
