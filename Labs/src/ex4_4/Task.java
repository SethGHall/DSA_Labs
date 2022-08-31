package ex4_4;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sehall
 * @param <E>
 */
public class Task<E extends Comparable> implements Comparable<Task>
{
   private int priority;
   private E element;
   private long timestamp;

   public Task(E element, int priority)
   {
      this.priority = priority;
      this.element = element;
      timestamp = System.nanoTime();
   }
   public Task(E element)
   {
      this(element,5);
   }

   @Override
   public String toString()
   {
      return element.toString()+" ("+priority+")";
   }
   public int getPriority()
   {
       return priority;
   }
   
   public E getElement()
   {
       return element;
   }
   @Override
   public int compareTo(Task o)
   {
      if(priority == o.priority)
         return (int) (timestamp - o.timestamp);
      else
         return priority - o.priority;
   }

}
