package my.projecteuler;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * <h1><a href="http://projecteuler.net/problem=1">Multiples of 3 and 5</a></h1>
 * 
 * <p>
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we
 * get 3, 5, 6 and 9. The sum of these multiples is 23.
 * </p>
 * <p>
 * Find the sum of all the multiples of 3 or 5 below 1000.
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem1 {
	public static void main(String[] args) {
		int result = IntStream.range(1, 1000)
				.filter(multipleOf(3).or(multipleOf(5))).sum();
		System.out.println("Problem 1: " + result);
	}

	private static IntPredicate multipleOf(int number) {
		return v -> (v % number == 0);
	}

}
