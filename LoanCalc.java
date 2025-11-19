// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double balance = loan;
    	double monthlyRate = rate / 100;  // Convert percentage to decimal (annual interest rate)
    
    	// Loop over each payment period
    	for (int i = 0; i < n; i++) {
			balance *= (1 + monthlyRate);  // Apply interest to the balance
			balance -= payment;            // Subtract the payment from the balance
    	}
    
    	return balance;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		// Replace the following statement with your code

		/* 
		  double payment = loan / n;  
		iterationCounter = 0;

		while (true) {
			iterationCounter++;
			double balance = endBalance(loan, rate, n, payment);
			
			if (Math.abs(balance) < epsilon) {
				break;
			}
			
			payment += epsilon;
		}
		return payment;
		  
		*/
		
		return 0;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        // Replace the following statement with your code
		double L = loan / n;  
        double H = loan;      
        iterationCounter = 0;

        while ((H - L) > epsilon) {
            iterationCounter++;
            double mid = (L + H) / 2; 
            double balance = endBalance(loan, rate, n, mid);
        
            if (balance > 0) {
                L = mid;  
            } else {
                H = mid;  
            }
        }
        
        return (L + H) / 2;  // Return the midpoint as the solution
    }
}