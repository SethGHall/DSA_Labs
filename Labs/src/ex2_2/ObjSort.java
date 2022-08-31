/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2_2;

import java.util.Set;

/**
 *
 * @author Seth
 */
public class ObjSort implements Comparable<ObjSort>{
    
    private int e1;
    private String e2;
    
    public ObjSort(int e1, String e2)
    {
        this.e1 = e1;
        this.e2 = e2;
    }
    
    @Override
    public String toString()
    {
        return e2 + "("+e1+")";
    }
    
    
    
    
    public static void main(String[] args) {
      Set<ObjSort> fruit = new LinkedSortedSet<>(); 
      
    }

    @Override
    public int compareTo(ObjSort o) {
        if(o.e1 == e1)
            return e2.compareTo(o.e2);
        else 
            return e1 - o.e1;
    }
}
