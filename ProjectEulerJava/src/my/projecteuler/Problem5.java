package my.projecteuler;

import java.util.OptionalInt;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <h1><a href="http://projecteuler.net/problem=5">Smallest multiple</a></h1>
 * 
 * <p>
 * 2520 is the smallest number that can be divided by each of the numbers from 1
 * to 10 without any remainder. <br>
 * What is the smallest positive number that is evenly divisible by all of the
 * numbers from 1 to 20?
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem5 {
	public static void main(String[] args) {
		OptionalInt result = IntStream.iterate(1, n -> n + 1).sequential()
				.filter(evenlyDivisibleByAllUpto(20)).findFirst();
		System.out.println(result.getAsInt());
	}

	private static IntPredicate evenlyDivisibleByAllUpto(int upperBound) {
		IntPredicate alwaysTrue = x -> true;
		Stream<IntPredicate> predicates = IntStream.rangeClosed(1, upperBound)
				.mapToObj(x -> evenlyDivisibleBy(x));
		IntPredicate result = predicates.reduce(alwaysTrue, IntPredicate::and);
		return result;
	}

	private static IntPredicate evenlyDivisibleBy(int divisor) {
		return v -> v % divisor == 0;
	}

}
