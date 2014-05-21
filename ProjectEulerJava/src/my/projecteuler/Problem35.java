package my.projecteuler;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <h1><a href="http://projecteuler.net/problem=35">Circular primes</a></h1>
 * 
 * <p>
 * The number, 197, is called a circular prime because all rotations of the
 * digits: 197, 971, and 719, are themselves prime.
 * 
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71,
 * 73, 79, and 97.
 * 
 * How many circular primes are there below one million?
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem35 {
	public static void main(String[] args) {
		int bound = 1_000_000;
		long result = new Problem35().getNumberOfCircluarPrimesBelow(bound);
		System.out.println("Result: " + result);
	}

	private Primes primes;

	public long getNumberOfCircluarPrimesBelow(int num) {
		primes = new Primes(num);
		Stream<Long> primesBelowNum = primes.primesAsStream().filter(
				x -> x < num);
		Stream<Long> circularPrimes = primesBelowNum.filter(isCircular());
		long result = circularPrimes.count();
		return result;
	}

	private Predicate<Long> isCircular() {
		return v -> {
			Optional<Boolean> result = getRotations(v).map(
					n -> primes.isPrime(n)).reduce((b1, b2) -> b1 && b2);
			return result.isPresent() ? //
			result.get()
					: true;// single-digit numbers are circular
		};
	}

	private Stream<Long> getRotations(long num) {
		return getRotations(String.valueOf(num)).map(s -> Long.parseLong(s));
	}

	private Stream<String> getRotations(String str) {
		return IntStream.range(1, str.length()).mapToObj(
				i -> (String) (str.substring(i) + str.substring(0, i)));
	}

}
