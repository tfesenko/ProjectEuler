package my.projecteuler;

import java.math.BigInteger;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility methods for String
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class StringUtils {

	public static Stream<Integer> toDigits(String digits) {
		return digits.chars().map(Character::getNumericValue).boxed();
	}

	public static Stream<Integer> toDigits(BigInteger bigInt) {
		return  toDigits(bigInt.toString());
	}
	
	public static Set<Integer> toDigitsSet(String digits) {
		return toDigits(digits).collect(Collectors.<Integer> toSet());
	}

}
