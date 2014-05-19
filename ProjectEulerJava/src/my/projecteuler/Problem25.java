package my.projecteuler;

import static my.projecteuler.fibonacci.FibonacciIterator.toStream;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import my.projecteuler.fibonacci.BigFibonacciIterator;

/**
 * <h1><a href="http://projecteuler.net/problem=25">1000-digit Fibonacci
 * number</a></h1>
 * 
 * <p>
 * The Fibonacci sequence is defined by the recurrence relation:<br>
 * 
 * Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.<br>
 * Hence the first 12 terms will be:<br>
 * 
 * F1 = 1 F2 = 1 F3 = 2 F4 = 3 F5 = 5 F6 = 8 F7 = 13 F8 = 21 F9 = 34 F10 = 55
 * F11 = 89 F12 = 144<br>
 * The 12th term, F12, is the first term to contain three digits.
 * <p>
 * </p>
 * What is the first term in the Fibonacci sequence to contain 1000 digits? </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 *
 */
public class Problem25 {

	public static void main(String[] args) {
		Stream<BigInteger> fibonacciNumbers = fibonacciNumbersUntil(numberOfDigitsEqualsTo(1000));
		long count = fibonacciNumbers.count();
		System.out.println("result: " + count);
	}

	private static Predicate<BigInteger> numberOfDigitsEqualsTo(
			int numberOfDigits) {
		return v -> v.toString().length() == numberOfDigits;
	}

	private static Stream<BigInteger> fibonacciNumbersUntil(
			final Predicate<BigInteger> until) {
		Iterator<BigInteger> iterator = new BigFibonacciIterator() {

			@Override
			protected boolean shouldTerminate(BigInteger currentValue) {
				return until.test(currentValue);
			}
		};
		return toStream(iterator);
	}

}
