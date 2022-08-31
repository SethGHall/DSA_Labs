/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex5_2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author sehall
 */
public class Enrollments {
    public static void main(String[] args)
    {
        Map<String, Student> map = new TreeMap<>();
        map.put("114533",new Student("Bobby Brown",23,"18 Te Arawa St"));
        map.put("135555",new Student("Dougie Houser",14,"13 Doctor St"));
        map.put("112122",new Student("Tim Tam",18,"5 Biscuit St"));
        map.put("114114",new Student("Tina Turner",60,"1 Best St"));
        map.put("144556",new Student("Adam Campbell",28,"23 Dodgy Papakura Road"));
        map.put("121212",new Student("Tim Cornwell",64,"339 W-Projection Ave"));
        map.put("333311",new Student("Jon Nödtveidt",31,"666 Lights Bane Road"));
        Scanner scan = new Scanner(System.in);
        
        String id = "";
        
        do
        {
            System.out.println("Enter ID of Student to obtain info");
            id = scan.nextLine();
            
            if(!map.containsKey(id))
                System.out.println("No Student Enrolled for "+id);
            else
            {
                Student s = map.get(id);
                System.out.println(s);
            }
            
        }while(!id.equalsIgnoreCase("quit"));
        

        System.out.println("Students in order of enrollments:");
        for(Student s: map.values())
            System.out.println(s);
        
    }
}
