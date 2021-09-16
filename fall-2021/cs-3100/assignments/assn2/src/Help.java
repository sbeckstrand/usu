public class Help {
	
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