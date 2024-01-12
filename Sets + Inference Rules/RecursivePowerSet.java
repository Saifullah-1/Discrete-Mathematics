import java.util.ArrayList;
import java.util.Scanner;

public class RecursivePowerSet {
    public static void main(String[] args) {
        
        Scanner in = new Scanner (System.in);
        System.out.println("Enter Number of Elements ");
        int n = in.nextInt();
        while(n<0){
            System.out.println("Number Can't be Negative");
            n = in.nextInt();
        }
        ArrayList <String> elements= new ArrayList <String>();
        ArrayList <String> set= new ArrayList <String>();
        ArrayList <ArrayList<String>> power = new ArrayList<ArrayList<String>>();
        
        for (int i=0;i<n;i++){ 
            System.out.println("Enter Element ["+(i+1)+"] :");
            String s = in.next();
            elements.add(s);
        }
        
        getPowerSet(power,elements,set);
        set.clear();
        power.add(set);
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
    
    static void getPowerSet(ArrayList<ArrayList<String>> res, ArrayList<String>arr, ArrayList<String> set){
        for (int i=arr.size()-1;i>=0;i--){
            set.add(arr.get(i));
            ArrayList<String> a = new ArrayList<String> (arr.subList(0, i));
            getPowerSet(res,a,set);
            ArrayList<String> temp = new ArrayList<String> (set);
            res.add(temp);
            set.remove(set.size()-1);
        }
    }
}
// You Must Enter the Number of Elements First
// Number of Elements is Bounded by Int Representation
// Elements are Case senstive since it's Strings
