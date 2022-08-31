package ex4_4;

 
import java.util.Comparator;
import java.util.NoSuchElementException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sehall
 */
public class PriorityQueue implements QueueADT<Task<?>>
{
   private final ArrayHeap<Task<?>> heap;
   
   public PriorityQueue()
   {
      heap = new ArrayHeap<>(new PriorityComparator());//(new PriorityComparator()); //
   }

   @Override
   public void enqueue(Task<?> task)
   {
      heap.add(task);
   }
   @Override
   public Task<?> dequeue()
   {
      return heap.removeMin();
   }
   @Override
   public boolean isEmpty()
   {
      return (heap.size() == 0);
   }

    @Override
    public Task<?> first() throws NoSuchElementException {
        return heap.getMin();
    }

    @Override
    public int size() {
        return heap.size();
    }
   private class PriorityComparator implements Comparator<Task>
   {
        @Override
        public int compare(Task o1, Task o2) 
        {
            int x = o2.getPriority() - o1.getPriority();
            
            if(x == 0)
                return o1.getElement().compareTo(o2.getElement());
            else return x;
        }
       
   }
   
   public static void main(String[] args)
   {   QueueADT<Task<?>> queue = new PriorityQueue();
       queue.enqueue(new Task<>("SKA work",4));
       queue.enqueue(new Task<>("Have a Nap",8));
       queue.enqueue(new Task<>("Run DSA Lab",3));
       queue.enqueue(new Task<>("Feed the Cat and Dog",1));
       queue.enqueue(new Task<>("Have Coffee",1));
       queue.enqueue(new Task<>("Have 2nd Coffee",2));
       queue.enqueue(new Task<>("Have 3rd Coffee",4));
       queue.enqueue(new Task<>("Have 4th Coffee",8));
       queue.enqueue(new Task<>("Watch Tipping Point",7));
       queue.enqueue(new Task<>("Brush Teeth",2));
       queue.enqueue(new Task<>("Walk Dog",9));
       queue.enqueue(new Task<>("Ring Wife",4));
       queue.enqueue(new Task<>("Visit Nana",9));
       queue.enqueue(new Task<>("Water the plants",8));
       queue.enqueue(new Task<>("Eat Lunch",3));
       queue.enqueue(new Task<>("Play Rocket League",8));
       queue.enqueue(new Task<>("Change out of Pyjamas",8));

       while(!queue.isEmpty())
       {
          System.out.println("PROCESSING TASK >> "+queue.dequeue());
          try
          {
              Thread.sleep(500);
          }catch(Exception e){}
       }

   }
}
