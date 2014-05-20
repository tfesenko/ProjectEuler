package my.projecteuler;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import com.google.common.collect.Maps;

/**
 * <h1><a href="http://projecteuler.net/problem=14">Longest Collatz sequence</a>
 * </h1>
 * 
 * <p>
 * The following iterative sequence is defined for the set of positive integers:
 * 
 * n → n/2 (n is even) n → 3n + 1 (n is odd)
 * 
 * Using the rule above and starting with 13, we generate the following
 * sequence:
 * 
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1 It can be seen that this sequence
 * (starting at 13 and finishing at 1) contains 10 terms. Although it has not
 * been proved yet (Collatz Problem), it is thought that all starting numbers
 * finish at 1.
 * 
 * Which starting number, under one million, produces the longest chain?
 * 
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem14 {
	public static void main(String[] args) {
		Problem14 problem = new Problem14();
		problem.solve();
	}

	private Map<BigInteger, BigInteger> chainLengths = Maps.newHashMap();

	private void init() {
		chainLengths.clear();
		chainLengths.put(ONE, ONE);
	}

	public void solve() {
		init();
		IntStream.rangeClosed(1, 1_000_000)
				.mapToObj(x -> new BigInteger(String.valueOf(x)))
				.forEach(x -> getChainLength(x));
		Entry<BigInteger, BigInteger> maxLength = chainLengths.entrySet()
				.stream().max(new Comparator<Entry<BigInteger, BigInteger>>() {

					@Override
					public int compare(Entry<BigInteger, BigInteger> o1,
							Entry<BigInteger, BigInteger> o2) {
						return o1.getValue().compareTo(o2.getValue());
					}
				}).get();
		System.out.println("Max length: " + maxLength.getValue());
		System.out.println("Index: " + maxLength.getKey());
	}

	protected BigInteger getChainLength(BigInteger n) {
		if (chainLengths.containsKey(n)) {
			return chainLengths.get(n);
		}
		BigInteger result = getChainLength(calculateNext(n)).add(ONE);
		chainLengths.put(n, result);
		return result;
	}

	protected BigInteger calculateNext(BigInteger n) {
		BigInteger two = new BigInteger("2");
		if (n.remainder(new BigInteger("2")).equals(ZERO)) {
			return n.divide(two);
		}
		return n.multiply(new BigInteger("3")).add(ONE);
	}

}
