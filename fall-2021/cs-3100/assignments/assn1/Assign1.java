import java.math.BigInteger;

public class Assign1 {
    
    // Main function
    public static void main(String[] args){
        
        // If no arguments are provided, throw error and return help message
        if (args.length < 1 || (args.length % 2) == 1 ) {
            System.out.println("Please provide valid number of arguments.");
            help();
        }
        
        // Check that argument is valid (-fib, -fac, -e). If so, process proceeding value with associated logic. 
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                
                if (args[i].equals("-fib") || args[i].equals("-fac") || args[i].equals("-e")) {
                    try {
                        int paramValue = Integer.parseInt(args[i + 1]);
                        
                        if (args[i].equals("-fib")) {
                            if (paramValue < 0 || paramValue > 40) {
                                System.out.println("Fibonacci valid range is [0, 40]");
                            }
                            else {
                                System.out.println(fibonacci(paramValue));
                            }
                        } 
                        else if (args[i].equals("-fac")) {
                            if (paramValue < 0 || paramValue > 2147483647) {
                                System.out.println("Factorial valid range is [0, 2147483647]");
                            }
                            else {
                                System.out.println(factorial(paramValue));
                            }
                            
                        } 
                        else if (args[i].equals("-e")) {
                            euler(paramValue);
                        }
                    }
                    catch (Exception e) {
                        System.out.println(args[i + 1] + " is not a valid value for " + args[i]);
                        help();
                    }
                    
                    
                }
                else {
                    System.out.println(args[i] + " is not a valid argument");
                    help();
                }
            }
        }
    }
    
    public static int fibonacci(Integer value) {
        if (value <= 1) {
            return value;
        }
        
        return fibonacci(value - 1) + fibonacci(value - 2);
    }

    public static BigInteger factorial(Integer value) {
            BigInteger factorialResult = BigInteger.valueOf(1);
            
            for (int i=1; i <= value; i++) {
                factorialResult = factorialResult.multiply(BigInteger.valueOf(i));
            }
            
            return factorialResult;
    }
    
    public static void euler(Integer value) {
        // Value Validation
        if (value < 1 || value > 2147483647) {
            System.out.println("Valid range for e is [1, 2147483647]");
        }
        else {
            
        }
    }

    // Help function
    public static void help(){
        System.out.println("\n--- Assign 1 Help ---");
        System.out.println("\t-fib [n] : Compute the Fibonacci of [n]; valid range [0, 40]");
        System.out.println("\t-fac [n] : Compute the factorial of [n]; valid range, [0, 2147483647]");
        System.out.println("\t-e [n] : Compute the value of 'e' using [n] iterations; valid range [1, 2147483647]\n");
        System.exit(0);
    }
    
}
