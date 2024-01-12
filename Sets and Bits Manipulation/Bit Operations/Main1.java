import java.util.Scanner;
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);           
        System.out.print("Enter the number : ");
        long number = sc.nextLong();
        BitOperations obj;
        while (true) { 
            System.out.print("Enter the position : ");
            int position = sc.nextInt();
            System.out.println("Select operation number [1]getBit [2]setBit [3]clearBit [4]updateBit [5]Exit");
            int op = sc.nextInt();
            obj = new BitOperations(number, position);
            switch (op) {
                case 1:
                        System.out.printf("The bit = %d\n", obj.getBit(number, position));
                    break;
                case 2:
                    System.out.printf("The number = %d\n", obj.setBit(number, position));
                    break;
                case 3:
                    System.out.printf("The number = %d\n", obj.clearBit(number, position));
                    break;
                case 4:
                    System.out.print("Enter bit value : ");
                    int value = sc.nextInt();
                    obj = new BitOperations(number, position, value == 1 ? true : false);
                    System.out.printf("The number = %d\n", obj.updateBit(number, position, value == 1 ? true : false));
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    throw new AssertionError();
            }
            number = obj.getNumber();
        }
    }
}
