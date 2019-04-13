/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricalstores.stateless;

import electricalstores.data.Account;

/**
 *
 * @author HP
 */
public class test {
    
    public static void main(String[] args) {
        LibrarySessionBean l = new LibrarySessionBean();
        Account a =  l.login("admin", "admin");
        System.out.println(a.getUser_name());
    }
}
