package my.projecteuler;

import java.util.stream.Stream;

/**
 * <h1><a href="http://projecteuler.net/problem=10">Summation of primes</a></h1>
 * 
 * <p>
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * 
 * Find the sum of all the primes below two million.
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem10 {
	public static void main(String[] args) {
		int bound = 2_000_000;
		long result = new Problem10().getSumOfPrimesBelow(bound);
		System.out.println("Result: " + result);
	}

	public long getSumOfPrimesBelow(int num) {
		Stream<Long> stream = new Primes(num).primesAsStream().filter(x->x<num);
		long result = stream.mapToLong(x->x).sum();
		return (long) result;
	}

}
