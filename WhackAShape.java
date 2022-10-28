// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mason Dooley (906230479)
package game;

import java.awt.Color;
import bag.SimpleBagInterface;
import cs2.Button;
import cs2.CircleShape;
import cs2.Shape;
import cs2.SquareShape;
import cs2.TextShape;
import cs2.Window;
import cs2.WindowSide;
import student.TestableRandom;

/**
 * This class creates a window that will display
 * shapes that when clicked are removed from the screen
 * 
 * @author Mason Dooley
 * @version 2022.10.03
 *
 */
public class WhackAShape {

    //Fields
    private SimpleBagInterface<Shape> bag;
    private Window window;
    private TestableRandom randomGenerator;
    private Button quitButton;
    
    /**
     * Constructor for the WhackAShape class.
     * Initializes the 4 fields, adds a Quit button,
     * and fills a bag with shapes
     */
    public WhackAShape() {       
        //Initializing the bag, window, and randomGenerator
        bag = new SimpleArrayBag<Shape>();
        window = new Window();
        randomGenerator = new TestableRandom();
        
        //Initializing clickedQuit and adding it to the window
        quitButton = new Button("Quit");  
        quitButton.onClick(this, "clickedQuit");
        window.addButton(quitButton, WindowSide.EAST);
        
        //Creating an array of Strings that are shapes that
        //will be used to fill the bag
        String[] shapes = new String[]{"red circle", "blue circle", 
            "red square", "blue square"};
        
        //A random number is generated to determine the bag's size
        //Will be between 6 and 14.
        int size = randomGenerator.nextInt(9);
        size = size + 6;
        
        //Looping through the bag and converting a random String from 
        //the array shapes[] to a shape and adding it to the bag
        for (int i = 0; i < size; i++) {
            bag.add(buildShape(shapes[randomGenerator.nextInt(shapes.length)]));
        }
        //Adding the first shape to the window
        window.addShape(bag.pick());
    }
    
    /**
     * Second constructor for the WhackAShape class.
     * Initializes the 4 fields, adds a quit button,
     * and fills the bag with shapes.
     * @param inputs String array that will be converted into shapes 
     */
    public WhackAShape(String[] inputs) {
        //Initializing the bag, window, and randomGenerator
        window = new Window();
        bag = new SimpleArrayBag<Shape>();
        randomGenerator = new TestableRandom();
        
        //Initializing clickedQuit and adding it to the window
        quitButton = new Button("Quit");  
        quitButton.onClick(this, "clickedQuit");
        window.addButton(quitButton, WindowSide.EAST);
        
        //Looping through each entry in inputs, and converting the 
        //valid Strings to shapes and adding them to the bag.
        //Illegal Argument Exception will be thrown for invalid Strings
        for (int i = 0; i < inputs.length; i++) {
            try {
                bag.add(buildShape(inputs[i]));
            }
            //a String from inputs is an invalid Shape.
            catch (IllegalArgumentException nfe) {
                nfe.printStackTrace();
            }
        }
        //Adding the first shape to the window
        window.addShape(bag.pick());
    }
    
    /**
     * This method converts Strings into a shape
     * 
     * @param input String that is being converted to a shape
     * @return Shape input String that has been converted
     */
    private Shape buildShape(String input) {
        //Generating a random size for the shape
        int size = randomGenerator.nextInt(101);
        size = size + 100;
        
        //Generating a random coordinate on the window for the shape
        int x = randomGenerator.nextInt(window.getGraphPanelWidth() + 1);
        int y = randomGenerator.nextInt(window.getGraphPanelHeight() + 1);
        
        //Determining is the shape is falling off the edge of the window
        //and adjusting it if it is by adding or subtracting the size
        if (x + size/2 >= window.getGraphPanelWidth()) {
            x = x - size;
        }
        if (y + size/2 >= window.getGraphPanelHeight()) {
            y = y - size;          
        }
        if (x - size/2 <= 0) {
            x = x + size;
        }
        if (y - size/2 <= 0) {
            y = y + size;
        }
           
        //Creating a shape object, will not be initialized until it is known
        //is the shape is a circle or square
        Shape currentShape = null;
        
        //Checking the String to see if it is a legal shape. If it is not,
        //an illegal argument exception is thrown. If it is, currentShape
        //is initialized to the input's shape
        //and specified as a circle or square
        if (input.contains("blue") && input.contains("circle")) {
            currentShape = new CircleShape(x, y, Color.blue);
        }
        else if (input.contains("blue") && input.contains("square")) {
            currentShape = new SquareShape(x, y, Color.blue);
        }
        else if (input.contains("red") && input.contains("square")) {
            currentShape = new SquareShape(x, y, Color.RED);
        }
        else if (input.contains("red") && input.contains("circle")) {
            currentShape = new CircleShape(x, y, Color.RED);
        }
        else {
            throw new IllegalArgumentException();
        }
        //When the current shape is clicked,
        //the clickedShape() method is called
        currentShape.onClick(this, "clickedShape");
        
        //The shape that was created from the input String
        return currentShape;
    }
    
    /**
     * This method is called when a shape is clicked. It will
     * remove the shape from the window and either display another
     * shape from the bag, or display "You Win!" indicating that
     * the bag is empty
     * @param shape Shape that has been clicked
     */
    public void clickedShape(Shape shape) {
        //Removes shape from the window
        window.removeShape(shape);
        
        //Removes shape from bag
        bag.remove(shape);
        
        //A new Shape is created and set to a random shape
        //that has been picked from the bag
        Shape nextShape = bag.pick();
        
        //Checking to see if any shapes are left in the bag
        if (nextShape == null) {
            //Bag is empty and the game is over
            TextShape textShape = new TextShape(100, 100, "You Win!");
            
            //Setting the "You Win!" text to the middle of the screen
            int middlePanelX = window.getGraphPanelWidth()/2;
            int middlePanelY = window.getGraphPanelHeight()/2;
            int middleTextX = textShape.getWidth()/2;
            int middleTextY = textShape.getHeight()/2;
            int middleXCoord = middlePanelX - middleTextX;
            int middleYCoord = middlePanelY - middleTextY;       
            textShape.moveTo(middleXCoord, middleYCoord);
            textShape.setForegroundColor(Color.BLACK);
            window.addShape(textShape);
        }
        else {
            //nextShape is added to the window. Game continues
            window.addShape(nextShape);
        }
    }
    
    /**   
     * Will exit out of the Window when "quit" is clicked
     * @param button quit button that had been pressed  
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }
    
    /**
     * Getter method for window
     * 
     * @return the current window
     */
    public Window getWindow() {
        return window;
    }
    
    /**
     * Getter method for the bag
     * 
     * @return the current bag
     */
    public SimpleBagInterface<Shape> getBag() {
        return bag;
    }
}
