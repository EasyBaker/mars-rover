
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
    private double dir;
    private double numPics;
    private double memory;
    private double energy;
    private double charge;
    private double health;
    private double damage;
    private double rotation;
    private double distancex;
    private double distancey;
    private double distancerotate;
    private double distancerotater;
    private double distancerotatel;
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

    public Rover(String name, double x, double y, double dir)
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
       if (isAlive && energy > 0) {
           dir += rotation;
           energy -= 1;
       } else {
           System.out.println(this.name + " can't rotate, it's ded.");
       }
        
       if (rotation < 0) {
           side = "left";
       } else if (rotation > 0) {
           side = "right";
       }
       
       if (dir == 8) {
           dir = 0;
       } else if (dir == -1) {
           dir = 7;
       }
       
       System.out.println(this.name + " turned to the " + side);
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
           energy -= 1;
           System.out.println(this.name + " moved forward");
       } else if (isAlive == false) {
           System.out.println(this.name + " can't move. It's ded.");
       } else if (energy == 0) {
           System.out.println(this.name + " can't move. It has no energy.");
        }
    }
    
    public void moveTo(int x, int y)
    {
        distancex = this.x - x;
        distancey = this.y - y;
        
        if (distancex > 0 && distancey > 0) {
            if (dir != 1) {
                distancerotate = Math.abs(dir - 8);
                if (distancerotate > 4) {
                    distancerotater = Math.abs(distancerotate - 8);
                    this.rotate(distancerotater);
                    System.out.println(this.name + " rotates " + distancerotater + " to the right");
                    while (this.x != x && this.y != y) {
                        this.move(distance);
                    }
                    if (this.x == x || this.y == y) {
                        this.moveTo(x, y);
                    }
                }
            }
        }
    }
    
    public void teleport(int x, int y)
    {
        this.x = x;
        this.y = y;
        System.out.println(this.name + " teleports to (" + x + "," + y + ").");
    }
    
    private void getDirectionName()
    {
        if (dir == 0) {
            this.direction = "North";
        } else if (dir == 1) {
            this.direction = "Northeast";
        } else if (dir == 2) {
            this.direction = "East";
        } else if (dir == 3) {
            this.direction = "Southeast";
        } else if (dir == 4) {
            this.direction = "South";
        } else if (dir == 5) {
            this.direction = "Southwest";
        } else if (dir == 6) {
            this.direction = "West";
        } else if (dir == 7) {
            this.direction = "Northwest";
        }
    }
    
    public void takePicture() {
        if (isAlive && energy > 0) {
            if (memory < 10) {
                numPics += 1;
                memory += 1;
                this.energy -= 1;
                System.out.println(this.name + " took a picture at [" + this.x + ", " + this.y + "] facing " + this.direction);
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
            } else if (isAlive == false) {
                System.out.println(this.name + " cannot attack " + other.name + " because it's ded.");
            } else if (energy == 0) {
                System.out.println(this.name + " cannot attack " + other.name + " because it has no energy.");
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
