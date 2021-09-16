/**
* Class: 			CS-3100
* Description: 		Assignment 2. Refactor of Assignment 1 using Gradle
* @author			Stephen Beckstrand
*/

public class Assn2Main {

	/** Main Function. -- Take input, check if it is a valid number and then pass it along to the appropriate computing method. Otherwise, provide output to user explaining what went wrong */
	public static void main(String[] args) {
		E E = new E();
		Fib Fib = new Fib();
		Fac Fac = new Fac();
		Help Help = new Help();

		/** If no arguments are provided, throw error and return help message */
		if (args.length < 1 || (args.length % 2) == 1) {
			System.out.println("Please provide valid number of arguments.");
			Help.help();
		}

		/** Check that argument is valid (-fib, -fac, -e). If so, process proceeding value with associated logic.  */
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {

				if (args[i].equals("-fib") || args[i].equals("-fac") || args[i].equals("-e")) {
					try {
						int paramValue = Integer.parseInt(args[i + 1]);

						if (args[i].equals("-fib")) {
							System.out.println(Fib.fibonacci(paramValue));
						} 
						
						else if (args[i].equals("-fac")) {
							System.out.println(Fac.factorial(paramValue));
						} 
						
						else if (args[i].equals("-e")) {
							System.out.println(E.euler(paramValue));
						}
					} catch (Exception e) {
						System.out.print(e);
						System.out.println(args[i + 1] + " is not a valid value for " + args[i]);
						Help.help();
					}


				} else {
					System.out.println(args[i] + " is not a valid argument");
					Help.help();
				}
			}
		}
	}
}