
/**
 * Write a description of class Runner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Runner extends Runner
{
    public static void main(String[] args) {
        Rover r1 = new Rover("Hitler");
        Rover r2 = new Rover("Stalin", 10, -4, 2);
        System.out.println(r1.toString());
        r1.charge(3);
        r1.rotate(1);
        r1.move(5);
        System.out.println(r1);
        r1.takePicture();
        r1.transmitPictures();
        r1.battle(r2);
        ///r2.rotate(1);
        ///System.out.println(r2);
        ///r1.move();
        ///System.out.println(r1);
        ///r2.rotate(1);
        ///System.out.println(r2);
        ///r2.rotate(-1);
        ///System.out.println(r2);
        ///r1.rotate(3);
        ///System.out.println(r1);
        
        ///r1.kill(r2);
        ///System.out.println(r1);
        ///System.out.println(r2);
        
        ///r1.revive(r2);
        ///System.out.println(r1);
        ///System.out.println(r2);
        
        ///r1.move();        
        ///System.out.println(r1);
        
        ///r1.teleport(5, 10);
        ///System.out.println(r1);
    }
}
