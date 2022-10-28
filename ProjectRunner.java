// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mason Dooley (906230479)
package game;

/**
 * ProjectRunner for the WhackAShape game
 * 
 * @author Mason Dooley
 * @version 2022.10.03
 *
 */
public class ProjectRunner {

    /**
     * This method is called when the program runs.
     * It will call one of the two constructors in the 
     * WhackAShape class depending on if the parameter
     * is empty
     * 
     * @param args Array of Strings that will be
     * used for the WhackAShape game
     */
    public static void main(String[] args)  {
            if (args.length == 0) {
                new WhackAShape();
            }
            else {
                new WhackAShape(args);
            }
        }
}
