package my.projecteuler.fibonacci;

import java.math.BigInteger;

/**
 * Iterator for BigInteger Fibonacci sequence
 * @author Tatiana Fesenko <tatiana.fesenko@gmail.com>
 */
public abstract class BigFibonacciIterator extends
		FibonacciIterator<BigInteger> {
	public BigFibonacciIterator() {
		super(null, new BigInteger("1"), new BigInteger("1"), (v1, v2) -> v1
				.add(v2));
	}

	/**
	 * A condition of termination
	 * @param currentValue
	 * @return true is the iterator should terminate
	 */
	protected abstract boolean shouldTerminate(BigInteger currentValue);

	@Override
	public boolean hasNext() {
		return !this.getCurrent().isPresent()
				|| !shouldTerminate(this.getCurrent().get());
	};

}
