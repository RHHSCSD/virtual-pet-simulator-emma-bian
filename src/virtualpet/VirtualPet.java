/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
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
        
        //CONSTANTS
        final String USERNAME = "snoopy";
        final String PASSWORD = "toto";
        final int NAME_LENGTH = (r.nextInt(5)+4);
        final String VOWELS = "aeiou";
        final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
        final int STARTING_POOL = 20;
        
        //Variable initialization
        String menuSelection = "", menuSelection2 = "";
        String userUsername = "", userPass = "";
        boolean chosenPet = false;
        String petName = "";
        boolean namedPet = false;
        int maxHealth, maxFood, maxEnergy;
        int remainingPool;
        boolean playGame = false;
        int gameChoice;
        int money = 0;
        String unshuffled = "AABBCCDDEE";
        String shuffled = "";
        String revealed = "XXXXXXXXXX";
        
        //Part 1: print splash screen
        System.out.println("           __..--''``---....___   _..._    __");
        System.out.println(" /// //_.-'    .-/\";  `        ``<._  ``.''_ `. / // /");
        System.out.println("///_.-' _..--.'_    \\                    `( ) ) // //");
        System.out.println("/ (_..-' // (< _     ;_..__               ; `' / ///");
        System.out.println(" / // // //  `-._,_)' // / ``--...____..-' /// / //");
        System.out.println("\nComputerized Companions");
        
        //Part 3: read user login; Part 5: give 3 chances
        for (int i=0;i<3;i++) {
            System.out.print("Enter username: ");
            userUsername = kb.nextLine();
            System.out.print("Enter password: ");
            userPass = kb.nextLine();
            if (userUsername.equals(USERNAME) && userPass.equals(PASSWORD)) {
                break;
            }
        }
        
        //Part 3: determine if the login matches
        if (!(userUsername.equals(USERNAME)) || !(userPass.equals(PASSWORD))) { 
            System.exit(0);
        }
        
        //Part 1: print menu
        while (true) {
            if (namedPet == false) {
                System.out.println("\nMenu:");
                System.out.println("1. Start");
                System.out.println("2. Instructions");
                System.out.println("3. Exit");
                //Read user selection
                System.out.print("Select option (int or all lowercase word): ");
                menuSelection = kb.next();
            } else {
                System.out.println("\nMenu:");
                System.out.println("1. Play/Interact");
                System.out.println("2. Instructions");
                System.out.println("3. Exit");
                //Read user selection
                System.out.print("Select option (int or all lowercase word): ");
                menuSelection2 = kb.next();
            }

            //Part 2: make decisions based on input selection
            switch (menuSelection) {
                case "1","start":
                    System.out.println("Select a pet from the list: "); //Displays selection of pets
                    System.out.println("Cat");
                    System.out.println("Fish");
                    System.out.println("Rabbit");
                    System.out.print("Selection: ");
                    String pet = kb.next(); //Read user selection
                    System.out.println("You have selected " + pet); //Confirm selection
                    chosenPet = true;
                    menuSelection = "";
                    break;
                case "2", "instructions": menuSelection = ""; break;
                case "3", "exit": menuSelection = ""; System.exit(0); break; //Ends the program
                default: break;
            } //end switch case
            
            
            //Part 5.2: print main menu
            switch (menuSelection2) {
                case "1","play","interact":
                    playGame = true;
                    break;
                case "2", "instructions": break;
                case "3", "exit": System.exit(0); break; //Ends the program
                default: break;
            } //end switch case

            //Part 4: read or generate pet name
            if (chosenPet == true && namedPet == false) {
                System.out.print("Type in a name (1) or generate random name (2): ");
                int userNamingChoice = kb.nextInt();
                if (userNamingChoice == 1) { //allow user input name
                    System.out.print("Enter pet name: ");
                    kb.nextLine(); //clear the return
                    petName = kb.nextLine();
                } else if (userNamingChoice == 2) { //generate random name
                    int startLetterIden = r.nextInt(2); //statring with consonant or vowel
                    for (int i=0;i<NAME_LENGTH;i+=2) {
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
                } //end if
                namedPet = true;
                System.out.println("Your pet, named " + petName + ", has been born!");
            } //end if

            //Part 5: generate pet stats for named pets
            if (namedPet == true && playGame == false) {
                maxHealth = r.nextInt(STARTING_POOL) + 1;
                remainingPool = STARTING_POOL - maxHealth;
                maxFood = r.nextInt(remainingPool) + 1;
                remainingPool -= maxFood;
                maxEnergy = remainingPool;

                System.out.println("MAXHEALTH = " + maxHealth + " MAXFOOD = " + maxFood + " MAXENERGY = " + maxEnergy);
            } //end if
            
            if (playGame == true) {
                do {
                    System.out.println("Game 1: Number Guessing Game");
                    System.out.println("Game 2: Matching Game");
                    System.out.print("Selection (1 or 2, -1 to quit): ");
                    gameChoice = kb.nextInt();
                    
                    if (gameChoice == 1) { //number guessing game
                        int targetNum = r.nextInt(100) + 1;
                        for (int i=10;i>0;i-=2) {
                            System.out.print("Guess: ");
                            int userGuess = kb.nextInt();
                            if (userGuess == targetNum) {
                                System.out.println("Congrats! You got it!");
                                money += i;
                                break;
                            } else if ((userGuess != targetNum) && (i != 2)) {
                                System.out.println("Wrong number, try again!");
                            } else if ((userGuess != targetNum) && (i == 2)) {
                                System.out.println("Out of guesses! End of game.");
                            } //end if
                        } //end loop
                        System.out.println("The target number was " + targetNum);
                        System.out.println("Money: " + money);
                    
                    } else if (gameChoice == 2) { //matching game
                        //suffle letters
                        shuffled = suffleString(unshuffled);
                        
                        //reveal letters as player guesses
                        do {
                            System.out.println(revealed);
                            System.out.print("Guess (a b): ");
                            int guess1 = kb.nextInt();
                            int guess2 = kb.nextInt();
                            if ((guess1 < 9 && guess2 < 9) && (shuffled.charAt(guess1) == shuffled.charAt(guess2))) {
                                revealed = revealed.substring(0,guess1)
                                        + shuffled.charAt(guess1)
                                        + revealed.substring(guess1+1,guess2)
                                        + shuffled.charAt(guess2)
                                        + revealed.substring(guess2+1);
                                money++;
                            } else {
                                System.out.println("Try again!");
                            }
                        } while (revealed.indexOf("X") != -1);
                        System.out.println("Money: " + money);
                    }
                } while (gameChoice != -1);
            } //end if

        } //end loop

        
    } //end main method
    
    public static String suffleString(String unshuffled) {
        Random r = new Random();
        String shuffled = "";
        for (int i=0;i<10;i++) {
            int ind = r.nextInt(unshuffled.length());
            shuffled += ""+ unshuffled.charAt(ind);
            unshuffled = unshuffled.substring(0,ind) + unshuffled.substring(ind+1);
        }
        return shuffled;
        
    }

   
}
