package ex3_1;

/*******************************************************************************************
FILE:           ArrayStackIneff.java
AUTHER:         Seth Hall
DESCRIPTION:    Bad  way of doing a stack using an Array
*******************************************************************************************/ //
import java.util.Collection;
import java.util.NoSuchElementException;

public class ArrayStackIneff<E> implements StackADT<E>
{
   private final int INITIAL_CAPACITY = 20;
   private int numElements;
   private E[] elements;

   // default constructor that creates a new stack
   // that is initially empty
   public ArrayStackIneff()
   {  super();
      numElements = 0;
      elements = (E[])(new Object[INITIAL_CAPACITY]); // unchecked
   }

   // constructor for creating a new stack which
   // initially holds the elements in the collection c
   public ArrayStackIneff(Collection<? extends E> c)
   {  this();
      for (E element : c)
         push(element);
   }

   // Adds one element to the top of this stack
   public void push(E element)
   {  if (numElements >= elements.length)
         expandCapacity();

        for(int i=numElements;i>0;i--)
        {
            elements[i] = elements[i-1];
        }

      elements[0] = element;
      numElements++;
   }

   // removes and returns the top element from this stack
   public E pop() throws NoSuchElementException
   {  if (numElements > 0)
      {
         E topElement = elements[0];
         for(int i=0;i<numElements;i++)
         {
            elements[i] = elements[i+1];
         }

         elements[numElements-1] = null;
         numElements--;
         return topElement;
      }
      else
         throw new NoSuchElementException();
   }

   // returns without removing the top element of this stack
   public E peek() throws NoSuchElementException
   {  if (numElements > 0)
         return elements[0];
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
      for (int i=0; i<=numElements; i++)
      {  output += elements[i];
         if (i>0)
            output += ",";
      }
      output += "]";
      return output;
   }

   // helper method which doubles the current size of the array
   private void expandCapacity()
   {  E[] largerArray =(E[])(new Object[elements.length*2]);//unchecked
      // copy the elements array to the largerArray
      for (int i=0; i<numElements; i++)
         largerArray[i] = elements[i];
      elements = largerArray;
   }
   
   public static void main(String[] args)
   {
       int numElements = 100000;
       StackADT<Integer>  stackGood = new ArrayStack<>();
       System.out.println("PUSHING ON "+numElements+" Onto Normal Stack ");
       long begin = System.currentTimeMillis();
       for(int i=0;i<numElements;i++)
       {
           stackGood.push(i);
       }
       System.out.println("NOW POPPING ALL VALUES From Good Stack- LIFO");
       while(!stackGood.isEmpty())
       {    stackGood.pop();
       }
       System.out.println();
       System.out.println("PROPERLY IMPLEMENTED STACK TOOK "+(System.currentTimeMillis()-begin)+" milliseconds!!!");
       System.out.flush();
       
       StackADT<Integer> stackBad = new ArrayStackIneff<>();
      
       System.out.println("PUSHING ON "+numElements+" Onto Inefficient Stack ");
       begin = System.currentTimeMillis();
       for(int i=0;i<numElements;i++)
       {
           stackBad.push(i);
       }
       System.out.println("NOW POLLING ALL VALUES From bad stack - LIFO");
       while(!stackBad.isEmpty())
       {    stackBad.pop();
       }
        System.out.println();
        System.out.println("INEFFICIENT STACK TOOK "+(System.currentTimeMillis()-begin)+" milliseconds!!!");
  
   }
}
