package my.projecteuler;

import static my.projecteuler.StringUtils.toDigits;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1><a href="http://projecteuler.net/problem=16">Power digit sum</a></h1>
 * 
 * <p>
 * 2pow15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26. <br>
 * What is the sum of the digits of the number 2pow1000?
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem16 {
	public static void main(String[] args) {
		BigInteger twoPowerThounsand = new BigInteger("2").pow(1000);
		List<Integer> digits = toDigits(twoPowerThounsand).collect(Collectors.<Integer> toList());
		int result = digits.stream().mapToInt(x->x).sum();
		System.out.println("Result: " + result);
	}

}
