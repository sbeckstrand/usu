public class Fib {
	
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
			return value;
		}
		
		return computeFibonacci(value - 1) + computeFibonacci(value - 2);
	}

}