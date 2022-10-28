// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mason Dooley (906230479)
package game;

import bag.SimpleBagInterface;
import student.TestableRandom;

/**
 * This class implements the SimpleBagInterface<T>
 * to create an array based bag.
 * 
 * @author Mason Dooley
 * @version 2022.10.03
 * @param <T> Bag contains generics
 *
 */
public class SimpleArrayBag<T> implements SimpleBagInterface<T> {
    
    //Fields
    private T[] bag;
    private int numberOfEntries;
    private static final int MAX = 18;

    /**
     * Constructor for the SimpleArrayBag() method.
     * Initializes the bag field to an array of generic
     * and numberOfEntries to 0.
     */
    public SimpleArrayBag()
    {
        @SuppressWarnings("unchecked")
        T[] tempbag = (T[]) new Object[MAX];
        bag = tempbag;
        numberOfEntries = 0;
    }
    
    /**
     * This method adds an object to the bag if it 
     * is not null and the bag is not full
     * 
     * @param anEntry that will be added to the bag
     * @return boolean if anEntry was
     * successfully added to bag
     * 
     */
    @Override
    public boolean add(T anEntry) {
        //Checking if anEntry is null and if the bag is full
        if (anEntry == null || numberOfEntries >= MAX)
        {
            //Additional of anEntry was unsuccessful
            return false;
        }
        
        //Adds anEntry to the end of the bag array
        bag[numberOfEntries] = anEntry;
        numberOfEntries++;
        return true;
    }

    /**
     * @return int The current size of the bag
     */
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    /**
     * @return boolean true if the bag is Empty
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    /**
     * Picks a random object from the bag
     * @return T the object that was randomly picked
     */
    @Override
    public T pick() {    
        
        if (isEmpty())
        {
            //Bag is empty and there is no item to pick
            return null;
        }
        
        //Generates a random number between 0 and the amount of
        //entries in the bag (numberOfEntries)
        TestableRandom generator = new TestableRandom();
        int index = generator.nextInt(numberOfEntries);
        
        //Object at randomly generated index is returned
        return bag[index];  
    }
    
    /**
     * Checking to see if an Object is in the Bag and if it
     * is, what index the Object is at.
     * 
     * @param anEntry Object that is being checked
     * @return int the index of the Object
     */
    private int getIndexOf(T anEntry) {
        //Loop through the bag's Objects
        for (int i = 0; i < numberOfEntries; i++) {
            //Check to see if current bag entry is equal to anEntry
            if (bag[i].equals(anEntry)) {
                //Current bag entry equals anEntry, so returning
                //the current bag index
                return i;
            }
        }
        //Looped through all the entries without finding anEntry in 
        //the bag.
        return -1;
        
    }

    /**
     * Removing a specific entry from the bag.
     * 
     * @param anEntry Object that will be attempted to 
     * be removed from the bag
     * @return boolean True if anEntry is successfully removed
     * from the bag
     */
    @Override
    public boolean remove(T anEntry) {
        
        //Locating the index of anEntry in the bag
        int index = getIndexOf(anEntry);
        
        //Checking to see if anEntry is in the bag and
        //therefore able to be removed
        if (index == -1) {
            //Removal of anEntry was unsuccessful
            return false;
        }
        
        //Setting the bag at anEntry's index to null, removing
        //it from the bag
        bag[index] = null;
        bag[index] = bag[numberOfEntries - 1];
        
        //Setting the last entry in the bag to null
        bag[numberOfEntries - 1] = null;
        numberOfEntries--;
        
        //Removal of anEntry was successful
        return true;
        
    }

    
}
