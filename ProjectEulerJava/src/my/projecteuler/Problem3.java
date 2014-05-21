package my.projecteuler;

import java.util.function.Predicate;

/**
 * <h1><a href="http://projecteuler.net/problem=3">Largest prime factor</a></h1>
 * 
 * <p>
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * 
 * What is the largest prime factor of the number 600851475143 ?
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem3 {
	public static void main(String[] args) {
		long testedNumber = 600851475143l;
		long maxPossibleValue = Double.valueOf(Math.sqrt(testedNumber))
				.longValue();
		Primes primes = new Primes(maxPossibleValue);
		long result = primes.primesAsStream().filter(isFactorOf(testedNumber))
				.mapToLong(x -> x).max().getAsLong();
		System.out.println("Result: " + result);
	}

	private static Predicate<Long> isFactorOf(long testedNumber) {
		return v -> testedNumber % v == 0l;
	}

}
