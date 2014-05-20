package my.projecteuler;

import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <h1><a href="http://projecteuler.net/problem=4">Largest palindrome
 * product</a></h1>
 * 
 * <p>
 * A palindromic number reads the same both ways. The largest palindrome made
 * from the product of two 2-digit numbers is 9009 = 91 × 99. <br>
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem4 {
	public static void main(String[] args) {
		int result = productsOfThreeDigitNumbers().filter(palindrome()).max()
				.getAsInt();
		System.out.println("Result: " + result);
	}

	private static IntStream productsOfThreeDigitNumbers() {
		// Unfortunately, Streams.zip() was not included to Java 8,
		// therefore, this workaround
		Stream<StreamPair> temp = IntStream.rangeClosed(100, 999).mapToObj(
				v -> new StreamPair(v, IntStream.range(1, v)));
		Stream<Integer> something = temp.flatMap(multiply());
		return something.mapToInt(x -> x);
	}

	private static Function<StreamPair, Stream<Integer>> multiply() {
		return t -> (t.stream.map((m) -> t.range * m).boxed());
	}

	private static IntPredicate palindrome() {
		return v -> isPalindrome(Integer.toString(v));
	}

	private static boolean isPalindrome(String str) {
		return str.equals(reverse(str));
	}

	private static String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}

	private static class StreamPair {
		public final int range;
		public final IntStream stream;

		public StreamPair(int curr, IntStream stream) {
			this.range = curr;
			this.stream = stream;

		}
	}

}
