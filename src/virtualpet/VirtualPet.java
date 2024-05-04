/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
import java.io.*;

/**
 * Program: Virtual Pet Generator
 * @author Emma Bian
 * @Version March 18, 2024
 */
public class VirtualPet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Random r = new Random();
        
        // TODO code application logic here
        
        //Global variables
        
        
        //Variable initialization
        String menuSelection = "", menuSelection2 = "";
        String username = "", password = "";
        String petType = "";
        String petName = "";
        int option = 0;
        int gameChoice;
        int[] maxStats = new int[3]; //maxHealth, maxFood, maxEnergy
        int[] currentStats = new int[3]; //currentHealth, currentFood, currentEnergy
        int money = 0;
        String unshuffled = "AABBCCDDEE";
        String shuffled = "";
        String revealed = "XXXXXXXXXX";
        boolean end = false;
        
        //Part 1: print splash screen
        printSplashScreen();
        
        //Part 3: read user login
        System.out.print("Enter username: ");
        username = kb.nextLine();
        System.out.print("Enter password: ");
        password = kb.nextLine();
                    
        //Part 3: determine if the login matches
        String fileName = username + ".txt";
        File f = new File(fileName);
        PrintWriter pw = null;
        
        //if the user is a new user
        if (!f.exists()) {
            
            //Part 1: print menu
            printMenuA();
            //Read user selection
            System.out.print("Select option (int or all lowercase word): ");
            menuSelection = kb.next();
            
            //Part 2: make decisions based on input selection
            switch (menuSelection) {
                case "1","start":
                    printPetSelection();
                    System.out.print("Selection: ");
                    petType = kb.next(); //Read user selection
                    System.out.println("You have selected " + petType); //Confirm selection
                    break;
                case "2", "instructions": break;
                case "3", "exit": //Ends the program
                    storeInFile(fileName, username, password, petType, petName, maxStats, currentStats, money);
                    System.exit(0);
                    break; 
                default: break;
            } //end switch case

            //Part 4: read or generate pet name
            System.out.print("Type in a name (1) or generate random name (2): ");
            int userNamingChoice = kb.nextInt();
            if (userNamingChoice == 1) { //allow user input name
                System.out.print("Enter pet name: ");
                kb.nextLine(); //clear the return
                petName = kb.nextLine();
            } else if (userNamingChoice == 2) { //generate random name
                petName = generatePetName();
            } //end if
            System.out.println("Your pet, named " + petName + ", has been born!");

            //Part 5: generate pet stats for named pets
            maxStats = generateStats();
            
        } //end if (for new users)
        
        while (end == false) {
            
            printMenuB();
            //Read user selection
            System.out.print("Select option (int or all lowercase word): ");
            menuSelection2 = kb.next();
            
            //Part 5.2: print main menu
            switch (menuSelection2) {
                case "1","play","interact":
                    option = 1;
                    break;
                case "2", "instructions":
                    option = 2;
                    break;
                case "3", "exit": //Ends the program
                    storeInFile(fileName, username, password, petType, petName, maxStats, currentStats, money);
                    System.exit(0);
                    break;
                default: break;
            } //end switch case
            
            if (option == 1) {
                System.out.print("Do you want to play games (1) or interact with you pet (2): ");
                int playInteract = kb.nextInt();
                
                //play games and earn money
                if (playInteract == 1) {
                    do {
                        System.out.println("Game 1: Number Guessing Game");
                        System.out.println("Game 2: Matching Game");
                        System.out.print("Selection (1 or 2, -1 to quit): ");
                        gameChoice = kb.nextInt();

                        if (gameChoice == 1) { //number guessing game
                            money += game1();

                        } else if (gameChoice == 2) { //matching game
                            //suffle letters
                            shuffled = shuffleString(unshuffled);

                            //reveal letters as player guesses
                            money += game2(revealed, shuffled);
                            
                            System.out.println("Money: " + money);
                        }
                    } while (gameChoice != -1);
                    
                //interact with pet
                } else if (playInteract == 2) {
                    System.out.println("1. Play with your pet");
                    System.out.println("2. Feeding your pet");
                    System.out.println("3. Grooming your pet");
                    System.out.print("Selection (1/2/3): ");
                    int selectPetInteraction = kb.nextInt();

                    if (selectPetInteraction == 1) {
                        System.out.println("Toy............$1.00");
                        money--;
                        currentStats[2]++;
                        System.out.println("Money: " + money);
                        System.out.println("Current energy: " + currentStats[2]);
                    } else if (selectPetInteraction == 2) {
                        System.out.println("Food............$1.00");
                        money--;
                        currentStats[1]++;
                        System.out.println("Money: " + money);
                    } else if (selectPetInteraction == 3) {
                        System.out.println("Groom............$1.00");
                        money--;
                        currentStats[0]++;
                        System.out.println("Money: " + money);
                    }

                }
                
            } //end if
            
            //reset option
            option = 0;
        }
        
    } //end main method
    
    //****************************************
    
    public static void printSplashScreen() {
        System.out.println("           __..--''``---....___   _..._    __");
        System.out.println(" /// //_.-'    .-/\";  `        ``<._  ``.''_ `. / // /");
        System.out.println("///_.-' _..--.'_    \\                    `( ) ) // //");
        System.out.println("/ (_..-' // (< _     ;_..__               ; `' / ///");
        System.out.println(" / // // //  `-._,_)' // / ``--...____..-' /// / //");
        System.out.println("\nComputerized Companions");

    } //end printSplashScreen method
    
    //****************************************
    
    public static void printMenuA() {
        System.out.println("\nMenu:");
        System.out.println("1. Start");
        System.out.println("2. Instructions");
        System.out.println("3. Exit");
    } //end printMenuA method
    
    //****************************************
    
    public static void printMenuB() {
        System.out.println("\nMenu:");
        System.out.println("1. Play/Interact");
        System.out.println("2. Instructions");
        System.out.println("3. Exit");
    } //end printMenuB method
    
    //****************************************
    
    public static void printPetSelection() {
        System.out.println("\nSelect a pet from the list: "); //Displays selection of pets
        System.out.println("Cat");
        System.out.println("Fish");
        System.out.println("Rabbit");
    } //end printPetSelection method
    
    //****************************************
    
    public static String generatePetName() {
        Random r = new Random();
        final int NAME_LENGTH = (r.nextInt(5)+4);
        final String VOWELS = "aeiou";
        final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
        String petName = "";
        
        int startLetterIden = r.nextInt(2); //determines if the starting letter is a consonant or vowel
        for (int i=0;i<NAME_LENGTH;i+=2) { //loops through each letter and assigns a letter accordingly
            if (startLetterIden == 0) {
                petName += "" + VOWELS.charAt(r.nextInt(5));
                if (r.nextInt(2)==0) {
                    petName += "" + petName.charAt(petName.length()-1); //adding double letters
                i++;
                }
                petName += "" + CONSONANTS.charAt(r.nextInt(21));
                if (r.nextInt(2)==0) {
                    petName += "" + petName.charAt(petName.length()-1); //adding double letters
                i++;
                }
            } else {
                petName += "" + CONSONANTS.charAt(r.nextInt(21));
                if (r.nextInt(2)==0) {
                    petName += "" + petName.charAt(petName.length()-1); //adding double letters
                i++;
                }
                petName += "" + VOWELS.charAt(r.nextInt(5));
                if (r.nextInt(2)==0) {
                    petName += "" + petName.charAt(petName.length()-1); //adding double letters
                i++;
                }

            }
        } //end for loop
        
        return petName;
    } //end generatePetName method
    
    //****************************************
    
    public static int[] generateStats() {
        Random r = new Random();
        final int STARTING_POOL = 20;
        int maxHealth, maxFood, maxEnergy;
        int remainingPool;
        
        int[] maxStats = new int[3];
        
        maxHealth = r.nextInt(STARTING_POOL) + 1;
        remainingPool = STARTING_POOL - maxHealth;
        maxFood = r.nextInt(remainingPool) + 1;
        remainingPool -= maxFood;
        maxEnergy = remainingPool;

        System.out.println("MAXHEALTH = " + maxHealth + " MAXFOOD = " + maxFood + " MAXENERGY = " + maxEnergy);
        
        maxStats[0] = maxHealth;
        maxStats[1] = maxFood;
        maxStats[2] = maxEnergy;
        
        return maxStats;
    } //end generateStats method
    
    //****************************************
    
    public static int game1() {
        Scanner kb = new Scanner(System.in);
        Random r = new Random();
        int moneyGame1 = 0;
        
        int targetNum = r.nextInt(100) + 1;
            for (int i=10;i>0;i-=2) {
                System.out.print("Guess: ");
                int userGuess = kb.nextInt();
                if (userGuess == targetNum) {
                    System.out.println("Congrats! You got it!");
                    moneyGame1 += i;
                    break;
                } else if ((userGuess != targetNum) && (i != 2)) {
                    System.out.println("Wrong number, try again!");
                } else if ((userGuess != targetNum) && (i == 2)) {
                    System.out.println("Out of guesses! End of game.");
                } //end if
            } //end loop
            
            System.out.println("The target number was " + targetNum);
            System.out.println("Money: " + moneyGame1);
            
            return moneyGame1;
    } //end game1 method
    
    //****************************************
    
    public static String shuffleString(String unshuffled) {
        Random r = new Random();
        String shuffled = "";
        
        for (int i=0;i<10;i++) {
            int ind = r.nextInt(unshuffled.length());
            shuffled += ""+ unshuffled.charAt(ind);
            unshuffled = unshuffled.substring(0,ind) + unshuffled.substring(ind+1);
        }
        
        return shuffled;
        
    } //end shuffleString method
    
    //****************************************
    
    public static int game2 (String revealed, String shuffled) {
        Scanner kb = new Scanner(System.in);
        int moneyGame2 = 0;
        do {
            System.out.println(revealed);
            System.out.print("Guess (a b): ");
            int guess1 = kb.nextInt();
            int guess2 = kb.nextInt();
            if ((guess1 < 10 && guess2 < 10) && (shuffled.charAt(guess1) == shuffled.charAt(guess2))) {
                revealed = revealed.substring(0,guess1)
                           + shuffled.charAt(guess1)
                           + revealed.substring(guess1+1,guess2)
                           + shuffled.charAt(guess2)
                           + revealed.substring(guess2+1);
                moneyGame2++;
            } else {
                System.out.println("Try again!");
            }

        } while (revealed.indexOf("X") != -1);
                
        return moneyGame2;
    } //end game2 method
    
    public static void storeInFile(String fileName, String username, String password, String petType, String petName, int[] maxStats, int[] currentStats, int money) {
        File f = new File(fileName);
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(f);
            
            pw.println("Username: " + username);
            pw.println("Password: " + password);
            pw.println("Pet type: " + petType);
            pw.println("Pet name: " + petName);
            pw.println("Max stats: " + Arrays.toString(maxStats));
            pw.println("Current stats: " + Arrays.toString(currentStats));
            pw.println("Money: " + money);
            
            pw.close();
        } catch (IOException e) {
            System.out.println("IOException occured.");
        }
    } //end storeInFile method
}