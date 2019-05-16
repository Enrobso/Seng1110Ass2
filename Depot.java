/*
Title: Depot
Authors: Ephream Osborne, Ethan Sarginson
Student number: C3279393, C3329758
Last update: 8/5/2019
This class contains all the details of the depot.
*/
public class Depot
{
    //These are the instance variables for the old program.
    private String name;
    private Product product1, product2, product3, product4, product5;
    
    //These are for the new program.
    //This will be similar to the methods in Interface for the depot array.
    private Product[] product = new Product[5];
    private static Product[] productSameName = new Product[1];
    private int counterDepot = 0;
    
    //The following methods will be for the new program.
    public Depot(){
        name = "Empty";
    }
    public static void initiateSameNameArray(){
        for (int i = 0; i < productSameName.length; i++){
            productSameName[i] = new Product();
        }
    }
    public int getCounter(){
        return counterDepot;
    }
    /**
     * Checks if the products are all null. Returns true if so.
     */
    public boolean checkProduct(){
        if (counterDepot == 0)
        return true;
        else
        return false;
    }
    /**
     * Initiates the products in the depots at the beginning of the program.
     */
    public void initiateProducts(){
        for (int i = 0; i < product.length; i++){
            product[i] = new Product();
        }
    }
    /**
     * Sets the depot's name.
     */
    public void setDepot(String newName){
        name = newName;
    }
    /**
     * Returns the specified depot's name.
     */
    public String getDepot(){
        return name;
    }
    /**
     * Returns the quantity of a specified product within a depot.
     * Returns -1 if the product was not found. 
     */
    public int getProductQty(String productName){
        int qty = -1;
        for (int i = 0; i < product.length; i++){
            if (product[i].getName().equals(productName)){
                qty = product[i].getQuantity();
            }
        }
        return qty;
    }
    /**
     * Precondition: a string inputted by the user. This is a name of a product.
     * Postcondition: returns an array that matches the array of an equal named product.
     */
    //This method will be implemented in case 3. 
    //The user will be notified if there is a product already with the same inputted name.
    public Product[] searchProductArray(String inputOfUser){
        for (int i = 0; i < product.length; i++){
            if (product[i].getName().equals(inputOfUser)){
                productSameName[0].setName(product[i].getName());
                productSameName[0].setPrice(product[i].getPrice());
                productSameName[0].setWeight(product[i].getWeight());
            }
        }
        return productSameName;
    }
    /**
     * Notifies the program if there is a product within the array that has the same name as the user's input.
     */
    public int checkProductArray(String inputOfUser){
        int returnValue = 0;
        for (int i = 0; i < product.length; i++){
            if (product[i].getName().equals(inputOfUser)){
                returnValue = 1;
            }
        }
        return returnValue;
    }
    /**
     * Sets the specified product to the values of the product with the same name.
     * The quantity is chosen by the user.
     */
    public void setSameNameArray(int i, int j){
        product[i].setName(productSameName[0].getName());
        product[i].setPrice(productSameName[0].getPrice());
        product[i].setWeight(productSameName[0].getWeight());
        product[i].setQuantity(j);
        counterDepot++;
        productArraySort();
        emptyProductSort();
    }
    /**
     * Preposition: name, price, weight and quantity of product.
     * Postcondition: New product added to specified depot if depot is not full.
     */
    public void addProduct(String newName, double newPrice, double newWeight, int newQuantity){
            product[counterDepot] = new Product();
            product[counterDepot].setName(newName);
            product[counterDepot].setPrice(newPrice);
            product[counterDepot].setWeight(newWeight);
            product[counterDepot].setQuantity(newQuantity);
            
            counterDepot++;
            productArraySort();
            emptyProductSort();
    }
    /**
     * If the user attempts to add a product with the same name as an already existing product,
     * the user will be notified. This method takes the new quantity from the user 
     * and adds it to the previous product quantity.
     */
    public void setSameNameProductIfSameDepot(int i, String depotName){
        for (int j = 0; j < product.length; j++){
            if (product[j].getName().equals(depotName))
            product[j].setQuantity(product[j].getQuantity() + i);
        }
    }
    
    /**
     * This sorts the product array into alphabetical order.
     */
    public void productArraySort(){
        for (int i = 0; i< product.length; i++){
            for (int j = i + 1; j < product.length; j++){
                if (product[i].getName().compareTo(product[j].getName()) > 0){
                    Product aux = product[i];
                    product[i] = product[j];
                    product[j] = aux;
                }
            }
        }
    }
    /**
     * This sorts the array so that all empty elements are put to the end of the array.
     * The first empty place is the same as counter.
     */
    public void emptyProductSort(){
    for (int i = 0; i < product.length; i++){
            for (int j = i + 1; j < product.length; j++){
                if (product[i].getName().equals("Empty") && !(product[j].getName().equals("Empty"))){
                    Product aux = product[i];
                    product[i] = product[j];
                    product[j] = aux;
                }
            }
        }
    }
    /**
     * Returns a one line message "No products" if all the products are null.
     * If not all products are empty, the values of the existing depots is returned.
     */
    public void getProduct(){
        if (checkProduct() == true)
        System.out.println("No products.");
        else
        getProductIndividual();
    }
    /**
     * Returns products' values for getProduct.
     */
    public void getProductIndividual(){
        for (int i = 0; i < product.length; i++){
            if (!product[i].getName().equals("Empty"))
            System.out.println("Name: "+product[i].getName()+" Price: $"+product[i].getPrice()+" Weight: "+product[i].getWeight()+"kg Quantity: "+product[i].getQuantity()+".");
            else
            System.out.println(product[i].getName());
        }
    }
    /**
     * This is for exporting the depot and product information to a txt file.
     */
    public String exportProducts(){
        String outputFile = "";
        for (int i = 0; i < counterDepot; i++){
            if ((counterDepot > 1) && ((i < (counterDepot-1))))
                outputFile += (name+ " "+product[i].getName()+" "+product[i].getPrice()+" "+product[i].getWeight()+" "+product[i].getQuantity()+",");
            else
                outputFile += (name+ " "+product[i].getName()+" "+product[i].getPrice()+" "+product[i].getWeight()+" "+product[i].getQuantity());
        }
        return outputFile;
    }
    /**
     * Precondition: the product's name and the quantity.
     * Postcondition: the product is found and the specified quantity is removed from the product.
     * If an error occured the user will be notified from Interface.
     */
    public int deleteProduct(String productName, int productQty){
        int returnNum = 0;
        int deleteIndex = -1;
        for (int i = 0; i < product.length; i++){
            if (product[i].getName().equals(productName)){
                returnNum = product[i].reduceQuantity(productQty);
                if (returnNum == 2){
                    deleteIndex = i;
                }
            }
            else if ((i == 4) && (returnNum == 0)){
                returnNum = 0;
            }
        }
        if (returnNum == 2){
            product[deleteIndex] = null;
            product[deleteIndex] = new Product();
            counterDepot--;
            productArraySort();
            emptyProductSort();
        }
        return returnNum;
    }
    
    
    
    
    
    //These methods are for the old program, but may be modified as the program progresses.
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
     * Similar to checkProduct, except it returns true if all the products are full.
     */
    public boolean checkProductAgain(){
        if (product1 != null && product2 != null && product3 != null)
        return true;
        else
        return false;
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
            
            if (product2.getQuantity() == 0){
                product2 = null;
                if (product3 != null){
                    setProduct2(product3.getName(), product3.getPrice(), product3.getWeight(), product3.getQuantity());
                    product3 = null;
                }
            }
        }
        else if (product3 != null && product3.getName().equals(newName)){
            
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

