package ex3_2;

/*******************************************************************************************
FILE:           ArrayQueue.java
AUTHER:         Seth Hall
DESCRIPTION:    Implementation of a Circular Queue for ex 3.2
*******************************************************************************************/ //
import java.util.Collection;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements QueueADT<E>
{
   private final int INITIAL_CAPACITY = 5;
   private int numElements;
   private E[] elements;
   private int head;
   private int tail;
   // default constructor that creates a new stack
   // that is initially empty

   public ArrayQueue()
   {  numElements = 0;
      elements = (E[])(new Object[INITIAL_CAPACITY]); // unchecked
      head = 0;
      tail = 0;
   }

   // constructor for creating a new stack which
   // initially holds the elements in the collection c
   public ArrayQueue(Collection<? extends E> c)
   {  this();
      for (E element : c)
         enqueue(element);
   }

   // Adds one element to the top of this stack
   public void enqueue(E element)
   {  if (numElements >= elements.length)
         expandCapacity();
        
      elements[tail] = element;
      numElements++;
      tail = (tail+1) % elements.length;
 
   }

   // removes and returns the top element from this stack
   @Override
   public E dequeue() throws NoSuchElementException
   {  if (numElements > 0)
      {
        E frontElement = elements[head];
        elements[head] = null;
        numElements --;
        head = (head+1)%elements.length;
         return frontElement;
      }
      else
         throw new NoSuchElementException();
   }

   // returns without removing the top element of this stack
   public E first() throws NoSuchElementException
   {  if (numElements > 0)

         return elements[head];
      else
         throw new NoSuchElementException();
   }

   // returns true if this stack contains no elements
   public boolean isEmpty()
   {  return (numElements==0);
   }

   // returns the number of elements in this stack
   public int size()
   {  return numElements;
   }

   // returns a string representation of the stack from top to bottom
   public String toString()
   {  String output = "[";
      for (int i=0; i<elements.length; i++)
      {  output += elements[i];
       
            if((i+1)<elements.length)output += ",";
      }
      output += "]";
      return output;
   }

   // helper method which doubles the current size of the array
   private void expandCapacity()
   {  E[] largerArray =(E[])(new Object[elements.length*2]);//unchecked
      // copy the elements array to the largerArray
      System.out.println("NEED TO EXPAND CAPACITY");
      for (int i=0; i<numElements; i++)
      {
         largerArray[i] = elements[head];
         head = (head+1) % elements.length;
      }
      elements = largerArray;
      head=0;
      tail=numElements;
      //System.out.println("SWEET AS");
   }
   public static void main(String args[])
   {
        System.out.println("=================== ARRAY QUEUE =====================");
        System.out.println("ENQUEUE SOME STUFF");
        QueueADT<String> queue = new ArrayQueue<String>();
        System.out.println("Offering A, B and C");
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        System.out.println("QUEUE IS: \n"+queue.toString());
        System.out.println("Removing "+queue.dequeue());
        System.out.println("Removing "+queue.dequeue());
        System.out.println("QUEUE IS: \n"+queue.toString());
        System.out.println("Offering D, E, F, G");
        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");
        queue.enqueue("G");
        System.out.println("QUEUE IS: \n"+queue.toString());
        System.out.println("Offering H");
        queue.enqueue("H");
        System.out.println("QUEUE IS: \n"+queue.toString());
        System.out.println("Removing "+queue.dequeue());
        System.out.println("Removing "+queue.dequeue());
        System.out.println("Removing "+queue.dequeue());
        System.out.println("QUEUE IS: \n"+queue.toString());
        System.out.println("Offering A, B,C,D, E and F");
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");
        System.out.println("QUEUE IS: \n"+queue.toString());
        System.out.println("Dequeing everything except last element");
       while((queue.size()) >= 1)
       {    System.out.println("Removing "+queue.dequeue());
           System.out.println("QUEUE IS: \n"+queue.toString());
       }
       queue.enqueue("DONE!");
       System.out.println("QUEUE IS: \n"+queue.toString());
        System.out.println("=================== END =====================");
   }
}
