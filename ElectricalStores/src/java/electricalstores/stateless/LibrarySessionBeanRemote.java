/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package electricalstores.stateless;

import electricalstores.data.Account;
import electricalstores.data.Product;
import electricalstores.data.Receipt;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author HP
 */
@Remote
public interface LibrarySessionBeanRemote {
    //return store name
    String getStoreName(String user_name);
    //return account information
    Account login(String userName, String password);
    // return success or failed
    String createAccount(String userName, String password, String position , int person_id);
    // return success or failed
    String createPerson(String name , int age , int store_id);
    // return success or failed
    String createStore(String storeName);
    // return success or failed
    String deleteAccount(String userName);
    // return receit information
    Receipt finishReceipt(String receiptId);
    // return list of receipt ID
    List<String> listReceiptId(); 
    // @param productId is product id the customer want to buy
    String createReceipt(List<String> productId , String sales_consultant_name);
    //@param product information
    String importProduct(String name, int price, String description, String type, int store_id); 
    // @param id is product id
    // @param attribute is the attribute will be change 
    String modifyProduct(String Id, String attribute, String newValue);
    
    String deleteProduct(String Id);
    
    List<Receipt> showHistoricalSelling(String storeName); // for admin
    
    List<Product> showProductInStore(String storeName);
    
    List<Product> searchProductByName(String name , String storeName);//for admin
    
    Product getProductDetail(String Id , String store_name);
}
