/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex5_1;

import ex5_2.*;
import java.util.Objects;

/**
 *
 * @author sehall
 */
public class Student {
    private String name;
    private int age;
    private String address;
        
    public Student(String name, int age, String address)
    {   this.name = name;
        this.age = age;
        this.address = address;
        
    }
    public Student(String name)
    {   this(name,0,null);
    }
    
    @Override
    public String toString()
    {
        return name;
    }
    
    @Override
    public int hashCode()
    {   int sum = name.hashCode();
//        int sum = name.charAt(0);
//        
//        for(int i=0;i<name.length();i++)
//        {
//            sum+=name.charAt(i);
//        }
        
        return sum;
    }
    public String getName()
    {
        return name;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student)
        {   Student other = (Student)obj;
            return other.getName().equals(name);
        }
        else return false;
    }

}
