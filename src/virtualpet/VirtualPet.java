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
        // TODO code application logic here
        
        //Part 1: print splash screen
        System.out.println("           __..--''``---....___   _..._    __");
        System.out.println(" /// //_.-'    .-/\";  `        ``<._  ``.''_ `. / // /");
        System.out.println("///_.-' _..--.'_    \\                    `( ) ) // //");
        System.out.println("/ (_..-' // (< _     ;_..__               ; `' / ///");
        System.out.println(" / // // //  `-._,_)' // / ``--...____..-' /// / //");
        System.out.println("\nComputerized Companions");
        
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
                break;
            case "2", "instructions": break;
            case "3", "exit": System.exit(0); break; //Ends the program
            default: break;
        } //end switch case
        
    }
    
}
