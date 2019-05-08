import java.util.Scanner;

/*
Title: Interface
Authors: Ephream Osborne, Ethan Sarginson 
Student number: C3279393, C3329758
Last update: 8/5/2019
This class contains all the details of the interface.
*/
public class Interface
{
    //The private variables for this class.
    //These variables will probably become redundant. Since these values will be stored in the Depot class.
    private Depot depot1, depot2, depot3, depot4;
    private String UserInput = "";
    private double price = 0.0, weight = 0.0;
    private int quantity = 0;
    
    //These will be used for assignment 2.
    private Depot[] depot = new Depot[4];
    private int counter = 0;
    
    private Scanner keyboard = new Scanner(System.in);
    
    /**
     * This is where the main program runs.
     */
    public void run(){
        initiateDepots();
        /**Input is anything but 9. The loop will continue to run.
         * Input is 9, the program exits.
         */
       
        while (!UserInput.equals("9"))
        {
            System.out.println("\nWelcome to Alcolworths Supermarkets. ");
            System.out.println("Enter a command from the list below. ");
            System.out.println("The command to type is the number to the left of the option. Type in that number to continue.");
            System.out.println("1-Add a depot");
            System.out.println("2-Remove a depot");
            System.out.println("3-Add a product to a depot");
            System.out.println("4-Remove one item of a product from a depot");
            System.out.println("5-Query for a list of depots");
            System.out.println("6-Query for a list of products in a depot");
            System.out.println("7-Query about the product's presence in the depots.");
            System.out.println("8-Query for the cumulative value of products in a depot.");
            System.out.println("9-Exit");
            
            //Each case has its own method called below.
            /**
             * Input is equal to a case number, that case's method is invoked.
             * Input is not a recognised case number, an error is displayed and the loop begins again.
             */
            UserInput = keyboard.nextLine();
            switch (UserInput){
                case "1":
                {
                    case1();
                    break;
                }
                case "2":
                {
                    case2();
                    break;
                }
                case "3":
                {
                    case3();
                    break;
                }
                case "4":
                {
                    case4();
                    break;
                }
                case "5":
                {
                    case5();
                    break;
                }
                case "6":
                {
                    case6();
                    break;
                }
                case "7":
                {
                    case7();
                    break;
                }
                case "8":
                {
                    case8();
                    break;
                }
                case "9":
                {
                    case9();
                    break;
                }
                default:
                {
                    System.out.println("Sorry, that is not a command.");
                    break;
                }
            }
        }
    }
    /**
     * Rather than the main being the interface, a method is invoked so as to eliminate the need for static.
     */
    public static void main(String[] args) {
        Interface intFace = new Interface();
        intFace.run();
    }
    
    //Finish off this equals method. It might be useful.
    /*public boolean equals(int depotPos){
        return depot[depotPos].getDepot();
    }*/
    /**
     * Sets all the depots to a default value at the beginning of the program.
     */
    public void initiateDepots(){
        for (int i = 0; i < depot.length; i++){
            depot[i] = new Depot();
            depot[i].resetDepot();
        }
    }
    
