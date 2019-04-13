/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricalstores.data;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class Person implements Serializable{
    private String id;
    private String name;
    private int age;
    private String store_id;

    public Person() {
    }
    public Person(Person p) {
        this.age = p.age;
        this.id = p.id;
        this.name = p.name;
        this.store_id = p.store_id;
    }

    public Person(String id, String name, int age, String store_id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.store_id = store_id;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_id() {
        return store_id;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
