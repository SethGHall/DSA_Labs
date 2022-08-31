package ex6_2;

/**
   A class that implements a stack collection using a
   singly-linked list as the underlying data structure
   @author Andrew Ensor
*/
import java.util.Collection;
import java.util.NoSuchElementException;

public class LinkedStack<E> implements StackADT<E>
{
   protected int numElements;
   protected Node<E> firstNode; // front of list is top of stack
   
   // default constructor that creates a new stack 
   // that is initially empty
   public LinkedStack()
   {  numElements = 0;
      firstNode = null;
   }
   
   // constructor for creating a new stack which
   // initially holds the elements in the collection c
   public LinkedStack(Collection<? extends E> c)
   {  this();
      for (E element : c)
         push(element);
   }
   

   // Adds one element to the top of this stack
   public void push(E element)
   {  Node<E> newNode = new Node<E>(element);
      // add the new node to the front of the list
      newNode.next = firstNode;
      firstNode = newNode;
      numElements++;
   }
   
   // removes and returns the top element from this stack
   public E pop() throws NoSuchElementException
   {  if (firstNode != null)
      {  E topElement = firstNode.element;
         firstNode = firstNode.next;
         numElements--;
         return topElement;
      }
      else
         throw new NoSuchElementException();
   }
   
   // returns without removing the top element of this stack
   public E peek() throws NoSuchElementException
   {  if (numElements > 0)
         return firstNode.element;
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
      Node currentNode = firstNode;
      while (currentNode != null)
      {  output += currentNode.element;
         if (currentNode.next != null)
            output += ",";
         currentNode = currentNode.next;
      }
      output += "]";
      return output;
   }
      
   // inner class which represents a node in a singly-linked list
   protected class Node<E>
   {  
      public E element;
      public Node<E> next;
      
      public Node(E element)
      {  this.element = element;
         next = null;
      }
   }
}
