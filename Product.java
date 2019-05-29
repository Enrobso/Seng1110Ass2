/*
Title: Product
Author: Ephream Osborne, Ethan Sarginson
Student number: C3279393, C3329758
Last update: 27/5/2019
This class contains all the details of the product.
*/
public class Product
{
    private String name;
    private double price;
    private double weight;
    private int quantity;
    /**
     * This is the constructor. When the products are initialised they are set to a default value.
     */
    public Product(){
        name = "Empty";
        price = 0;
        weight = 0;
        quantity = 0;
    }
    
    /**
     * Get the product's name.
     */
    public String getName()
    {
        return name;        

    }
    
    /**
     * The user needs to be able to search/add a product name. This sets the product name for that.
     */
    public void setName(String newName)
    {
        name = newName;
    }
    /**
     * Returns the product price.
     */
    public double getPrice()
    {
        return price;       

    }
    
    /**
     * The user also needs to search/add the product's price, weight and quantity. 
     * This method sets the price.
     */   
    public void setPrice(double newPrice)
    {
        price = newPrice;
    }
    
    /**
     * Returns the product's weight.
     */
    public double getWeight()
    {
        return weight;      

    }
    
    /**
     * Sets the product's weight.
     */
    public void setWeight(double newWeight)
    {
        weight = newWeight;
    }

    /**
     * Returns the product's quantity.
     */
    public int getQuantity()
    {
        return quantity;        

    }
    
    /**
     * Sets the product's quantity.
     */
    public void setQuantity(int newQuantity)
    {
        quantity = newQuantity;
    }
    
    /**
     * Reduces the quantity of the product by 1.
     * The user can remove one of a product.
     */
    public int reduceQuantity(int qtyReduce){
        int returnNo = -1;
        if ((quantity - qtyReduce) < 0){
            returnNo = 1;
        }
        else if ((quantity - qtyReduce) == 0){
            quantity -= qtyReduce;
            returnNo = 2;
        }
        else if ((quantity - qtyReduce) > 0){
            quantity -= qtyReduce;
            returnNo = 3;
        }
        return returnNo;
    }

}
