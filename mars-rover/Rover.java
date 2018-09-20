
/**
 * Write a description of class Rover here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rover
{
    // instance variables
    private double x;
    private double y;
    private double distance;
    private int dir;
    private double numPics;
    private double memory;
    private double energy;
    private double charge;
    private double health;
    private double damage;
    private double rotation;
    private String name;
    private String direction;
    private String side;
    private boolean isAlive;

    public Rover(String name)
    {
        // initialise instance variables
        this.x = 0;
        this.y = 0;
        this.dir = 0;
        this.numPics = 0;
        this.name = name;
        this.health = 10;
        this.energy = 10;
        this.isAlive = true;
    }

    public Rover(String name, double x, double y, int dir)
    {
        this.x = x;
        this.y = y;
        this.numPics = 0;
        this.dir = dir;
        this.name = name;
        this.health = 10;
        this.energy = 10;
        this.isAlive = true;
    }
    
    public void rotate(double rotation)
    {
       if(energy > 0){
            if(isAlive) {
                this.dir += rotation;
                energy -= 1;
                if (this.dir >= 8) {
                    this.dir = (dir % 8);
                    System.out.println(name + " turned to the right " + Math.abs(rotation) + " to face " + getDirectionName() + "."); 
                } else if (this.dir < 0) {
                    this.dir = 8 - (Math.abs(dir) % 8);
                    System.out.println(name + " turned to the left " + Math.abs(rotation) + " to face " + getDirectionName() + "."); 
                } else {
                    System.out.println(name + " turned to the right " + Math.abs(rotation) + " to face " + getDirectionName() + "."); 
                }
            }
            else {
                System.out.println(this.name + " cannot rotate, it's ded.");
            }
        } else System.out.println(this.name + " cannot rotate, it has no energy.");
    }
    
    public void move(double distance)
    {
       if (isAlive && energy > 0) {
           if (dir == 0) {
               y += distance;
           } else if (dir == 1) {
               x += distance;
               y += distance;
           } else if (dir == 2) {
               x += distance;
           } else if (dir == 3) {
               x += distance;
               y -= distance;
           } else if (dir == 4) {
               y -= distance;
           } else if (dir == 5) {
               x -= distance;
               y -= distance;
           } else if (dir == 6) {
               x -= distance;
           } else if (dir == 7) {
               x -= distance;
               y += distance;
           }
           energy -= Math.abs(distance);
           System.out.println(this.name + " moved forward");
       } else if (isAlive == false) {
           System.out.println(this.name + " can't move. It's ded.");
       } else if (energy == 0) {
           System.out.println(this.name + " can't move. It has no energy.");
        }
    }
    
    /** this function takes an integer and rotates the rover to move to the given point as efficiently as possible
     * @param moveTo is the function which takes the x and y coordinate and moves the rover as efficiently as possible to the desired x and y value.
     */
     public void moveTo(int x, int y) { 
            rotate(-this.dir);
            move(y - this.y);
            rotate(2);
            move(x - this.x);
    }
    
    /** this function initiates the rover to take the most efficient way back home using the moveTo function above.
     * @param moveTo is the function which takes the x and y coordinate and moves the rover as efficiently as possible to the desired x and y value.
     * @param goHome makes the rover efficiently move to the coordinate (0,0)
     */
    public void goHome() { 
        moveTo(0, 0);
        System.out.println(name + " went home ");
    }
    
    public void teleport(int x, int y)
    {
        this.x = x;
        this.y = y;
        System.out.println(this.name + " teleports to (" + x + "," + y + ").");
    }
    
    /** this allows the program to output a cardinal direction onto the screen given the input the computer received and calculated in the rotation function.
     * directionArray are the cardinal directions allowed in the rover.
     */
    private String getDirectionName() { 
          String[] directionArray = {"North", "Northeast", "East", "Southeast", "South", "Southwest", "West", "Northwest"};
        return directionArray[this.dir];
    }
    
    public void takePicture() {
        if (isAlive && energy > 0) {
            if (memory < 10) {
                numPics += 1;
                memory += 1;
                this.energy -= 1;
                System.out.println(this.name + " took a picture at [" + this.x + ", " + this.y + "] facing " + getDirectionName());
            } else {
                System.out.println(this.name + " cannot take a picture, it has no spare memory.");
            }
        } else if (isAlive == false) {
            System.out.println(this.name + " cannot take a picture, it's ded.");
        } else if (energy == 0) {
            System.out.println(this.name + " can't move. It has no energy.");
        }
    }
    
    public void transmitPictures() {
        if (isAlive && energy > 0) {
            numPics = 0;
            memory = 0;
            this.energy -= 1;
            System.out.println(this.name + " transmitted its pictures back to earth.");
        } else if (isAlive == false) {
            System.out.println(this.name + " cannot take a picture, it's ded.");
        } else if (energy == 0) {
            System.out.println(this.name + " can't move. It has no energy.");
        }
    }
    
    public void charge(double charge) {
        energy += charge;
 
        System.out.println(this.name + " charges up " + charge + " energy.");
    }
    
    public void battle(Rover other) {
        double damage = 4 * Math.random();
        damage = (int)(damage);
        
        if (isAlive && energy > 0) {
            if (other.isAlive) {
                health -= damage;
                this.energy -= 1;
                System.out.println(this.name + " attacks " + other.name + " and does " + damage + " damage.");
            } else if (isAlive == false) {
                System.out.println(other.name + " cannot be attacked, because it's ded.");
            } else if (energy == 0) {
                System.out.println(other.name + " cannot be attacked, because it's out of energy.");
            } else if (other.isAlive == false) {
                System.out.println(this.name + " cannot attack " + other.name + " because it's ded.");
            } else if (other.energy == 0) {
                System.out.println(other.name + " cannot be attacked because it has no energy and that's just not fair.");
            }
        }
    }
    
    public void kill(Rover other) {
        System.out.println(this.name + " shoots " + other.name + " with space lasers.");
        System.out.println(other.name + " goes 'beep beep aaaaaaakkkkkk!' and dies");
        
        this.energy -= 1;
        other.isAlive = false;
    }
    
    public void revive(Rover other) {
        System.out.println(this.name + " revives " + other.name + " with magik.");
        System.out.println(other.name + " goes 'beep bop eeeeeeeeekkkkkk!' and comes back to life");
        this.energy -= 1;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        getDirectionName();
        return "Rover[name=" + name + ",x=" + x + ",y=" + y + 
               ",dir=" + direction + ",isAlive=" + isAlive + ",energy=" + energy + 
               "]";
    }
}
