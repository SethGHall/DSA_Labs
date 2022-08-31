package ex3_6;

/**
   Seth Hall,

   Bin Sort algorithm that allows repetitions, replacing boolean[] with int[]
*/
import java.util.*;
public class BinSort
{
   public static void binSort(int[] list, int MAX_VALUE) 
   {
       int[] bins = new int[MAX_VALUE+1]; //initially all false
      // determine which bins should be set to true
      for (int i=0; i<list.length; i++)
         bins[list[i]] = bins[list[i]]+1;
      // use the bins to put the numbers back in the list sorted
      int flagNo = 0;
      for(int i=0; i<bins.length; i++)
      {  // find the next flag that is true
         if(bins[i]>0)
        {   for(int j=0;j<bins[i];j++)
            {   list[flagNo] = i;
                flagNo++;
            }
        }
      }
      
   }
    
   public static void main(String[] args)
   {
      Random generator = new Random();
      final int MAX_VALUE = 10;
      int[] list = new int[500];
      for(int i=0;i<list.length;i++)
      { list[i] = generator.nextInt(MAX_VALUE+1);
      }

      System.out.println("SORTING THESE VALUES... ");
      System.out.println(Arrays.toString(list));
     
      long beginTime = System.nanoTime()/1000; //
      
      binSort(list,  MAX_VALUE);

      long endTime = System.nanoTime()/1000; //
      // output the results
      System.out.println("NOW SORTED...");
      System.out.println(Arrays.toString(list));
      System.out.println("THE ALGORITHM TOOK "+(endTime - beginTime)+ " microS ");

   }
}
