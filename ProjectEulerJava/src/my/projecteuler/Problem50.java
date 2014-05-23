package my.projecteuler;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.IntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

/**
 * <h1><a href="http://projecteuler.net/problem=50">Consecutive prime sum</a></h1>
 * 
 * <p>
 * The prime 41, can be written as the sum of six consecutive primes:
 * 
 * 41 = 2 + 3 + 5 + 7 + 11 + 13 This is the longest sum of consecutive primes
 * that adds to a prime below one-hundred.
 * 
 * The longest sum of consecutive primes below one-thousand that adds to a
 * prime, contains 21 terms, and is equal to 953.
 * 
 * Which prime, below one-million, can be written as the sum of the most
 * consecutive primes?
 * </p>
 * 
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public class Problem50 {
	public static void main(String[] args) {
		long upper = 1_000_000;
		long result = new Problem50().solve(upper);
		System.out.println("Result: " + result);
	}

	public long solve(long upperBound) {
		Primes primes = new Primes(upperBound);
		List<Long> primesAsList = primes.primesAsStream().sequential()
				.filter(x -> x < upperBound).map(x -> x)
				.collect(Collectors.toList());
		Iterator<Long> primesIterator = primesAsList.iterator();
		int maxSequenceLength = (int) conditionalIterate(primesAsList.get(0),
				x -> x + primesIterator.next(), x -> x > upperBound).count();
		IntFunction<Optional<Long>> findSubsequenceSummingToPrime = subsequenceLength -> IntStream
				.rangeClosed(0, primesAsList.size() - subsequenceLength)
				.mapToObj(
						x -> primesAsList.subList(x, x + subsequenceLength)
								.stream().mapToLong(v -> v))
				.map(el -> el.sum())
				.filter(el -> (el <= upperBound && primes.isPrime(el)))
				.findFirst();
		long result = IntStream.iterate(maxSequenceLength, i -> i - 1)
				.limit(maxSequenceLength).mapToObj(findSubsequenceSummingToPrime)
				.filter(s -> s.isPresent()).findFirst().get().get();
		return result;
	}

	private static LongStream conditionalIterate(final long seed,
			final LongUnaryOperator f, Predicate<Long> condition) {
		Objects.requireNonNull(f);
		final PrimitiveIterator.OfLong iterator = new PrimitiveIterator.OfLong() {
			long t = seed;

			@Override
			public boolean hasNext() {
				return !condition.test(t);
			}

			@Override
			public long nextLong() {
				long v = t;
				t = f.applyAsLong(t);
				return v;
			}
		};
		return StreamSupport.longStream(Spliterators.spliteratorUnknownSize(
				iterator, Spliterator.ORDERED | Spliterator.IMMUTABLE
						| Spliterator.NONNULL), false);
	}

}
