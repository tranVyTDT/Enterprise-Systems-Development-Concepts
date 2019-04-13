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
    Account login(String userName, String password);
    
    String createAccount(String userName, String password, String position , int person_id);
    
    void deleteAccount(String userName);

    Receipt finishReceipt(String receiptId);
    
    List<String> listReceiptId(); // return list of receipt ID
    
    void createReceipt(List<String> productId);
    
    void importProduct(String name, int price, String description, String type); 
    
    void modifyProduct(String Id, String attribute, String newValue);
    
    void deleteProduct(String Id);
    
    List<Receipt> showHistoricalSelling(); // for manager
    
    List<Receipt> showHistoricalSelling(String storeName); // for admin
    
    List<Product> showProductInStore();//for manager
    
    List<Product> showProductInStore(String storeName);//for admin
    
    List<Product> searchProductByName(String name);//for manager
    
    List<Product> searchProductByName(String name , String storeName);//for admin
    
    Product getProductDetail(String Id);
}
