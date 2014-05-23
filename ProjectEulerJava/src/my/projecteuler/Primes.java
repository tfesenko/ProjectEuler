package my.projecteuler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Primes {
	private final Map<Long, Boolean> primes = new HashMap<>();
	private final long maxSize;

	public Primes(long maxSize) {
		this.maxSize = maxSize;
		boundedStream(2l, i -> i + 1).forEach(x -> primes.put(x, true));
		boundedStream(2l, i -> i + 1).forEach(sieveFactors());
	}

	public boolean isPrime(Long number) {
		if (!primes.containsKey(number)) {
			throw new RuntimeException("isPrime() is undefined for " + number);
		}
		return primes.get(number);
	}

	public Stream<Long> primesAsStream() {
		return primes.entrySet().stream().filter(v -> v.getValue())
				.map(v -> v.getKey());
	}

	private Consumer<Long> sieveFactors() {
		return num -> {
			if (2 * num < maxSize) {
				boundedStream(2 * num, x -> x + num).forEach(
						x -> primes.put(x, false));
			}
		};
	}

	private Stream<Long> boundedStream(final Long seed,
			final UnaryOperator<Long> f) {
		return boundedStream(seed, f, maxSize);
	}

	private static Stream<Long> boundedStream(final Long seed,
			final UnaryOperator<Long> f, final Long maxValue) {
		final Iterator<Long> iterator = new Iterator<Long>() {
			Long t = null;

			@Override
			public boolean hasNext() {
				return t == null || t <= maxValue;
			}

			@Override
			public Long next() {
				Long result = t = (t == null) ? seed : f.apply(t);
				return result;
			}
		};
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
				iterator, Spliterator.ORDERED | Spliterator.IMMUTABLE), false);
	}

}
