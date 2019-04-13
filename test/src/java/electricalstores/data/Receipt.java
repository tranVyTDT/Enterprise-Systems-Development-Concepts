/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricalstores.data;

import javafx.scene.chart.PieChart.Data;

/**
 *
 * @author HP
 */
public class Receipt {
    private String id;
    private String cashier_name;
    private String sales_consultant_name;
    private Data date_and_time;
    private float total_cost;

    public Receipt() {
    }
    public Receipt(Receipt r) {
        this.cashier_name = r.cashier_name;
        this.date_and_time = r.date_and_time;
        this.id = r.id;
        this.sales_consultant_name = r.sales_consultant_name;
        this.total_cost = r.total_cost;
    }

    public Receipt(String id, String cashier_name, String sales_consultant_name, Data date_and_time, float total_cost) {
        this.id = id;
        this.cashier_name = cashier_name;
        this.sales_consultant_name = sales_consultant_name;
        this.date_and_time = date_and_time;
        this.total_cost = total_cost;
    }

    public String getCashier_name() {
        return cashier_name;
    }

    public Data getDate_and_time() {
        return date_and_time;
    }

    public String getId() {
        return id;
    }

    public String getSales_consultant_name() {
        return sales_consultant_name;
    }

    public float getTotal_cost() {
        return total_cost;
    }

    public void setCashier_name(String cashier_name) {
        this.cashier_name = cashier_name;
    }

    public void setDate_and_time(Data date_and_time) {
        this.date_and_time = date_and_time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSales_consultant_name(String sales_consultant_name) {
        this.sales_consultant_name = sales_consultant_name;
    }

    public void setTotal_cost(float total_cost) {
        this.total_cost = total_cost;
    }
    
}
