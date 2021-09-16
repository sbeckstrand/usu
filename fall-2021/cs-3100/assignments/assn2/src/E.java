import java.math.BigDecimal;
import java.math.RoundingMode;

public class E {
	
	
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
		Fac Fac = new Fac();
		
		BigDecimal eResult = BigDecimal.valueOf(0);
		
		
		for (int i = 0; i < value; i++) {
			BigDecimal numorator = BigDecimal.valueOf(1.0);
			BigDecimal denominator = new BigDecimal(Fac.computeFactorial(i));
			
			
			BigDecimal result = numorator.divide(denominator, 16, RoundingMode.HALF_UP);
			
			eResult = eResult.add(result);
			
		}
		
		return eResult;
	}

}