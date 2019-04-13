/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import electricalstores.data.Account;
import javax.ejb.Remote;

/**
 *
 * @author HP
 */
@Remote
public interface NewSessionBeanLocal {
    String createAccount(String userName, String password, String position , int person_id);
    Account login(String userName, String password);

    public Account login(Account account);
}
