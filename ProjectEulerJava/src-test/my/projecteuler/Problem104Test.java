package my.projecteuler;

import static java.util.Arrays.asList;
import static my.projecteuler.Problem104.endsWithPandigital;
import static my.projecteuler.Problem104.fibonacciNumbersUntil;
import static my.projecteuler.Problem104.isPandigital;
import static my.projecteuler.Problem104.startsWithPandigital;
import static my.projecteuler.Problem104.toDigits;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for {@link Problem104}
 *
 */
public class Problem104Test {

	@Test
	public void testToDigits() {
		// alternatively, we can use Guava's Sets.newHashSet() 
		Set<Integer> expected = new HashSet<>(asList(1, 2, 3));
		assertThat(toDigits("123"), equalTo(expected));
	}

	@Test
	public void testIsPandigital_true() {
		assertThat(isPandigital().test("987654321"), is(true));
	}

	@Test
	public void testIsPandigital_false() {
		assertThat(isPandigital().test("112345678"), is(false));
	}

	@Test
	public void testEndsWithPandigital_true() {
		assertThat(endsWithPandigital().test("987654321"), is(true));
	}

	@Test
	public void testEndsWithPandigital_false() {
		assertThat(endsWithPandigital().test("9876543211"), is(false));
	}

	@Test
	public void testFirstStartsWithPandigital() {
		assertThat(fibonacciNumbersUntil(startsWithPandigital()).count(),
				equalTo(2749l));
	}

}
