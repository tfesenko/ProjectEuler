package my.projecteuler;

import java.util.stream.IntStream;

/**
 * <h1><a href="http://projecteuler.net/problem=9">Special Pythagorean
 * triplet</a></h1>
 * 
 * <p>
 * 
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for
 * which,
 * 
 * a2 + b2 = c2 For example, 32 + 42 = 9 + 16 = 25 = 52.
 * 
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find
 * the product abc.
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem9 {
	public static void main(String[] args) {
		IntStream.rangeClosed(1, 1000 - 2 - 3).forEach(
				a -> {
					IntStream
							.rangeClosed(a, 1000 - 3)
							.filter(b -> (a * a + b * b == (1000 - a - b)
									* (1000 - a - b)))
							.forEach(
									b -> {
										System.out.println("a=" + a + ", b="
												+ b + ", c=" + (1000 - a - b));
										System.out.println("Product: " + a * b
												* (1000 - a - b));
									});
					;
				});
	}
}
