package ex1_2;

import java.util.Arrays;
import java.util.Random;

/*
    Seth Hall
    Soloutions for the lab excersize 1.2
*/



public class SelectionSort
{
   public static void selection_sort(int[] array)
   {
       //logic in here - do it in place
       for(int pos=0;pos<array.length-1;pos++)
       {
           int smallest = array[pos];
           int smallestIndex = pos;
           
           for(int j=pos+1;j<array.length;j++)
           {
               if(array[j]<smallest)
               {   smallest = array[j];
                   smallestIndex = j;
                   
               }
           }
           array[smallestIndex] = array[pos];
           array[pos] = smallest;
       }   
   }
    
    

   public static void main(String[] args)
   {
       System.out.println("========SELECTION SORT==========");
       
       int MAX = 10;
       int[] numbers = new int[MAX];
       Random gen = new Random();
       for(int i=0;i<numbers.length;i++)
       {    numbers[i] = gen.nextInt(100);
       }
       System.out.println("Here are your unsorted numbers ");
       System.out.println(Arrays.toString(numbers));
       selection_sort(numbers);
       
       System.out.println("Now sorted ");
       System.out.println(Arrays.toString(numbers));
       
   }
}
