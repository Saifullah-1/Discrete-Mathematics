import java.util.ArrayList;
import java.util.Scanner;


public class Main2 {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Universe : ");
        String input = sc.nextLine();
        String[] universeArr = input.split(", ");
        ArrayList<String> arr = new ArrayList<>();
        for (String element : universeArr) {
            if (!arr.contains(element))
                arr.add(element);
        }
        Set first = new Set(arr);
        ArrayList<Set> arrSet = new ArrayList<>();
        arrSet.add(first);
        System.out.print("Enter number of sets : ");
        int num = sc.nextInt();
        sc.nextLine();
        String str;
        for (int i = 0; i < num; i++) {
            Set set = new Set();
            System.out.print("Please enter elements of set no. " + (i+1) + " : ");
            str = sc.nextLine();
            String[] setArr = str.split(", ");
            for (int j = 0; j < setArr.length; ++j) {
                set.addStringToSet(setArr[j]);
            }
            arrSet.add(set);
        }
        
        System.out.println("Select Operation [1]Union [2]Intersection [3]Complement [4]Difference [5]Cardinality [6]Print");
        int op = sc.nextInt();
        Set operationSet = new Set(); 
        int set1, set2;
        switch (op) {
            case 1:
                if (num < 2) {
                    System.err.print("This Operation requires two sets or more");
                    System.exit(0);
                }
                System.out.println("Please enter the index of two sets(1 : #sets)");
                set1 = sc.nextInt();
                set2 = sc.nextInt();
                if (set1 > num || set2 > num || set1 == set2) {
                    System.err.print("Invalid set index");
                    System.exit(0);
                }
                operationSet = arrSet.get(set1).union(arrSet.get(set2));
                operationSet.getElementsOfSet();
                break;
            case 2:
                if (num < 2) {
                    System.err.print("This Operation requires two sets or more");
                    System.exit(0);
                }
                System.out.println("Please enter the index of two sets(1 : #sets)");
                set1 = sc.nextInt();
                set2 = sc.nextInt();
                if (set1 > num || set2 > num || set1 == set2) {
                    System.err.print("Invalid set index");
                    System.exit(0);
                }
                operationSet = arrSet.get(set1).intersection(arrSet.get(set2));
                operationSet.getElementsOfSet();
                break;
            case 3:
                if (num < 1) {
                    System.err.print("There is no sets");
                    System.exit(0);
                }
                System.out.println("Please enter the index of the set(1 : #sets)");
                set1 = sc.nextInt();
                if (set1 > num ) {
                    System.err.print("Invalid set index");
                    System.exit(0);
                }
                operationSet = arrSet.get(set1).complement();
                operationSet.getElementsOfSet();
                break;
            case 4:
                if (num < 2) {
                    System.err.print("This Operation requires two sets or more");
                    System.exit(0);
                }
                System.out.println("Please enter the index of two sets(1 : #sets)");
                set1 = sc.nextInt();
                set2 = sc.nextInt();
                if (set1 > num || set2 > num || set1 == set2) {
                    System.err.print("Invalid set index");
                    System.exit(0);
                }
                operationSet = arrSet.get(set1).difference(arrSet.get(set2));
                operationSet.getElementsOfSet();
                break;
            case 5:
                if (num < 1) {
                    System.err.print("There is no sets");
                    System.exit(0);
                }
                System.out.println("Please enter the index of the set(1 : #sets)");
                set1 = sc.nextInt();
                if (set1 > num ) {
                    System.err.print("Invalid set index");
                    System.exit(0);
                }
                System.out.print(arrSet.get(set1).getCardinality());
                break;
            case 6:
                System.out.println("Please enter the index of the set(1 : #sets)");
                set1 = sc.nextInt();
                if (set1 > num ) {
                    System.err.print("Invalid set index");
                    System.exit(0);
                }
                arrSet.get(set1).getElementsOfSet();
                break;
            default:
                System.err.print("Invalid choice");
                System.exit(0);
        }
    }
}
