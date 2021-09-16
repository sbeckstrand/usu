import java.math.BigInteger;

public class Fac {
	
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

}