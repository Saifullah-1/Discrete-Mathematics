import java.util.ArrayList;
import java.util.Scanner;

public class ChineseRemainder {
    
    static int modInverse(int a, int m) {
        for (int i = 1; i < m; i++) 
            if (((a % m) * (i % m)) % m == 1) 
                return i; 
        return -1000; 
    } 
    
    public int CRT(String numStr[], String remStr[]) throws ArithmeticException{ 
        int []rem = new int[remStr.length];
        int []num = new int[numStr.length];
        
        int m = 1;
        for (int i = 0; i < remStr.length; i++) {
            rem[i] = Integer.parseInt(remStr[i]);
            m *= rem[i];
            num[i] = Integer.parseInt(numStr[i]);
        }
        
        ArrayList<Integer> M = new ArrayList<>();
        for (int i = 0; i < rem.length; i++) 
            M.add(m / rem[i]);
        
//        for (int i = 0; i < M.size(); i++) {
//            System.out.print(M.get(i) + " ");
//        }
//        System.out.println("");
        
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            if(modInverse(M.get(i), rem[i]) == -1000)
                throw new ArithmeticException();
            y.add(modInverse(M.get(i), rem[i]));
        }
        
//        for (int i = 0; i < y.size(); i++) {
//            System.out.print(y.get(i) + " ");
//        }
//        System.out.println("");
        
        int x = 0;
        for (int i = 0; i < y.size(); i++) 
            x += num[i] * y.get(i) * M.get(i);
        
        return x % m;
    } 
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {            
            System.out.println("Format >> x = a_i (mod m_i)");
            System.out.println("Please enter numbers(a) array separeted by comma (a0, a1, a2, .... : )");
            String numInput = sc.nextLine();

            System.out.println("Please enter remainders(m) array separeted by comma (m0, m1, m2, .... : )");
            String remInput = sc.nextLine();

            String []numStr = numInput.split(", ");
            String []remStr = remInput.split(", ");

            ChineseRemainder obj = new ChineseRemainder();
            try {
                System.out.println("X = " + obj.CRT(numStr, remStr));
            } catch (ArithmeticException e) {
                System.out.println("No Solution");
            }
            System.out.println("-----------------------------------------------------------------------------------");
        }
    }
}
