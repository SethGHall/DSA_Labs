/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex5_2;

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
    @Override
    public String toString()
    {
        return "NAME: "+name+" AGE: "+age+" ADDRESS: "+address;
    }

}