    /**
     * Each case method will have this method invoked. If there are no depots, no actions can be performed.
     */
    public boolean checkDepots(){
        //This needs to be updated for the new assignment. --> It has been updated.
        //The counter here indicates how many depots there are. 
        //A user cannot perform certain functions until a depot exists.
        if(counter == 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * After the checkDepots method is invoked, if no depots are present this error message is printed.
     */
    public void printDepots(){
        System.out.println("Sorry, you do not have any depots.");
        }
      
    /**
     * This is invoked in case 3 to reduce repetion of same code.
     */
    //This will probably be a method in the depot class. Will have to see later on. 
    public void inputNewProduct(){
        System.out.println("Enter the name of the product:");
        UserInput = keyboard.nextLine();
        System.out.println("Now enter the price (dollars), weight (kilograms) and quanity in that order.");
        price = keyboard.nextDouble();
        weight = keyboard.nextDouble();
        quantity = keyboard.nextInt();    
    }
    /**
     * Input is 1, the method to add a depot is invoked. It checks the counter. If the counter is at 4, the depots are full. 
     * If one is empty the depot will add a name to the first empty depot.
     */
    public void case1(){
        if (counter < 4){
            System.out.println("Enter the name of the new depot:");
            UserInput = keyboard.nextLine();
            for (int i = 0; i < depot.length; i++){
                if (depot[i].getDepot().equals(UserInput)){
                    while (depot[i].getDepot().equals(UserInput)){
                        System.out.println("Sorry, that depot already exists, try again.");
                        UserInput = keyboard.nextLine();
                    }
                }
            }
            depot[counter].setDepot(UserInput);
            counter++;
            depotArraySort();
            arraySortEmpty();
        }
        else{
            System.out.println("Sorry, you already have 4 depots.");
            //The error message is easier to read if the user then has to press enter to return to menu.
            returnToMenu();
        }
    }
    /**
     * Input is 2, the method to delete a depot is invoked. 
     * It will check if the user input is the same as a registered depot.
     * That depot will be made null.
     */
    public void case2(){
        if (checkDepots() == true){
            printDepots();
        }
        else{// Will this function, an option is to sort the array after removing a depot.
            //If a depot is deleted, the counter is minused by one. 
            //We then sort the array so the empty space is equal to counter.
            System.out.println("Enter the name of the depot you wish to delete:");
            UserInput = keyboard.nextLine();
            for (int i = 0; i < depot.length; i++){
                if (depot[i].getDepot().equals(UserInput)){
                    depot[i] = null;
                    depot[i] = new Depot();
                    depot[i].resetDepot();
                }
            }
            counter--;
            depotArraySort();
            arraySortEmpty();
        }
        returnToMenu();
    }
    /**
     * Input is 3, the user can add a new product to a depot they specify.
     * They will be requested to input name, quantity, weight and cost. Not necessarily in that order.
     */
    public void case3(){
        //Products can only be added if the depot exists (counter is not 0).
        if (checkDepots() == true){
            printDepots();
        }
        else{
            System.out.println("Type the depot you want to add a product too");
            UserInput = keyboard.nextLine();
            //A product is added to a specified depot.
            if (depot1 != null && depot1.getName().equals(UserInput)){   
                if (depot1.checkProductAgain() == false){
                    inputNewProduct();
                    depot1.setProduct(UserInput, price, weight, quantity);
                    UserInput = keyboard.nextLine();
                }
                //No products can be added if there are already 3 in the depot.
                else{
                    System.out.println("Sorry, this depot is full.");
                }
            }     
            else if (depot2 != null && depot2.getName().equals(UserInput)){
                if (depot2.checkProductAgain() == false){
                    inputNewProduct();
                    depot2.setProduct(UserInput, price, weight, quantity);
                    UserInput = keyboard.nextLine();
                }
                else{
                    System.out.println("Sorry, this depot is full.");
                }
            }
            else{
                System.out.println("Sorry, that does not match any listed depots.");
            }
        }
    }
    /**
     * This method was added to reduce repetitive code.
     */
    public String productDelete(){
        System.out.println("Enter the product you wish to delete:");
        UserInput = keyboard.nextLine();
        return UserInput;
    }
    
    /**
     * Input is 4, the user can remove one item of a product from a depot. The quantity is reduced by 1.
     * If the quantity becomes 0. the product is deleted.
     */
    public void case4(){
        if (checkDepots() == true){
            printDepots();
        }
        else{
            System.out.println("Enter the depot of the product you wish to remove:");
            UserInput = keyboard.nextLine();
            if (depot1 != null && depot1.getName().equals(UserInput)){
                System.out.println("Enter the product you wish to delete:");
                UserInput = keyboard.nextLine();
                depot1.deleteProduct(UserInput);
            }
            else if (depot2 != null && depot2.getName().equals(UserInput)){
                System.out.println("Enter the product you wish to delete:");
                UserInput = keyboard.nextLine();
                depot2.deleteProduct(UserInput);
            }
            else{
                System.out.println("Sorry, that does not match any depots.");
            }
        }
    }
    
    /**
     * Input is 5, all existing depots appear. If no depots exist, according to the requirements, a one line message will appear.
     */
    public void case5(){
        System.out.println("The names of the depots will appear below.");
        if (counter != 0){
            for (int i = 0; i < depot.length; i++){
                System.out.println(depot[i].getDepot());
            }
        }
        else{
            System.out.println("No depots.");
        }
        returnToMenu();
    }
    /**
     * Input is 6, the user can choose a depot to query for products. 
     * All the products are listed with their cost, weight, quantities.
     */
    public void case6(){
        if (checkDepots() == true){
            printDepots();
        }
        else{
            System.out.println("Which depot do you want to query for products?");
            UserInput = keyboard.nextLine();
            if ((depot1 != null) && depot1.getName().equalsIgnoreCase(UserInput)){
                System.out.println(depot1.getProduct());
            }  
            else if ((depot2 != null) && depot2.getName().equalsIgnoreCase(UserInput)){
                System.out.println(depot2.getProduct());
            }
            else{
                System.out.println("Sorry that does not match any listed depots.");
            }       
        }
        returnToMenu();
    }
    /**
     * Input is 7, the user can search for a product.
     * The depot/s the product is in with its quantity is printed. The product may be in both depots.
     */
    public void case7(){
        if (checkDepots() == true){
            printDepots();
        }
        else {
            System.out.println("Enter the name of the product you wish to query:");
            UserInput = keyboard.nextLine();
            if ((depot1 != null) && (depot1.searchProducts(UserInput) != 0)){
                if ((depot2 != null) && (depot2.searchProducts(UserInput) != 0)){
                    System.out.println("Product "+UserInput+" is in depots "+depot1.getName()+" and "+depot2.getName() +" with quantity "+(depot1.searchProducts(UserInput)+depot2.searchProducts(UserInput)));
                }
                else{
                    System.out.println("Product "+UserInput+" is in depot "+depot1.getName()+" with quantity "+depot1.searchProducts(UserInput));
                }
            }
            else if (!((depot1 != null) && (depot1.searchProducts(UserInput) != 0)) && ((depot2 != null) && (depot2.searchProducts(UserInput) != 0))){
                System.out.println("Product "+UserInput+" is in depot "+depot2.getName()+" with quantity "+depot2.searchProducts(UserInput));
            }
            else{
                System.out.println("Sorry that does not match any listed products.");
            }
        }
        returnToMenu();
    }
    /**
     * Input is 8, the user can check for the cumulative cost of all the products in the depot.
     */
    public void case8(){
        if (checkDepots() == true){
            printDepots();
        }
        else{
            System.out.println("Enter the name of the depot you wish to check to cumulative product value of:");
            UserInput = keyboard.nextLine();
            if (depot1 != null && depot1.getName().equals(UserInput)){
                System.out.println("The cumulative value of depot "+depot1.getName()+" is $"+depot1.getDepotValue());
            }
            else if (depot2 != null && depot2.getName().equals(UserInput)){
                System.out.println("The cumulative value of depot "+depot2.getName()+" is $"+depot2.getDepotValue());   
            }
            else{
                System.out.println("Sorry, that does not match any of the depots listed.");
            }
        }
        returnToMenu();
    }
    /**
     * Input is 9, the loop ends and the program terminates.
     */
    public void case9(){
        System.out.println("Thankyou for using Alcolworths Supermarkets.");
        UserInput = "9";
    }
    /** 
     * Prompts user to enter an input to continue to the menu. Gives them time to read the queries. 
     */
    public void returnToMenu(){//We need to double check where we want to put this method.
        System.out.println("Enter any character to return to menu:");
        UserInput = keyboard.nextLine();
    }
    /**
     * Sorts the arrays into alphabetical order, with empty depots at the end.
     */
    public void depotArraySort(){
        for (int i = 0; i< depot.length; i++){
            for (int j = i + 1; j < depot.length; j++){
                if (depot[i].getDepot().compareTo(depot[j].getDepot()) > 0){
                    Depot aux = depot[i];
                    depot[i] = depot[j];
                    depot[j] = aux;
                }
            }
        }
    }
    /**
     * This sorts the array so that all empty elements are put to the end of the array.
     * The first empty place is the same as counter.
     */
    public void arraySortEmpty(){
    for (int i = 0; i < depot.length; i++){
            for (int j = i + 1; j < depot.length; j++){
                if (depot[i].getDepot().equals("Empty") && !(depot[j].getDepot().equals("Empty"))){
                    Depot aux = depot[i];
                    depot[i] = depot[j];
                    depot[j] = aux;
                }
            }
        }
    }
}
