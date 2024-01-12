import java.util.Scanner;


public class Application1 {
    public static int uniqueSearch1(int[] arr) {
        int unique = arr[0];
        for (int i = 1; i < arr.length; i++) 
            unique ^= arr[i];
        return unique;
    }
    
    
    public static int onesCounter(int num) {
        int count = 0;
        while(num > 0) {
            count += num & 1;
            num >>= 1;
        }
        return count;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter array size : ");
        int size = sc.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            int x = sc.nextInt();
            array[i] = x;
        }
        System.out.println("Unique number is " + uniqueSearch1(array));
//        System.out.print("Enter the number: ");
//        int num = sc.nextInt();
//        System.out.println("Number of 1's = " + onesCounter(num));
        
    }
}
