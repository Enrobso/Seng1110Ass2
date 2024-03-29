/*
Title: Depot
Authors: Ephream Osborne, Ethan Sarginson
Student number: C3279393, C3329758
Last update: 27/5/2019
This class contains all the details of the depot.
*/
public class Depot
{
    //These are for the new program.
    //This will be similar to the methods in Interface for the depot array.
    private Product[] product = new Product[5];
    private static Product[] productSameName = new Product[1];
    private int counterDepot = 0;
    private String name;
    //The following methods will be for the new program.
    /**
     * This is the default constructor. When a depot is initialised it is set to a default name.
     * This name is "Empty" and is displayed when querying for depots.
     */
    public Depot(){
        name = "Empty";
    }
    
    /**
     * Products are all initialised at the beginning of the programs execution.
     */
    public static void initiateSameNameArray(){
        for (int i = 0; i < productSameName.length; i++){
            productSameName[i] = new Product();
        }
    }
    
    /**
     * Return's the counter of the specified depot.
     */
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
    public void setName(String newName){
        name = newName;
    }
    
    /**
     * Returns the specified depot's name.
     */
    public String getName(){
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
                outputFile += (name+"-depot"+ " "+product[i].getName()+" "+product[i].getPrice()+" "+product[i].getWeight()+" "+product[i].getQuantity()+",");
            else
                outputFile += (name+"-depot"+" "+product[i].getName()+" "+product[i].getPrice()+" "+product[i].getWeight()+" "+product[i].getQuantity());
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
    /**
     * Returns the total cumulative value of a depot.
     */
    public double getProductPrice(){
        double sum = 0;
        for (int i = 0; i < product.length; i++){
            sum += product[i].getPrice()*product[i].getQuantity();
        }
        return sum;
    }
}   
