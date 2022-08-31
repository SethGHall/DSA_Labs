package ex5_1;

/**
   A class that implements a hash table with chaining
   @author Seth Hall
 * @param <E>
*/
public class HashTable<E>
{
   private final int INITIAL_CAPACITY = 5;
   private Node<E>[] table;
   private int numElements;

   public HashTable()
   { 
       table = new Node[INITIAL_CAPACITY]; // unchecked
       numElements = 0;
   }
   
   public void add(E o)
   {
      if((float)numElements/(float)table.length > 0.75f)
          expandCapacity();
      int index = Math.abs(o.hashCode())%table.length;
      Node<E> newNode = new Node<>(o);
      newNode.next = table[index];
      table[index] = newNode;
      numElements++;
   }
   
   
   public boolean contains(E o)
   {
      int index = Math.abs(o.hashCode())%table.length;
      
      Node<E> current = table[index];
      boolean found = false;
      while(!found && current != null)
      {
         if(current.element.equals(o))
            found = true;
         else
            current = current.next;
      }
      return found;
   }
   
  
   public boolean remove(E o)
   {
      int index = o.hashCode()%table.length;
       if(index < 0) index= index *-1;
      Node<E> current = table[index];
      Node<E> previous = null;
      boolean found = false;
      while(!found && current != null)
      {
         if(current.element.equals(o))
         {  found = true;
            if(previous == null)
            {  table[index] = current.next;
            }
            else
               previous.next = current.next;
         }
         else
         {  previous = current;
            current = current.next;
         }
      }
      if(found) numElements--;
      
      return found;
   }
   @Override
   public String toString()
   {  String s = "";
      for(int i=0;i<table.length;i++)
      {  Node<E> current = table[i];
         s+="row "+i+": ";
         while(current != null)
         {  s+=current.element+" ";
            current = current.next;
         }
         s+="\n";
      }
      return s;
   }
   private class Node<E>
   {
      public Node<E> next;
      public E element;
      
      public Node(E element)
      {
         this.element = element;
         next = null;
      }
   }
   private void expandCapacity()
   {
       System.out.println("LOAD FACTOR EXCEEEDED, EXPANDING CAPACITY");
       Node<E>[] oldtable = table;
       table= new Node[oldtable.length*2];
       numElements = 0;
       for(int i=0;i<oldtable.length;i++)
       {
           Node<E> current = oldtable[i];
           while(current != null)
           {
               add(current.element);
               current = current.next;
           }
       }
   }
   public static void main(String[] args)
   {
      System.out.println("-------HASH TABLE----------");
      HashTable<Student> table = new HashTable<>();
      table.add(new Student("Seth"));
      table.add(new Student("Stan"));
      table.add(new Student("Sam"));
      table.add(new Student("Stella"));
      System.out.println("TABLE: \n"+table.toString());
      table.add(new Student("John"));
       table.add(new Student("Susan"));
      table.add(new Student("Randy"));
      table.add(new Student("Tash"));
      table.add(new Student("Sheena"));
      table.add(new Student("Cushla"));
      System.out.println("TABLE: \n"+table.toString());
      table.add(new Student("Bob"));
      table.add(new Student("Tim"));
      table.add(new Student("Andy"));
      table.add(new Student("Wiki"));
      table.add(new Student("Mark"));
      table.add(new Student("Rita"));
      table.add(new Student("Kara"));
      table.add(new Student("Greg"));
      table.add(new Student("Liam"));
      table.add(new Student("Sara"));
      table.add(new Student("Dennis"));
      table.add(new Student("Bobby"));
      table.add(new Student("Garry"));
      table.add(new Student("Adam"));
      table.add(new Student("Kyle"));
      System.out.println("TABLE: \n"+table.toString());
      System.out.println("CONTAINS LEO "+table.contains(new Student("Leo")));
      System.out.println("CONTAINS TASH "+table.contains(new Student("Tash")));
      System.out.println("CONTAINS TIM "+table.contains(new Student("Tim")));
      System.out.println("CONTAINS MANU "+table.contains(new Student("Manu")));
      System.out.println("CONTAINS Seth "+table.contains(new Student("Seth")));
      System.out.println("Removing Bob,Seth,Leo,Tash,Tim,Sheena,Sara");
      table.remove(new Student("Bob"));
      table.remove(new Student("Seth"));
      table.remove(new Student("Sheena"));
      table.remove(new Student("Sara"));
      table.remove(new Student("Leo"));
      table.remove(new Student("Tash"));
      table.remove(new Student("Tim"));
      System.out.println("TABLE: \n"+table.toString());
   }
}
