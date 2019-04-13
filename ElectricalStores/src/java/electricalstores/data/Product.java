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
    private String id;

    public Product() {
    }
    public Product(Product p ){
        this.description = p.description;
        this.id = p.id;
        this.is_sold = p .is_sold;
        this.name = p.name;
        this.type = p.type;
    }
    public Product(String name, String type, String description, String id) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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
    
    
}
