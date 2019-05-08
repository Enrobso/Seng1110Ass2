/*
Title: Depot
Authors: Ephream Osborne, Ethan Sarginson
Student number: C3279393, C3329758
Last update: 8/5/2019
This class contains all the details of the depot.
*/
public class Depot
{
    private String name;
    private Product[] product = new Product[5];
    private Product product1, product2, product3, product4, product5;
    
    public void resetDepot(){
        name = "Empty";
    }
    public void setDepot(String newName){
        name = newName;
    }
    public String getDepot(){
        return name;
    }
    
    /**
     * Returns the depot names. All the methods dealing with instance variables use incapsulation.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Sets the depot name.
     */
    public void setName(String newName){
        name = newName;
    }
    //include methods to instantiate products 1, 2 and 3.
    //Also include methods to make them null if desired.
    
    /**
     * This setProduct is used to set new products. The name, price, weight and quantity must all be specified.
     */
    public void setProduct(String newName, double newPrice, double newWeight, int newQuantity){
        if (product1 == null){
        product1 = new Product();
        product1.setName(newName);
        product1.setPrice(newPrice);
        product1.setWeight(newWeight);
        product1.setQuantity(newQuantity);
        }
        else if (product2 == null){
        product2 = new Product();
        product2.setName(newName);
        product2.setPrice(newPrice);
        product2.setWeight(newWeight);
        product2.setQuantity(newQuantity);
        }
        else if (product3 == null){
        product3 = new Product();
        product3.setName(newName);
        product3.setPrice(newPrice);
        product3.setWeight(newWeight);
        product3.setQuantity(newQuantity);
        }
    }
    /**
     * This setProduct1 is invoked in deleteProduct. 
     * It is used to move the remaining products up to fill the empty product place above.
     */
    public void setProduct1(String newName, double newPrice, double newWeight, int newQuantity){
        product1 = new Product();
        product1.setName(newName);
        product1.setPrice(newPrice);
        product1.setWeight(newWeight);
        product1.setQuantity(newQuantity); 
    }
    /**
     * This setProduct2 is invoked in deleteProduct. 
     * It is used to move the remaining products up to fill the empty product place above.
     */
    public void setProduct2(String newName, double newPrice, double newWeight, int newQuantity){
        product2 = new Product();
        product2.setName(newName);
        product2.setPrice(newPrice);
        product2.setWeight(newWeight);
        product2.setQuantity(newQuantity); 
    }
    /**
     * This setProduct3 is invoked in deleteProduct. 
     * It is used to move the remaining products up to fill the empty product place above.
     */
    public void setProduct3(String newName, double newPrice, double newWeight, int newQuantity){
        product3 = new Product();
        product3.setName(newName);
        product3.setPrice(newPrice);
        product3.setWeight(newWeight);
        product3.setQuantity(newQuantity); 
    }
    
    /**
     * This method is invoked in Interface is case 7. The user can search for a specified product.
     * The product's quantity is returned.
     */
    public int searchProducts(String newInput){
        if (product1 != null || product2 != null || product3 != null){
            if (product1 != null && product1.getName().equals(newInput))
            return printProduct1(newInput);
            else if (product2 != null && product2.getName().equals(newInput))
            return printProduct2(newInput);
            else if (product3 != null && product3.getName().equals(newInput))
            return printProduct3(newInput);
            else
            return 0;
        }
        else{
            return 0;
        }
    }
    
    /**
     * Prints the quantity of product 1.
     */
    public int printProduct1(String newInput){
            return product1.getQuantity();
    }
    /**
     * Prints the quantity of product 2.
     */
    public int printProduct2(String newInput){
            return product2.getQuantity();
    }
    
    /**
     * Prints the quantity of product 3.
     */
    public int printProduct3(String newInput){
            return product3.getQuantity();
    }
    
    /**
     * Checks if the products are all null. Returns true if so.
     */
    public boolean checkProduct(){
        if (product1 == null && product2 == null && product3 == null)
        return true;
        else
        return false;
    }
    /**
     * Similar to checkProduct, except it returns true if all the products are full.
     */
    public boolean checkProductAgain(){
        if (product1 != null && product2 != null && product3 != null)
        return true;
        else
        return false;
    }
    
    /**
     * Returns a one line message "No products" if all the products are null.
     * If not all products are empty, the values of the existing depots is returned.
     */
    public String getProduct (){
        if (checkProduct() == true)
        return "No products.";
        else
        return (getProduct1() +"\n----\n"+ getProduct2() +"\n----\n"+ getProduct3());
    }
    /**
     * Returns product1's values for getProduct.
     */
    public String getProduct1(){
        if (product1 != null)
        return ("Name: "+product1.getName()+" Price: $"+product1.getPrice()+" Weight: "+product1.getWeight()+"kg Quantity: "+product1.getQuantity()+".");
        else
        return "Product 1 slot: empty.";
    }
    /**
     * Returns product2's values for getProduct.
     */
    public String getProduct2(){
        if (product2 != null)
        return ("Name: "+product2.getName()+" Price: $"+product2.getPrice()+" Weight: "+product2.getWeight()+"kg Quantity: "+product2.getQuantity()+".");
        else
        return "Product 2 slot: Empty";
    }
    /**
     * Returns product3's values for getProduct.
     */
    public String getProduct3(){
        if (product3 != null)
        return ("Name: "+product3.getName()+" Price: $"+product3.getPrice()+" Weight: "+product3.getWeight()+"kg Quantity: "+product3.getQuantity()+".");
        else
        return "Product 3 slot: Empty";
    }
    /**
     * This method is invoked in Interface. The reduceQuantity method in Product class is invoked.
     * If the quantity reaches 0, then product is deleted. The products then move up a place to fill the empty space.
     */
    public void deleteProduct(String newName){
        if (product1 != null && product1.getName().equals(newName)){
            product1.reduceQuantity();
            if (product1.getQuantity() == 0){
                product1 = null;
                if (product2 != null){
                    setProduct1(product2.getName(), product2.getPrice(), product2.getWeight(), product2.getQuantity());
                    product2 = null;
                    if (product3 != null){
                        setProduct2(product3.getName(), product3.getPrice(), product3.getWeight(), product3.getQuantity());
                        product3 = null;
                    }
                }
                
            }
        }
        else if (product2 != null && product2.getName().equals(newName)){
            product2.reduceQuantity();
            if (product2.getQuantity() == 0){
                product2 = null;
                if (product3 != null){
                    setProduct2(product3.getName(), product3.getPrice(), product3.getWeight(), product3.getQuantity());
                    product3 = null;
                }
            }
        }
        else if (product3 != null && product3.getName().equals(newName)){
            product3.reduceQuantity();
            if (product3.getQuantity() == 0)
                product3 = null;
        }
        else{
            System.out.println("Sorry, that does not match any products in this depot.");   
        }
        
    }
    /**
     * Returns the total cost of all the products of a specified depot.
     */
    public double getDepotValue(){
        double price1 = 0, price2 = 0, price3 = 0;
        if (product1 != null)
        price1 = product1.getPrice() * product1.getQuantity();
        if (product2 != null)
        price2 = product2.getPrice() * product2.getQuantity();
        if (product3 != null)
        price3 = product3.getPrice() * product3.getQuantity();
        
        return (price1 + price2 + price3);
        
    }
}

