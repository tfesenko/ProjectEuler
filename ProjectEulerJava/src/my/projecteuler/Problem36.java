package my.projecteuler;

import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * <h1><a href="http://projecteuler.net/problem=36">Double-base palindromes</a></h1>
 * 
 * <p>
 * The decimal number, 585 = 1001001001 b2 (binary), is palindromic in both
 * bases.
 * 
 * Find the sum of all numbers, less than one million, which are palindromic in
 * base 10 and base 2.
 * 
 * (Please note that the palindromic number, in either base, may not include
 * leading zeros.)
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem36 {
	public static void main(String[] args) {
		int result = IntStream.range(1, 1_000_000)
				.filter(isDoubleBasePalindrome()).sum();
		System.out.println(result);
	}

	private static IntPredicate isDoubleBasePalindrome() {
		return isDecimalPalindrome().and(isBinaryPalindrome());
	}

	private static IntPredicate isDecimalPalindrome() {
		// We can compose functions, can we compose functions with predicates?
		return i -> isPalindrome().test(Integer.toString(i));
	}

	private static IntPredicate isBinaryPalindrome() {
		return i -> isPalindrome().test(Integer.toBinaryString(i));
	}

	private static Predicate<String> isPalindrome() {
		return str -> IntStream
				.rangeClosed(0, str.length() / 2)
				.mapToObj(
						i -> str.charAt(i) == str.charAt(str.length() - 1 - i))
				.reduce((b1, b2) -> b1 && b2).get();
	}

}
