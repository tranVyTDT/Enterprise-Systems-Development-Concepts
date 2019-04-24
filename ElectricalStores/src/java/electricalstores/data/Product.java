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
public class Product implements Serializable{
    private String name;
    private String type;
    private String description;
    private boolean is_sold = false;
    private int price;
    private String store_name;

    public Product() {
    }
    public Product(Product p ){
        this.description = p.description;
        this.price = p.price;
        this.is_sold = p .is_sold;
        this.name = p.name;
        this.type = p.type;
        this.store_name = p.store_name;
    }
    public Product(String name, String type, String description, Boolean is_sold ,int price, String store_name) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.is_sold = is_sold;
        this.price = price;
        this.store_name = store_name;
    }

    public int getPrice() {
        return price;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
    
    
    public String getDescription() {
        return description;
    }

    public int getId() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int price) {
        this.price = price;
    }

    public void setIs_sold(boolean is_sold) {
        this.is_sold = is_sold;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
