package ex3_3;

/******************************************************************************************
PROGRAM NAME:   Josephus Problem with Queues
AUTHER:         Seth Hall   
*******************************************************************************************/
import java.util.Scanner;
import java.util.LinkedList;

public class JosephusQueue
{
    public static void main(String args[])
    {
        System.out.println("=================== Josphus Queue =====================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of Soldiers in the Josphus Problem: ");
        String input = scanner.nextLine();
        int numSoldiers = Integer.parseInt(input);

        System.out.println("Enter the number to remove the soldier!!: ");
        input = scanner.nextLine();
        int gap = Integer.parseInt(input);

        LinkedList<Integer> queue = new LinkedList<Integer>();
        System.out.println("ADDING THESE SOLDIERS ");
        for(int i=1;i<=numSoldiers;i++)
        {   Integer soldier = new Integer(i);
            System.out.print(soldier+", ");
            queue.offer(soldier);
        }
        System.out.println("");
        int count = 0;
        while(queue.size() > 1)
        {   
            if(count % gap == gap-1)
                 System.out.println("REMOVING SOLDIER >>: "+queue.poll());
            else
            {   queue.offer(queue.poll());
            }
            count ++;
        }
        Integer last = queue.poll();
        System.out.println("THE LAST SOLDIER STANDING IS >>: "+last);
        System.out.println("=================== END =====================");
    }
}
