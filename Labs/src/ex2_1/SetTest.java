package ex2_1;

//package chapter2;

import java.util.*;

public class SetTest
{
    public static void main(String[] args)
    {
         
        Set<Integer> set = new HashSet<>();
        
        Random rand = new Random();
        
        long begin = System.currentTimeMillis();
        for(int i=0;i<3000000;i++)
        {   set.add(rand.nextInt());
            if(i%1000 == 0)
                System.out.println("Adding random element "+i);
        }
        long end = System.currentTimeMillis();
       
        System.out.println("SET TOOK "+((double)(end-begin))+" MilliSeconds");
    }
}
