//Seth Hall
package ex1_4;

import java.util.ArrayList;
import java.util.Random;
import java.util.NoSuchElementException;

public class RandomObtainableList<E> extends ArrayList<E> implements RandomObtainable<E>
{
    private final Random generator;

    public RandomObtainableList()
    {
        super();
        generator = new Random();
    }
    @Override
    public E getRandom() throws NoSuchElementException
    {   if(super.size() <= 0) throw new NoSuchElementException("List has no Elements in it ");
        return super.get(generator.nextInt(super.size()));
    }
    @Override
    public boolean removeRandom() throws NoSuchElementException
    {
        if(super.size() <= 0) throw new NoSuchElementException("List has no Elements in it to remove");
        super.remove(generator.nextInt(super.size()));
        return true;
    }


    //simple main method for testing
    public static void main(String args[])
    {
        System.out.println("=================== Random Obtainable List=====================");
        RandomObtainableList<String> list = new RandomObtainableList<>();
        System.out.println("Adding Items to the list");
     

        list.add("bob");
        list.add("gary");
        list.add("leo");
        list.add("tim");
        list.add("nat");
        list.add("helen");
        list.add("tina");
        list.add("seth");
        list.add("sergio");
        list.add("alice");

        System.out.println("=======Now Getting Items in Order=========");
        list.forEach((name) -> {
            System.out.println("ADDED THESE NAMES IN ORDER >> "+name);
        });

        System.out.println("========Now get 7 Items Names in random order=======");
        for(int i=0;i<7;i++)
        {   System.out.println("GETTIN THESE NAMES IN RANDOM ORDER >> "+list.getRandom());
        }

        System.out.println("========Now REMOVING Items in a Random Order==========");
        while(list.size() > 0)
        {   list.removeRandom();
            System.out.println("RANDOMLY REMOVED A NAME, LIST NOW>> \n"+list);
        }
        
    }
}