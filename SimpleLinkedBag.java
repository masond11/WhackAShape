// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Mason Dooley (906230479)
/**
 * 
 */
package game;

import bag.Node;
import bag.SimpleBagInterface;
import student.TestableRandom;

/**
 * This class implements the SimpleBagInterface<T>
 * to create a linked based bag.
 * 
 * @author Mason Dooley
 * @version 2022.10.03
 * @param <T> Bag contains generics
 *
 */
public class SimpleLinkedBag<T> implements SimpleBagInterface<T> {
    
    //Fields
    private Node<T> firstNode;
    private int numberOfEntries;

    /**
     * Constructor for the SimpleLinkedBag() method.
     * Initializes the firstNode to null
     * and numberOfEntries to 0.
     */
    public SimpleLinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }
    
    /**
     * This method adds an object to the bag if it 
     * is not null
     * 
     * @param anEntry that will be added to the bag
     * @return boolean if anEntry was
     * successfully added to bag
     */
    @Override
    public boolean add(T anEntry) {
        //Checking to see if anEntry is null
        if (anEntry == null) {
            //Additional of anEntry was unsuccessful
            return false;
        }
        
        //Creating a new node with anEntry as the 
        //head that points to firstNode
        Node<T> temp = new Node<T>(anEntry, firstNode);
        
        //Setting firstNode to the temp Node; anEntry has
        //been added to firstNode
        firstNode = temp;
        numberOfEntries++;
        
        //Addition of anEntry to the bag was successful
        return true;
    }


    /**
     * @return int the current size of the bag
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
        
        if (isEmpty()) {
            //Bag is empty and there is no item to pick
            return null;
        }
        
        //Creating a new node that will be used to loop
        //through firstNode without altering firstNode
        Node<T> currentNode = firstNode;
        
        //Generating a random number that will determine
        //which bag entry is randomly picked
        TestableRandom generator = new TestableRandom();
        int index = generator.nextInt(numberOfEntries);
        
        //Looping through the link until the randomly 
        //generated index is reached
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        //Object at the randomly generated index that had been picked
        return currentNode.getData();
    }

    /**
     * Checking to see if an Object is in the Bag and if it
     * is, what node's data contains the Object.
     * 
     * @param anEntry Object that is being checked
     * @return Node<T> Data contains the object.
     */
    private Node<T> getReferenceTo(T anEntry) {
        //Assuming that anEntry will not be found in the bag
        boolean found = false;
        
        //Creating a new node that will be used to loop
        //through firstNode without altering firstNode
        Node<T> currentNode = firstNode;
        
        //Will either loop through the entire linked chain, or until
        //a Node's data is equal to anEntry.
        while (!found && currentNode != null) {
            
            if (currentNode.getData() == anEntry) {
                //current Node's data is equal to anEntry; 
                //found is set to true to stop looping
                found = true;
            }
            else {
                //anEntry has not been found in the bag, and
                //looping through the linked chain must continue
                currentNode = currentNode.getNext();
            }
        }
        //currentNode is either equal to the Node where anEntry was
        //located, or is equal to null indicating that anEntry 
        //does not exist in the bag
        return currentNode;
        
    }
    
    /**
     * Removing a specific entry from the bag. Will utilize
     * the private method of getReferenceTo()
     * 
     * @param anEntry Object that will be attempted to 
     * be removed from the bag
     * @return boolean True if anEntry is successful removed
     * from the bag
     */
    @Override
    public boolean remove(T anEntry) {
        //Creating a new Node that is equal to the
        //node whose data is equal to anEntry
        Node<T> temp = getReferenceTo(anEntry);
        
        if (temp == null) {
            //anEntry was not found in the bag, so removal is
            //unsuccessful
            return false;
        }
        
        //Replacing the Node where anEntry is located with
        //FirstNode's data. AnEntry no longer exists in the bag
        temp.setData(firstNode.getData());       
        
        //Remove firstNode
        firstNode = firstNode.getNext();
        numberOfEntries--;
        
        //Removal of anEntry was successful
        return true;
    }
}
