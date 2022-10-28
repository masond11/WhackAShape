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
 * array based bag in the SimpleArrayBag class
 * 
 * @author Mason Dooley
 * @version 2022.10.03
 * @param <T> Bag contains generics
 *
 */
public class SimpleArrayBagTest<T> extends TestCase {

    //Fields
    private SimpleArrayBag<T> bag;
    private T testObject;
    private static final int MAX = 18;
    
    /**
     * The setUp() method runs before each test method
     * and creates a new SimpleArrayBag and a testObject
     */
    @SuppressWarnings("unchecked")
    public void setUp()
    {
        bag = new SimpleArrayBag<T>();
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
     * Tests that up to 18 testObjects can be added into the bag,
     * and the bag will return true every time. When trying to add
     * a 19th object to the bag, the method should return false, and
     * the currentSize() method should return 18.
     */
    public void testAdd2() {
        //Adding 18 testObjects
        for (int i = 0; i < MAX; i++) {
            assertTrue(bag.add(testObject));
        }
        assertEquals(bag.getCurrentSize(), MAX);
        
        //Trying to add a 19th test object
        assertFalse(bag.add(testObject));
        assertEquals(bag.getCurrentSize(), MAX);
    }
    
    /**
     * Test method for the isEmpty() method.
     * The bag is initially empty, and isEmpty()
     * should return true, but when the testObject
     * is added, isEmpty() should return false.
     */
    public void testIsEmpty() {
        //Initially empty
        assertTrue(bag.isEmpty());
        
        //Adding a testObject, bag is no longer empty
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
        
        //Order of testObjects picked should be the
        //order in which they were added
        TestableRandom.setNextInts(0, 1, 2);
        assertEquals(bag.pick(), testObject);
        assertEquals(bag.pick(), testObject2);
        assertEquals(bag.pick(), testObject3);
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
        //Declaring 2 new test objects
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
     * that is not in the bag, false is returned, and the
     * numberOfEntries remains unchanged.
     */
    @SuppressWarnings("unchecked")
    public void testRemove2() {
        //Declaring 2 new test objects
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
