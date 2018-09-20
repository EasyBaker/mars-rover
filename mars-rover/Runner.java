/**
 * Write a description of class RoverRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Scanner;

public class Runner
{
    public static void main(String[] args) {
        // Make a SimpleScanner
        SimpleScanner input = new SimpleScanner();
        
        // Make some Rovers
        Rover r1 = new Rover("Hitler");
        Rover r2 = new Rover("Stalin", 10, -4, 2);
        
        // Make a RoverGroup and add Rovers
        RoverGroup group = new RoverGroup();
        group.add(r1);
        group.add(r2);
        
        // Now let's do stuff
        boolean running = true;
        String exitCommand = "quit";
        
        while (running) {
            // Input name
            System.out.print("Enter the name of the Rover to act: ");
            String name = input.readString();
            
            // Select Rover with matching name
            Rover actor = group.find(name);
            
            if (actor != null) {
                // If the rover is found
                System.out.print("Enter a command: ");
                String command = input.readString();
                
                if (command.equals("move")) {
                    System.out.print("Enter distance to move: ");
                    int distance = input.readInt();
                    actor.move(distance);
                }
                else if (command.equals("rotate")) {
                    System.out.print("Enter (-1, -7) to rotate left or (1, 7) to rotate right: ");
                    double rotation = input.readInt();
                    actor.rotate(rotation);
                }
                else if (command.equals("kill")) {
                    System.out.print("Enter the name the target rover: ");
                    String targetName = input.readString();
                    
                    Rover target = group.find(targetName);
                    
                    if (target != null) {
                        actor.kill(target);
                        System.out.print(target);
                    }
                    else {
                        System.out.print("Error: No such target.");
                    }
                }
                else if (command.equals("battle")) {
                    System.out.print("Enter the name the target rover: ");
                    String targetName = input.readString();
                    
                    Rover target = group.find(targetName);
                    
                    if (target != null) {
                        actor.battle(target);
                        System.out.println(target);
                    }
                    else {
                        System.out.print("Error: No such target.");
                    }
                }
                else if (command.equals("revive")) {
                    System.out.print("Enter the name the target rover: ");
                    String targetName = input.readString();
                    
                    Rover target = group.find(targetName);
                    
                    if (target != null) {
                        actor.revive(target);
                        System.out.print(target);
                    }
                    else {
                        System.out.print("Error: No such target.");
                    }
                }
                else if (command.equals("take picture")) {
                    actor.takePicture();
                }
                else if (command.equals("transmit picture")) {
                    actor.transmitPictures();
                }
                else if (command.equals("move to")) {
                    System.out.print("Enter the name the x value that you want to move to: ");
                    int x = input.readInt();
                    System.out.print("Enter the name the y value that you want to move to: ");
                    int y = input.readInt();
                    
                    actor.moveTo(x, y);
                }
                else if (command.equals("charge")) {   
                    System.out.print("Enter how much you want to charge up: ");
                    double charge = input.readDouble();
                    actor.charge(charge);
                }
                else if (command.equals("go home")) {
                    actor.goHome();
                }
                else {  
                    System.out.println("Error: Invalid command.");
                }
                
                System.out.println(actor);
            }
            else if (name.equals(exitCommand)) {
                running = false; // set flag to exit loop
            }
            else {
                System.out.println("Error: " + name + " doesn't exist.");
            }
            
            // just a blank line
            System.out.println();
        }
        
        System.out.println("Goodbye.");
    }
}
