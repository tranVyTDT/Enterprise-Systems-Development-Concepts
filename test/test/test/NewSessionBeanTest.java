/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import electricalstores.data.Account;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author HP
 */
public class NewSessionBeanTest {
    
    public NewSessionBeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class NewSessionBean.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String userName = "admin";
        String password = "admin";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        NewSessionBeanLocal instance = (NewSessionBeanLocal)container.getContext().lookup("java:global/classes/NewSessionBean");
        Account expResult = new Account(userName,password,"admin",1);
        Account result = instance.login(userName, password);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAccount method, of class NewSessionBean.
     */
    /*
    @Test
    public void testCreateAccount() throws Exception {
        System.out.println("createAccount");
        String userName = "";
        String password = "";
        String position = "";
        int person_id = 0;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        NewSessionBeanLocal instance = (NewSessionBeanLocal)container.getContext().lookup("java:global/classes/NewSessionBean");
        String expResult = "";
        String result = instance.createAccount(userName, password, position, person_id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
