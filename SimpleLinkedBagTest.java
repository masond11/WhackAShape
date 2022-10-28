// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mason Dooley (906230479)
package game;

import student.TestCase;
import student.TestableRandom;

/**
 * Test class for the implementation of an 
 * linked based bag in the SimpleLinkedBag class
 * 
 * @author Mason Dooley
 * @version 2022.10.03
 *
 * @param <T> Bag contains generics
 */
public class SimpleLinkedBagTest<T> extends TestCase {

    //Fields
    private SimpleLinkedBag<T> bag;
    private T testObject;

    /**
     * The setUp() method runs before each test method
     * and creates a new SimpleLinkedBag and a testObject
     */
    @SuppressWarnings("unchecked")
    public void setUp() {
        bag = new SimpleLinkedBag<T>();
        testObject = (T) new Object();
    }
    
    /**
     * Test method for the add() method.
     * Tests that when trying to pass a null object
     * into the bag's add method, that false is returned.
     */
    public void testAdd() {
        assertFalse(bag.add(null));
    }
    
    /**
     * Test method for the add() method.
     * Also tests the currentSize() method
     * Tests that an object can be added to the bag,
     * and that the size increases upon adding the object
     */
    public void testAdd2() {      
        assertTrue(bag.add(testObject));        
        assertEquals(bag.getCurrentSize(), 1);
    }
    
    /**
     * Test method for the isEmpty() method.
     * The bag is initially empty, and isEmpty()
     * should return true, but when the testObject
     * is added, isEmpty() should return false.
     */
    public void testIsEmpty() {
        //Initially Empty
        assertTrue(bag.isEmpty());
        
        //testObject added isEmpty() is no longer true
        bag.add(testObject);
        assertFalse(bag.isEmpty());  
    }
    
    /**
     * Test method for pick() method.
     * Tests that when trying to pick
     * from an empty bag, NULL is returned.
     */
    public void testPick() {
        assertNull(bag.pick());
    }
    
    /**
     * Test method for the pick() method.
     * Tests that when adding 3 different testObjects
     * to the bag, and setting the next random integers,
     * the proper object is picked.
     */
    @SuppressWarnings("unchecked")
    public void testPick2() {
        //Declaring 2 new testObjects
        T testObject2 = (T) new Object();
        T testObject3 = (T) new Object();
        
        //Adding all the testObjects to the bag
        bag.add(testObject);
        bag.add(testObject2);
        bag.add(testObject3);
        
        //Order of testObjects picked should be in
        //reverse order in which they were added
        TestableRandom.setNextInts(0, 1, 2);
        assertEquals(bag.pick(), testObject3);
        assertEquals(bag.pick(), testObject2);
        assertEquals(bag.pick(), testObject);
    }
    
    /**
     * Test method for the remove() method. 
     * Will also test the getIndexOf() method.
     * Tests that when adding 3 testObjects and passing
     * in one of the objects in the remove() method call,
     * that the proper testObject is removed.
     */
    @SuppressWarnings("unchecked")
    public void testRemove() {
        //Declaring 2 new testObjects
        T testObject2 = (T) new Object();
        T testObject3 = (T) new Object();
        
        //Adding all the testObjects to the bag
        bag.add(testObject);
        bag.add(testObject2);
        bag.add(testObject3);
        
        //Removing one of the testObjects should return 
        //true, and the currentSize() should decrease by 1.
        assertEquals(bag.getCurrentSize(), 3);
        assertTrue(bag.remove(testObject));
        assertEquals(bag.getCurrentSize(), 2);
    }
    
    /**
     * Test method for the remove() method
     * Tests that when trying to remove a testObject
     * that is not in the bag, false is returned.
     */
    @SuppressWarnings("unchecked")
    public void testRemove2() {
        //Declaring 2 new testObjects
        T testObject2 = (T) new Object();
        T testObject3 = (T) new Object();
        
        //Adding only 2 of the testObjects to the bag
        bag.add(testObject);
        bag.add(testObject2);
        
        //Removing testObject3 which was not added should return 
        //false, and the currentSize() should remain 2.
        assertEquals(bag.getCurrentSize(), 2);
        assertFalse(bag.remove(testObject3));
        assertEquals(bag.getCurrentSize(), 2);
    }
}
