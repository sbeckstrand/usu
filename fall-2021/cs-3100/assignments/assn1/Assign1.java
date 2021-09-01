public class Assign1 {
    
    // Main function
    public static void main(String[] args){
        
        // If no arguments are provided, throw error and return help message
        if (args.length < 1 || (args.length % 2) == 1 ) {
            System.out.println("Please provide valid number of arguments.");
            help();
            System.exit(0);
        }
        
        // Check that argument is valid (-fib, -fac, -e). If so, process proceeding value with associated logic. 
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (args[i].equals("-fib")) {
                    System.out.println("1");
                } 
                else if (args[i].equals("-fac")) {
                    System.out.println("2");
                } 
                else if (args[i].equals("-e")) {
                    System.out.println("3");
                } 
                else {
                    System.out.println(args[i] + " is not a valid argument");
                    help();
                    System.exit(0);
                }
            }
        }
    }


    // Help function
    public static void help(){
        System.out.println("\n--- Assign 1 Help ---");
        System.out.println("\t-fib [n] : Compute the Fibonacci of [n]; valid range [0, 40]");
        System.out.println("\t-fac [n] : Compute the factorial of [n]; valid range, [0, 2147483647]");
        System.out.println("\t-e [n] : Compute the value of 'e' using [n] iterations; valid range [1, 2147483647]\n");
    }
    
}
