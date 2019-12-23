// Obj referece var overwritting
// Will result in error.

import java.util.*;
public class P02{
    public static void main(String args[]){
        Object s = new Object();

        Scanner s1 = new Scanner(System.in);
        s = s2;
        System.out.println("Error after this!");
        String s2 = new String();
    }
}