public class Assign1 {
    
    /**
    Main function. 
    
    This is our controller function which takes command line arguments, validates that an appropriate number of arguments were provided and then validates that the argument is for a valid method. If valid, it checks if an integer value was provided and if so, passes the value to the corresponding method to handle further validation and computing. Once handled by method, it prints the method's output back to the terminal.  
    */
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
                            System.out.println(fibonacci(paramValue));
                        } 
                        else if (args[i].equals("-fac")) {
                            System.out.println(factorial(paramValue));
                            
                        } 
                        else if (args[i].equals("-e")) {
                            System.out.println(euler(paramValue));   
                        }
                    }
                    catch (Exception e) {
                        System.out.print(e);
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
    
    /**
    Primary fibonacci function.
    
    Used to validate provided value and either return the computed fibonacci number or explain any issues with input. 
    */
    public static String fibonacci(Integer value) {
        
        // Validate provided value
        if (value < 0 || value > 40) {
            return "Fibonacci valid range is [0, 40]";
        }
        // If valid, rely on helper method to calculate the fibonacci value and return it 
        else {
            Integer fibonacciValue = computeFibonacci(value);
            return String.format("Fibonacci of %d is %d", value, fibonacciValue);
        }
    }
    
    /**
    Helper method for fibonacci. 
    
    This recursive method works through the formula f_(n-1) + f_(n-2) until the value is 1.
    */
    public static int computeFibonacci(Integer value) {
        if (value <= 1) {
            return 1;
        }
        
        return computeFibonacci(value - 1) + computeFibonacci(value - 2);
    }
    
    
    /**
    Primary factorial function. 
    
    Used to validate provided value and either return the computed factorial value or explain any issues with input. 
    */
    public static String factorial(Integer value) {
        if (value < 0 || value > 2147483647) {
            return "Factorial valid range is [0, 2147483647]";
        }
        else {
            BigInteger factorialValue = computeFactorial(value);
            return String.format("Factorial of %d is %d", value, factorialValue);
        }
    }

    /**
    Helper method for factorial. 
    
    This method uses a for loop to iterate through each number 1-n and multiply each number number by each other. 
    */
    public static BigInteger computeFactorial(Integer value) {
        BigInteger factorialResult = BigInteger.valueOf(1);
        
        for (int i=1; i <= value; i++) {
            factorialResult = factorialResult.multiply(BigInteger.valueOf(i));
        }
        
        return factorialResult;
    }
    
    /**
    Primary Euler function. 
    
    Used to validate provided value and either return the computed value for e, approximated based on the number of iterations specified. 
    */
    public static String euler(Integer value) {
        if (value < 1 || value > 2147483647) {
            return String.format("Valid range for e is [1, 2147483647]");
        }
        else {
            BigDecimal eulerValue = computeEuler(value);
            return String.format("Value of e using %d iterations is %.16f", value, eulerValue);
        }
    }
    
    /**
    Helper method for euler function. 
    
    This method uses the following formula to calculate e to the n iterations: 1/0! + 1/1! + 1/2! + ... + 1/n!
    */
    public static BigDecimal computeEuler(Integer value) {
        BigDecimal eResult = BigDecimal.valueOf(0);
        
        
        for (int i = 0; i < value; i++) {
            BigDecimal numorator = BigDecimal.valueOf(1.0);
            BigDecimal denominator = new BigDecimal(computeFactorial(i));
            
            
            BigDecimal result = numorator.divide(denominator, 16, RoundingMode.HALF_UP);
            
            eResult = eResult.add(result);
            
        }
        
        return eResult;
    }

    /**
    Help function. 
    
    Should input validation fail in the main function or it's methods, a message will be provided to the enduser highlighting the issue and then this method is used to highlight valid syntax when using the application. 
    */
    
     
    public static void help(){
        System.out.println("\n--- Assign 1 Help ---");
        System.out.println("\t-fib [n] : Compute the Fibonacci of [n]; valid range [0, 40]");
        System.out.println("\t-fac [n] : Compute the factorial of [n]; valid range, [0, 2147483647]");
        System.out.println("\t-e [n] : Compute the value of 'e' using [n] iterations; valid range [1, 2147483647]\n");
        System.exit(0);
    }
    
}
