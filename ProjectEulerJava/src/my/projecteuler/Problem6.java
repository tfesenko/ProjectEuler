package my.projecteuler;

import java.util.stream.IntStream;

/**
 * <h1><a href="http://projecteuler.net/problem=6">Sum square difference</a></h1>
 * 
 * <p>
 * The sum of the squares of the first ten natural numbers is,
 * 
 * 12 + 22 + ... + 102 = 385 <br>
 * The square of the sum of the first ten natural numbers is,
 * 
 * (1 + 2 + ... + 10)2 = 552 = 3025
 * 
 * </p>
 * <p>
 * Hence the difference between the sum of the squares of the first ten natural
 * numbers and the square of the sum is 3025 − 385 = 2640.<br>
 * 
 * Find the difference between the sum of the squares of the first one hundred
 * natural numbers and the square of the sum.
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem6 {
	public static void main(String[] args) {
		int upperBound = 100;
		int sumOfSquares = IntStream.rangeClosed(1, upperBound).map(v -> v * v)
				.sum();
		int sum = IntStream.rangeClosed(1, upperBound).sum();
		int squareOfSum = sum * sum;
		int result = squareOfSum - sumOfSquares;
		System.out.println("Problem 6: " + result);
	}

}
