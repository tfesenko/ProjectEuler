package my.projecteuler;

import java.util.stream.Stream;

/**
 * <h1><a href="http://projecteuler.net/problem=7">10001st prime</a></h1>
 * 
 * <p>
 * 
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
 * that the 6th prime is 13.
 * 
 * What is the 10 001st prime number? *
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem7 {
	public static void main(String[] args) {
		int index = 10_001;
		long result = new Problem7().getNthPrime(index);
		System.out.println("Result: " + result);
	}
	
	public long getNthPrime(int index) {
		Stream<Long> stream = new Primes(1000000).primesAsStream();
		Object result = stream.toArray()[index-1];
		return (long)result;
	}

}
