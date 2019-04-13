/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricalstores.data;

/**
 *
 * @author HP
 */
public class Account {

    private String user_name;
    private String password;
    private String role;
    private int person_id;

    public Account() {
    }

    public Account(Account a) {
        this.person_id = a.person_id;
        this.password = a.password;
        this.role = a.role;
        this.user_name = a.user_name;
    }

    public Account(String user_name, String password, String role, int id) {
        this.user_name = user_name;
        this.password = password;
        this.role = role;
        this.person_id = id;
    }

    public int getId() {
        return person_id;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setId(int id) {
        this.person_id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
