import java.util.ArrayList;
import java.util.Scanner;

public class IterativePowerSet {

    public static void main(String[] args) {
        
        Scanner in = new Scanner (System.in);
        System.out.println("Enter Number of Elements ");
        int n = in.nextInt();
        while(n<0){
            System.out.println("Number Can't be Negative");
            n = in.nextInt();
        }
        
        ArrayList <String> elements= new ArrayList <String>();
        ArrayList <String>  set = new ArrayList <String> ();
        set.add("");
        ArrayList <ArrayList<String>> power = new ArrayList<ArrayList<String>>();
        power.add(set);
        
        for (int i=0;i<n;i++){ 
            System.out.println("Enter Element ["+(i+1)+"] :");
            String s = in.next();
            while (elements.contains(s)){
                System.out.println("Element is Already Entered, Enter a New Element");
                s = in.next();
            }
            elements.add(s);
            
            int len = power.size();
            for (int j=0;j<len;j++){
                set = new ArrayList<String> (power.get(j));
                if (set.get(0).isEmpty()) set.clear();
                set.add(s);
                power.add(set);
            }
        }
         
        print(power);
                
    }

    static void  print (ArrayList<ArrayList<String>> power){

        System.out.println("The Power Set = ");
        System.out.print("{");
        ArrayList <String> set = new ArrayList<String>();
        for (int i=0;i<power.size();i++){
            set=power.get(i);
            System.out.print("{");
            for (int j=0;j<set.size();j++){
                System.out.print(set.get(j));
                if (j!=set.size()-1) System.out.print(", ");
            }
            System.out.print("}");
            if (i!=power.size()-1) System.out.print(", ");
        }
        System.out.println("}");
    }
    
}
// You Must Enter the Number of Elements First
// Number of Elements is Bounded by Int Representation
// Elements are Case senstive since it's Strings
