import java.util.Scanner;


public class Primes {
    public boolean[] sieveOfEratosthenes(int n) {
        boolean prime[] = new boolean[n + 1]; 
        for (int i = 2; i <= n; i++) 
            prime[i] = true; 
        
        for (int p = 2; p * p <= n; p++) { 
            if (prime[p] == true) { 
                for (int i = p * p; i <= n; i += p) 
                    prime[i] = false; 
            } 
        } 
        return prime;
    } 
    
    public boolean primeChecker(int num) {
        if (num == 0 || num == 1) 
            return false;
        
        int bound = (int) Math.sqrt(num);
        boolean prime[] = sieveOfEratosthenes(bound);
        for (int i = 2; i <= bound; i++) {
            if (prime[i] == true && num % i == 0) { // This condition checks if the number is divisible by a prime number less than its square root 
                return false;
            }
        }
        return true;
    }
    
    public int[] primeFactors(int num) {
        if (primeChecker(num) || num == 0 || num == 1) 
            return null;
            
        int bound = (int) Math.sqrt(num);
        boolean prime[] = sieveOfEratosthenes(bound);
        int[] factorsList  = new int[bound + 1]; 
        for (int i = 2; i < prime.length; i++) {
            if (prime[i]) { 
                while (num % i == 0) { 
                    factorsList[i] += 1;
                    num /= i;
                }
            }
        }
        return factorsList;
    }
    
    /*
    * GCD & LCM using Euclidean algorithm
    */
    public int GCD_1(int num1, int num2) { 
        int x = num1;
        int y = num2;
        
        while(y != 0){
            int r = x % y;
            x = y;
            y = r;
        }
        return x;
    }
    
    public int LCM_1(int num1, int num2) throws ArithmeticException{ // num1 * num2 = gcd(num1, num2) * lcm(num1, num2)
        if (GCD_1(num1, num2) == 0) 
            throw new ArithmeticException();
        
        return (num1 * num2) / GCD_1(num1, num2);
    }
    
    /*
    * GCD & LCM using prime factorization
    */
    public int GCD_2(int num1, int num2) {
        if (num1 == 0 || num2 == 0) 
            return Integer.max(num1, num2);
        
        if (primeChecker(num1) && primeChecker(num2)){ 
            if (num1 == num2) 
                return num1;
            
            return 1;
        }
        
        int[] primeFactors1 = primeFactors(num1);
        int[] primeFactors2 = primeFactors(num2);
        
        if (primeFactors1 == null || primeFactors2 == null) {
            if (Integer.max(num1, num2) % Integer.min(num1, num2) == 0) 
                return Integer.min(num1, num2);
            return 1;
        }
        
        int bound = Integer.min(primeFactors1.length, primeFactors2.length);
        
        int gcd = 1;
        for (int i = 2; i < bound; i++) {
            int x = primeFactors1[i];
            int y = primeFactors2[i];
            
            gcd *= Math.pow(i, Integer.min(x, y));
        }
        
        return gcd;
    }
    
    public int LCM_2(int num1, int num2) throws ArithmeticException{
        if (num1 == 0 || num2 == 0) 
            throw new ArithmeticException();
        
        if (primeChecker(num1) && primeChecker(num2)){ 
            if (num1 == num2) 
                return num1;
            
            return num1 * num2;
        }
        int[] primeFactors1 = primeFactors(num1);
        int[] primeFactors2 = primeFactors(num2);
        
        if (primeFactors1 == null || primeFactors2 == null) {
            if (Integer.max(num1, num2) % Integer.min(num1, num2) == 0) 
                return Integer.max(num1, num2);
            return num1 * num2;
        }
        
        int bound = Integer.min(primeFactors1.length, primeFactors2.length);
        
        int lcm = 1;
        for (int i = 2; i < bound; i++) {
            int x = primeFactors1[i];
            int y = primeFactors2[i];
            
            lcm *= Math.pow(i, Integer.max(x, y));
        }
        
        return lcm;
    }
    
    void printFactors(int num) {
        int[] factors = primeFactors(num);
        
        if (factors != null) {
            System.out.print("Factorization of " + num + ": ");
            for(int i = 2; i < factors.length; ++i) {
                if (factors[i] != 0) 
                    System.out.print(i + "^" + factors[i]);
                
                if (i < factors.length - 1) 
                    System.out.print(" * ");
            }
        }else {
            if (primeChecker(num)) 
                System.out.print(num + " is a prime number");
            else 
                System.out.print(num + " cannot be factorized");
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number : ");
        int a = sc.nextInt();
        System.out.print("Enter second number : ");
        int b = sc.nextInt();
        System.out.println("");
            
        Primes obj = new Primes();
        System.out.println(a + " Prime? : " + obj.primeChecker(a));
        System.out.println(b + " Prime? : " + obj.primeChecker(b));
        System.out.println("");
            
        System.out.println("Prime Factorizations :");
        obj.printFactors(a);
        obj.printFactors(b);
        System.out.println("");
            
        System.out.printf("gcd(%d, %d):\nFirst Method = %d\nSecond Method = %d\n", a, b, obj.GCD_1(a, b), obj.GCD_2(a, b));
        try {
            System.out.printf("lcm(%d, %d):\nFirst Method = %d\nSecond Method = %d\n", a, b, obj.LCM_1(a, b), obj.LCM_2(a, b));
        } catch (ArithmeticException e) {
            System.out.printf("lcm(%d, %d):\nNaN\n", a, b);
        }
    }
}

/*
    Assumptions:
    * positive integers
    * integers are small
*/