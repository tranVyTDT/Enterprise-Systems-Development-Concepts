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
public class Store {
    private String name;
    private String id;

    public Store() {
    }
    public Store(Store s) {
        this.id = s.id;
        this.name = s.name;
    }

    public Store(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
