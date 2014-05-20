package my.projecteuler;

import static my.projecteuler.StringUtils.toDigitsSet;
import static my.projecteuler.fibonacci.FibonacciIterator.toStream;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import my.projecteuler.fibonacci.BigFibonacciIterator;

/**
 * <h1><a href="http://projecteuler.net/problem=49">Pandigital Fibonacci
 * ends</a></h1>
 * 
 * <p>
 * The Fibonacci sequence is defined by the recurrence relation:
 * 
 * Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1. It turns out that F541, which
 * contains 113 digits, is the first Fibonacci number for which the last nine
 * digits are 1-9 pandigital (contain all the digits 1 to 9, but not necessarily
 * in order). And F2749, which contains 575 digits, is the first Fibonacci
 * number for which the first nine digits are 1-9 pandigital. <br>
 * Given that Fk is the first Fibonacci number for which the first nine digits
 * AND the last nine digits are 1-9 pandigital, find k.
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 *
 */
public class Problem104 {

	private static final Set<Integer> ALL_DIGITS = IntStream.range(1, 10)
			.boxed().collect(Collectors.<Integer> toSet());

	public static void main(String[] args) {
		Stream<BigInteger> fibonacciNumbers = fibonacciNumbersUntil(startsWithPandigital()
				.and(endsWithPandigital()));
		long count = fibonacciNumbers.count();
		System.out.println("Result: " + count);
	}

	public static Predicate<String> startsWithPandigital() {
		return v -> (v.length() < 9) ? false : isPandigital().test(
				v.substring(0, 9));
	}

	public static Predicate<String> endsWithPandigital() {
		return v -> (v.length() < 9) ? false : isPandigital().test(
				v.substring(v.length() - 9));
	}

	public static Predicate<String> isPandigital() {
		return v -> ALL_DIGITS.equals(toDigitsSet(v));
	}

	public static Stream<BigInteger> fibonacciNumbersUntil(
			final Predicate<String> until) {
		Iterator<BigInteger> iterator = new BigFibonacciIterator() {

			@Override
			protected boolean shouldTerminate(BigInteger currentValue) {
				return until.test(currentValue.toString());
			}
		};
		return toStream(iterator);
	}

}
