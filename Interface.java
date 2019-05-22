import java.util.Scanner;
import java.io.*;
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
        Depot.initiateSameNameArray();
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
            System.out.println("9-Export depot and product information to a file");
            System.out.println("10-Import depot and product information from a file");
            System.out.println("11-Exit");
            
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
                case "10":
                {
                    case10();
                    break;
                }
                case "11":
                {
                    case11();
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
            depot[i].initiateProducts();
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
        System.out.println("Now enter the price (dollars), weight (kilograms) and quantity in that order.");
        price = keyboard.nextDouble();
        weight = keyboard.nextDouble();
        quantity = keyboard.nextInt();    
    }
    
    //This could be a method implemented to reduce copy-paste.
    /*public void checkDepotMatch(){
        
    }*/
    
    /**
     * Input is 1, the method to add a depot is invoked. It checks the counter. If the counter is at 4, the depots are full. 
     * If one is empty the depot will add a name to the first empty depot.
     */
    public void case1(){
        if (counter < 4){
            System.out.println("Enter the name of the new depot:");
            UserInput = keyboard.nextLine();
            UserInput = UserInput.toLowerCase();
            
            for (int i = 0; i < depot.length; i++){
                    while (depot[i].getDepot().equals(UserInput)){
                        System.out.println("Sorry, that depot already exists, try again.");
                        UserInput = keyboard.nextLine();
                    }
            }
            UserInput = UserInput.toLowerCase();
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
            UserInput = UserInput.toLowerCase();
            int repeatLoop = 0;
            do{
                for (int i = 0; i < depot.length; i++){
                    if (depot[i].getDepot().equals(UserInput)){
                        depot[i] = null;
                        depot[i] = new Depot();
                        repeatLoop = 1;
                    }
                    if((repeatLoop == 0) && (i == 3)){
                        System.out.println("Sorry that did not match any of the depots. Try again.");
                        UserInput = keyboard.nextLine();
                        UserInput = UserInput.toLowerCase();
                    }
                }
            }while(repeatLoop == 0);
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
            UserInput = UserInput.toLowerCase();
            //A product is added to a specified depot.
            int indexDepot = -1;
            int repeatLoop = 0;
            for (int i = 0; i < depot.length; i++){
                if (depot[i].getDepot().equals(UserInput)){
                    indexDepot = i;
                    repeatLoop = 1;
                }
                if ((repeatLoop == 0) && (i == 3)){
                    System.out.println("Sorry that does not match any listed depots.");
                    returnToMenu();
                }
            }
            if((indexDepot >= 0) && (depot[indexDepot].getCounter() < 5)){
                //depot[i]   implement a method in Depot that adds the product to depot[i].
                System.out.println("Now enter the name of the product: ");
                UserInput = keyboard.nextLine();
                UserInput = UserInput.toLowerCase();
                //Enter depot and product array check in here. If there is a product with the same name, it indicates this.
                int ifNewProduct = 0;
                if (depot[indexDepot].checkProductArray(UserInput) == 1){
                    System.out.println("This product already exists in this depot.");
                    System.out.println("Enter a new quantity. This will be added to the previous quantity.");
                    quantity = keyboard.nextInt();
                    depot[indexDepot].setSameNameProductIfSameDepot(quantity, UserInput);
                    ifNewProduct = 1;
                }
                else{
                    for (int j = 0; j < depot.length; j++){
                        if ((ifNewProduct == 0) && (depot[j].checkProductArray(UserInput) == 1)){
                                System.out.println("There is a product in depot "+depot[j].getDepot()+" with the same name.");
                                depot[j].searchProductArray(UserInput);
                                System.out.println("Now enter the quantity. The price and weight has been copied from the other depot.");
                                quantity = keyboard.nextInt();
                                depot[indexDepot].setSameNameArray(counter, quantity);
                                ifNewProduct = 1;
                            }
                        }
                    }
                //Now set the product.
                if (ifNewProduct != 1){
                    inputNewProduct();
                    depot[indexDepot].addProduct(UserInput, price, weight, quantity);
                    
                }
                keyboard.nextLine();
            }
            else if ((indexDepot >= 0) && (depot[indexDepot].getCounter() == 5)){
                System.out.println("Sorry, this depot is full.");
                returnToMenu();
            }
        }
    }
    
    /**
     * Input is 4, the user can remove any specified number of items of a product from a depot.
     * If the quantity becomes 0. the product is deleted.
     */
    public void case4(){
        if (checkDepots() == true){
            printDepots();
        }
        else{
            int depotIndex = -1;
            System.out.println("Enter the depot of the product you wish to remove:");
            UserInput = keyboard.nextLine();
            UserInput = UserInput.toLowerCase();
            
            for (int i = 0; i < depot.length; i++){
                if (depot[i].getDepot().equals(UserInput)){
                    depotIndex = i;
                }
            }
            if (depotIndex != -1){
                System.out.println("Enter the product and the quantity of it you wish to delete:");
                UserInput = keyboard.nextLine();
                UserInput = UserInput.toLowerCase();
                quantity = keyboard.nextInt();
            
                int checkForProduct = depot[depotIndex].deleteProduct(UserInput, quantity);
                if (checkForProduct == 0 ){
                    System.out.println("Sorry, that product doesn't exist. ");
                }
                else if (checkForProduct == 1 ){
                    System.out.println("Sorry, "+UserInput+" only has "+depot[depotIndex].getProductQty(UserInput)+" items in depot "+depot[depotIndex].getName()+".");
                }
                else if (checkForProduct == 2 ){
                    System.out.println(UserInput+" is now removed from the depot.");
                }
                else if (checkForProduct == 3 ){
                    System.out.println(quantity +" items of product "+UserInput+" deleted from depot "+depot[depotIndex].getDepot()+".");
                }
                keyboard.nextLine();
            }
            else{
                System.out.println("Sorry that depot doesn't exist.");
            }
        }
        
        returnToMenu();
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
            System.out.println("which depot do you want to query for products:");
            UserInput = keyboard.nextLine();
            UserInput = UserInput.toLowerCase();
            int repeatLoop = 0;
            for (int i = 0; i < depot.length; i++){
                if (depot[i].getDepot().equals(UserInput)){
                    depot[i].getProduct();
                    repeatLoop = 1;
                }
                if((repeatLoop == 0) && (i == 3)){
                    System.out.println("Sorry that did not match any of the depots.");
                }
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
            UserInput = UserInput.toLowerCase();
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
            UserInput = UserInput.toLowerCase();
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
     * Input is 9, user specifies the name of the file.
     * Depot and product information is exported to a text file.
     */
    public void case9(){
        String fileName = "";
        System.out.println("Enter the name of the new file:");
        fileName = keyboard.nextLine();
        fileName = fileName+".txt";
        
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(fileName);
        }
        catch(FileNotFoundException e){
            System.out.println("Error opening the file "+fileName);
        }
        
        String line = "";
        String[] outputLine = new String[5];
        for (int i = 0; i < counter; i++){
            if (depot[i].getCounter() == 0){
                outputStream.println(depot[i].getDepot());
            }
            else{
                line = depot[i].exportProducts();
                if (depot[i].getCounter() == 1){
                    outputStream.println(line);
                }
                else{
                    //If there are multiple products, the string returned from exportProducts() is all of the products together.
                    //A comma is placed between each line of products. This is split here.
                    outputLine = line.split(",");
                    for (int j = 0; j < depot[i].getCounter(); j++){
                        outputStream.println(outputLine[j]);
                    }
                }
            }
        }
        outputStream.close();
        System.out.println("The lines were written to the text file "+fileName);
        returnToMenu();
    }
    /**
     * Input is 10, user specifies the file to import.
     * The file is imported into depot and product information.
     */
    public void case10(){
        //The .txt file is added to the existing depot and product arrays.
        String fileName;
        String[] importArray = new String[5];
        System.out.println("Enter the name of the file you wish to import:");
        fileName = keyboard.nextLine();
        
        Scanner inputStream = null;
        try{
            inputStream = new Scanner(new File(fileName));
        }
        catch(FileNotFoundException e){
            System.out.println("There was an error in opening the file "+fileName);
            System.exit(0);
        }
        
        while(inputStream.hasNextLine() && (counter < 4)){
            UserInput = inputStream.nextLine();
            UserInput = UserInput.trim();
            boolean newDepot = UserInput.contains(" ");
            
            if (!newDepot){
                //Add a new depot with no product info.
                //Check if the depot already exists.
                //If the depot already exists, that depot will be used.
                
                //A line with no spaces is a depot only.
                int i = -1;
                UserInput = UserInput.toLowerCase();
                for (int j = 0; j < depot.length; j++){
                    if(depot[j].getDepot().equals(UserInput)){
                        i = j;
                    }
                }
                if (i != -1){
                    System.out.println("The depot "+UserInput+" already exists.");
                    System.out.println("That depot will be used.");
                }
                else{
                    depot[counter].setDepot(UserInput);
                    counter++;
                    depotArraySort();
                    arraySortEmpty();
                    System.out.println("The depot "+UserInput+" was successfully added.");
                }
            }
            else{
                //Add a depot that may or may not be new, with the new product.
                int checkForException = 0;
                //Check if the format in the line is correct.
                //Otherwise there is an exception and then the next line is tested.
                try{
                    importArray = UserInput.split(" ");
                    importArray[0] = importArray[0].toLowerCase();
                    importArray[1] = importArray[1].toLowerCase();
                    price = Double.parseDouble(importArray[2]);
                    weight = Double.parseDouble(importArray[3]);
                    quantity = Integer.parseInt(importArray[4]);
                }
                catch(Exception e){
                    System.out.println("There was an error in the format of this line.");
                    checkForException = 1;
                }
                if (checkForException == 0){
                    int j = -1;
                    for (int i = 0; i < depot.length; i++){
                        if (depot[i].getDepot().equals(importArray[0])){
                            j = i;
                        }
                    }
                    if (j != -1){
                        System.out.println("There is a depot called "+importArray[0]+" already.");
                        System.out.println("We will use that depot.");
                    }
                    else{
                        depot[counter].setDepot(importArray[0]);
                        counter++;
                        depotArraySort();
                        arraySortEmpty();
                    }
                
                    int indexDepot = -1;
                    for (int i = 0; i < depot.length; i++){
                        if (depot[i].getDepot().equals(importArray[0])){
                            indexDepot = i;
                        }
                    }
                    if((indexDepot >= 0) && (depot[indexDepot].getCounter() < 5)){
                        //depot[i]   implement a method in Depot that adds the product to depot[i].
                        //Enter depot and product array check in here. If there is a product with the same name, it indicates this.
                        int ifNewProduct = 0;
                        if (depot[indexDepot].checkProductArray(importArray[1]) == 1){
                            System.out.println("The product "+importArray[1]+" already exists in this depot.");
                            System.out.println("The quantity will be taken from the .txt file.");
                    
                            depot[indexDepot].setSameNameProductIfSameDepot(quantity, importArray[1]);
                            ifNewProduct = 1;
                        }
                        else{
                            for (int k = 0; k < depot.length; k++){
                                if ((ifNewProduct == 0) && (depot[k].checkProductArray(importArray[1]) == 1)){
                                    System.out.println("There is a product in depot "+depot[k].getDepot()+" with the same name.");
                                    depot[k].searchProductArray(importArray[1]);
                                    System.out.println("The quantity will be taken from the .txt file. The price and weight has been copied from the other depot.");
                                
                                    depot[indexDepot].setSameNameArray(counter, quantity);
                                    ifNewProduct = 1;
                                }
                            }
                        }
                        //Now set the product.
                        if (ifNewProduct != 1){
                            depot[indexDepot].addProduct(importArray[1], price, weight, quantity);
                        }
                
                    }
                    else{
                        System.out.println("Sorry, that depot is full.");
                    }
                }
                else{
                    System.out.println("There was an error in the formatting of the line.");
                    checkForException = 0;
                }
        
            }
        
        }
        inputStream.close();
        returnToMenu();
    }
    
    /**
     * Input is 11, the loop ends and the program terminates.
     */
    public void case11(){
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
