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
        
        //Variable initialization
        boolean chosenPet = false;
        String petName = "";
        
        //Part 1: print splash screen
        System.out.println("           __..--''``---....___   _..._    __");
        System.out.println(" /// //_.-'    .-/\";  `        ``<._  ``.''_ `. / // /");
        System.out.println("///_.-' _..--.'_    \\                    `( ) ) // //");
        System.out.println("/ (_..-' // (< _     ;_..__               ; `' / ///");
        System.out.println(" / // // //  `-._,_)' // / ``--...____..-' /// / //");
        System.out.println("\nComputerized Companions");
        
        //Part 3: read user login
        System.out.print("Enter username: ");
        String userUsername = kb.nextLine();
        System.out.print("Enter password: ");
        String userPass = kb.nextLine();
        
        //Part 3: determine if the login matches
        if (!(userUsername.equals(USERNAME)) || !(userPass.equals(PASSWORD))) { 
            System.exit(0);
        }
        
        //Part 1: print menu
        System.out.println("\nMenu:");
        System.out.println("1. Start");
        System.out.println("2. Instructions");
        System.out.println("3. Exit");
        //Read user selection
        System.out.print("Select option (int or all lowercase word): ");
        String menuSelection = kb.next();
        
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
                break;
            case "2", "instructions": break;
            case "3", "exit": System.exit(0); break; //Ends the program
            default: break;
        } //end switch case
        
        //Part 4: read or generate pet name
        if (chosenPet == true) {
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
                        petName += "" + VOWELS.charAt(r.nextInt(5)) + CONSONANTS.charAt(r.nextInt(21));
                    } else {
                        petName += "" + CONSONANTS.charAt(r.nextInt(21)) + VOWELS.charAt(r.nextInt(5));
                    }
                } //end for loop
            } //end if
            System.out.println("Your pet, named " + petName + ", has been born!");
        }
        
    }
    
}
