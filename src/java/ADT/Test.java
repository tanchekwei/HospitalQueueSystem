/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Tan Chek Wei
 */
public class Test {
    public static void main(String args[]){
        ArrayList<String> a = new ArrayList<>();
        
        a.add("1");
        a.add("abc");
        System.out.print(a.getEntry(0));
        a.remove(0);
        System.out.print(a);
    }
}
