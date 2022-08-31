package ex2_2;

//package chapter2;

/*******************************************************************************************
PROGRAM NAME:   Sorted Singly-Linked Set
FILE:           LinkedSortedSet.java
AUTHER:         Seth Hall
DESCRIPTION:    Creates the class to hold sorted elements in a set using singly-linked list
                extends the LinkedSet class
*******************************************************************************************/
import java.util.Collection;
import java.util.Set;

public class LinkedSortedSet<E extends Comparable<E>> extends LinkedSet<E>
{

   public LinkedSortedSet()
   {
      super();
   }

   public LinkedSortedSet(Collection<? extends E> c) {
      super(c);
   }
    
   

   @Override
   public boolean add(E o)
   {
      Node<E> newNode = new Node<E>(o);
      System.out.println(">>>>>>>>>>> about to add "+o+" <<<<<<<<<<<<<");
      if (firstNode == null)
      {  
         System.out.println("First Element in list is "+o);
         firstNode = newNode;
         numElements++;
         System.out.println("-----------------------------------------------------------");
         return true;
      }
      else
      {
         //Node<E> newNode = new Node<E>(o);
         Node<E> current = firstNode;
         Node<E> previous = null;
         boolean found = false;

         while (current != null && !found)
         {
            if(o.compareTo(current.element) > 0)
            {  previous = current;
               current = current.next;
            }
            else if (o.compareTo(current.element) == 0)
            {  System.out.println("ELEMENT ALREADY EXISTS for "+o);
               System.out.println("-----------------------------------------------------------");
               return false;
            }
            else
            {
               found = true;
            }
         }
         if (previous == null)
         {  System.out.println("putting "+o+" at start of set before "+firstNode.element);
            newNode.next = current;
            firstNode = newNode;
         }
         else if(current == null)
         {  System.out.println("putting "+o+" at end of list after "+previous.element);
            newNode.next = current;
            previous.next = newNode;
         }
         else
         {  System.out.println("putting "+o+" between "+previous.element+" and "+current.element);
            newNode.next = current;
            previous.next = newNode;
         }
         numElements++;
         System.out.println("-----------------------------------------------------------");
         return true;
      }

   }

   static public void main(String[] args) {
      Set<String> fruit = new LinkedSortedSet<>();
      fruit.add("strawberry");
      System.out.println(fruit);
      
      fruit.add("banana");
      System.out.println(fruit);
      
      fruit.add("apple");
      System.out.println(fruit);
      fruit.add("apple");
      System.out.println(fruit);
      fruit.add("pear");
      fruit.add("orange");
      fruit.add("mango");
      fruit.add("zedberry");
      fruit.add("pear");
      fruit.add("boysenberry");
      fruit.add("avocado");
      System.out.println("NUM ELEMENTS = "+fruit.size());
      System.out.println(fruit);
      fruit.remove("pear");
      fruit.remove("apple");
      fruit.remove("zedberry");
      fruit.remove("boysenberry");
      System.out.println("REMOVING ELEMENTS, SIZE IS NOW = "+fruit.size());
      System.out.println(fruit);
      
      fruit.add("apple");
      fruit.add("tangello");
      
      System.out.println("ADDING ELEMENTS ELEMENTS, SIZE IS NOW = "+fruit.size());
      System.out.println(fruit);
   }
}
