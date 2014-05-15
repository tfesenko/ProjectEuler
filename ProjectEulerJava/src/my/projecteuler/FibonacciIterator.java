package my.projecteuler;

import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class FibonacciIterator<T> implements Iterator<T> {
	private final T undefinedValue;
	private final T firstElementValue;
	private final T secondElementValue;
	private final Adder<T> adder;

	private T previous;
	private T current;

	public FibonacciIterator(T undefinedValue, T firstElementValue,
			T secondElementValue, Adder<T> appender) {
		this.undefinedValue = undefinedValue;
		this.firstElementValue = firstElementValue;
		this.secondElementValue = secondElementValue;
		this.adder = appender;
		previous = undefinedValue;
		current = undefinedValue;
	}

	@Override
	public T next() {
		T result = calculateNext();
		previous = current;
		current = result;
		return result;
	}

	protected Optional<T> getCurrent() {
		return current == undefinedValue ? Optional.empty() : Optional
				.of(current);
	}

	protected T calculateNext() {
		if (current == undefinedValue) {
			return firstElementValue;
		}
		if (previous == undefinedValue) {
			return secondElementValue;
		}
		return adder.add(previous, current);
	}

	public static <T> Stream<T> toStream(Iterator<T> iterator) {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
				iterator, Spliterator.ORDERED | Spliterator.NONNULL
						| Spliterator.SORTED), false);
	}

	public static <T> T getLast(Stream<T> stream) {
		return stream.reduce((previous, current) -> current).get();
	}

	public interface Adder<T> {
		T add(T v1, T v2);
	}
}